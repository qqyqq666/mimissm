package com.java.service;

import com.github.pagehelper.PageInfo;
import com.java.pojo.ProductInfo;
import com.java.pojo.vo.ProductInfoVo;

import java.util.List;

/**
 * @author JlX
 * @create 2022-03-06 15:07
 */
public interface ProductInfoService {
    //显示全部商品信息 不分页
    List<ProductInfo> getAll();

    //分页功能实现
    PageInfo splitPage(int pageNum, int pageSize);//pageNum 当前页码，每页显示多少条数据

    //增加商品
    int save(ProductInfo info);

    //按主键id查询商品
    ProductInfo getByID(int pid);
    //更新商品
    int update(ProductInfo info);

    //单个商品删除
    int delete(int pid);

    //批量删除商品
    int deleteBatch(String []ids);
    //多条件商品查询
    List<ProductInfo> selectCondition(ProductInfoVo vo);

    //多条件查询分页
    public PageInfo splitPageVo(ProductInfoVo vo,int pageSize);
}
