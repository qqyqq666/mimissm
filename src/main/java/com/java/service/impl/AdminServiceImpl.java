package com.java.service.impl;

import com.java.mapper.AdminMapper;
import com.java.pojo.Admin;
import com.java.pojo.AdminExample;
import com.java.service.AdminService;
import com.java.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JlX
 * @create 2022-03-06 10:55
 */
@Service    //交给spring创建AdminServiceImpl对象
public class AdminServiceImpl implements AdminService {
    //在业务逻辑层一定会有数据访问层的对象
    @Autowired// 交给spring容易自动创建adminMapper 对象
    AdminMapper adminMapper;

    @Override
    public Admin login(String name, String pwd) {
        //根据传入的用户或到数据库中查询相应的用户对象
        //如果有条件 ,则一定要创建AdminExample的对象,用来封装条件
        AdminExample example = new AdminExample();
        /**如何添加条件
         * select * from admin where a_name ='admin'
         */
        //添加用户名a_name条件
        example.createCriteria().andANameEqualTo(name);
        List<Admin> list = adminMapper.selectByExample(example);
        //list查就不会为空
        if(list.size() > 0){
            //如果查询到用户对象,再进行密码的比对,注意密码是密文
            Admin admin = list.get(0);
            /**
             * 分析:
             * admin.getApass==>c984aed014aec7623a54f0591da07a85fd4b762d
             * pwd===>000000
             * 在进行密码对比时,要将传入的pwd进行md5加密,再与数据库中查到的对象的密码进行对比
             */
            String mipwd = MD5Util.getMD5(pwd);
            if(mipwd.equals(admin.getaPass())){
                return admin;
            }
        }


        return null;
    }
}
