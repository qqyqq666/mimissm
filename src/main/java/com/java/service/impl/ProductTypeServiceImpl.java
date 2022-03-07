package com.java.service.impl;

import com.java.mapper.ProductTypeMapper;
import com.java.pojo.ProductType;
import com.java.pojo.ProductTypeExample;
import com.java.service.ProductInfoService;
import com.java.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JlX
 * @create 2022-03-07 9:04
 */
@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {
    //在业务逻辑层一定有数据访问层对象
    @Autowired
    ProductTypeMapper productTypeMapper ;
    @Override
    public List<ProductType> getAll() {
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
