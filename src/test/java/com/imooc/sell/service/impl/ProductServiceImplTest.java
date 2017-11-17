package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.enums.ProductStatusEunm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by liuzhang on 2017/8/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productService.findOne("123455");
        Assert.assertEquals("123455",productInfo.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> productInfoList = productService.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());
     }

    @Test
    public void findAll() throws Exception {
        PageRequest request = new PageRequest(0,1);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        productInfoPage.getTotalElements();
    }

    @Test
    public void save() throws Exception {

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1234555");
        productInfo.setProductName("鱼香肉丝62");
        productInfo.setProductPrice(new BigDecimal(13.5));
        productInfo.setProductStock(107);
        productInfo.setProductDescription("aa哼好得分额8");
        productInfo.setProductIcon("http://www.xaaaxxx.img");
        productInfo.setProductStatus(ProductStatusEunm.DOWN.getCode());
        productInfo.setCategoryType(2);
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findDownAll() throws Exception {
        List<ProductInfo> productInfoList = productService.findDownAll();
        Assert.assertNotEquals(0,productInfoList.size());
    }
}