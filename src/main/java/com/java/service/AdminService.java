package com.java.service;

import com.java.pojo.Admin;
import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * @author JlX
 * @create 2022-03-06 10:54
 */
public interface AdminService {

     Admin login(String name, String pwd);//返回值为admin，方便后面使用admin相关属性

}
