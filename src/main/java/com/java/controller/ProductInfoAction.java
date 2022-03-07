package com.java.controller;

import com.github.pagehelper.PageInfo;
import com.java.pojo.ProductInfo;

import com.java.service.ProductInfoService;
import com.java.utils.FileNameUtil;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author JlX
 * @create 2022-03-06 16:08
 */
@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    //每页显示的记录数
    public static final int PAGE_SIZE = 5;
    //异步上传的图片的名称
    String saveFileName = "";

    //切记:在界面层中,一定会有业务逻辑层的对象
    @Autowired
    ProductInfoService productInfoService ;
    //显示全部商品不分页
    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest request) {

        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list", list);
        return "product";
    }
    //显示第1页的5条记录
    @RequestMapping("/split")
    public String split(HttpServletRequest request){
            //得到第一页的数据
        PageInfo pageInfo = productInfoService.splitPage(1, PAGE_SIZE);
        request.setAttribute("info",pageInfo);
        return "product";
    }

    //ajax分页翻页处理 ResponseBody可以解析ajax请求，若有返回值还可以将返回值转为json格式
    @ResponseBody
    @RequestMapping("/ajaxSplit")
    public void ajaxSplit(int page, HttpSession session) {
        //取得当前page参数的页面的数据
        PageInfo info = productInfoService.splitPage(page,PAGE_SIZE);
        session.setAttribute("info", info);
    }

    //异步ajax文件上传处理   MultipartFile:专门用于文件上传流的接收
    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage, HttpServletRequest request){
        //提取生成文件名UUID+上传图片的后缀.jpg  .png
        saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());
        //得到项目中图片存储的路径
        String path = request.getServletContext().getRealPath("/image_big");
        //转存  E:\idea_workspace\mimissm\image_big\23sdfasferafdafdadfasfdassf.jpg
        try {                                 //File.separator  反斜杠
            pimage.transferTo(new File(path + File.separator + saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回客户端JSON对象,封装图片的路径,为了在页面实现立即回显
        JSONObject object = new JSONObject();
        object.put("imgurl", saveFileName);
        return object.toString();
    }
    @RequestMapping("/save")
    public String save(ProductInfo info, HttpServletRequest request) {
        info.setpImage(saveFileName);
        info.setpDate(new Date());//获取系统当前日期
        //info对象中有表单提交上来的5个数据,有异步ajax上来的图片名称数据,有上架时间的数据
        int num = -1;
        try {
            num = productInfoService.save(info);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (num > 0) {
            request.setAttribute("msg", "增加成功!");
        } else {
            request.setAttribute("msg", "增加失败!");
        }
        //清空saveFileName变量中的内容,为了下次增加或修改的异步ajax的上传处理
        saveFileName = "";
        //增加成功后应该重新访问数据库,所以跳转到分页显示的action上
        return "forward:/prod/split.action";
    }
    @RequestMapping("/one")
    public String one(int pid, Model model) {
        ProductInfo info = productInfoService.getByID(pid);
        model.addAttribute("prod", info);
        return "update";
    }

}
