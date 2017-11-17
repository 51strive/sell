package com.imooc.sell.service;

import com.imooc.sell.dataobject.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuzhang on 2017/8/2.
 */
@Service
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);


    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    List<ProductCategory> findAll();

    ProductCategory save(ProductCategory productCategory);
}
