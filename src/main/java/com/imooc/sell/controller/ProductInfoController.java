package com.imooc.sell.controller;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dataobject.Result;
import com.imooc.sell.enums.ProductStatusEunm;
import com.imooc.sell.service.ProductService;
import com.imooc.sell.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
//@RequestMapping(value = "/sell")
public class ProductInfoController {
    @Autowired
    private ProductService productService;


    /** 查询所上架商品 **/

    @RequestMapping(value = "/product/upall",method = RequestMethod.GET)
    private Result<ProductInfo> getProductAll(){
        return ResultUtil.success(productService.findUpAll());
    }

    /**添加商品**/
    @RequestMapping(value = "/product/add",method = RequestMethod.POST)
    public Result<ProductInfo> addProduct(@Valid ProductInfo productInfo , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        productInfo.setProductId(productInfo.getProductId());
        productInfo.setProductName(productInfo.getProductName());
        productInfo.setProductPrice(productInfo.getProductPrice());
        productInfo.setProductStock(productInfo.getProductStock());
        productInfo.setProductDescription(productInfo.getProductDescription());
        productInfo.setProductIcon(productInfo.getProductIcon());
        productInfo.setProductStatus(ProductStatusEunm.DOWN.getCode());
        productInfo.setCategoryType(productInfo.getCategoryType());
        return ResultUtil.success(productService.save(productInfo));
    }


}
