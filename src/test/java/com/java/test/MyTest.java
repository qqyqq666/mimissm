package com.java.test;

import com.java.mapper.ProductInfoMapper;
import com.java.pojo.ProductInfo;
import com.java.pojo.vo.ProductInfoVo;
import com.java.utils.MD5Util;
import org.apache.ibatis.javassist.ClassPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author JlX
 * @create 2022-03-06 10:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml","classpath:applicationContext-service.xml"})
public class MyTest {

    @Autowired
    ProductInfoMapper mapper;

    @Test
    public void testSelectCondition(){
        ProductInfoVo vo = new ProductInfoVo();
        //vo.setPname("4");
        //vo.setTypeid(3);
       // vo.setLprice(3000);
       // vo.setHprice(3999);
        List<ProductInfo> list = mapper.selectCondition(vo);
        list.forEach(productInfo -> System.out.println(productInfo));
    }
}
