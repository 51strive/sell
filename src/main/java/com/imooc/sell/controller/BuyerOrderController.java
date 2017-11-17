package com.imooc.sell.controller;

import com.imooc.sell.converter.OrderForm2OrderDTOConverter;
import com.imooc.sell.dataobject.Result;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    protected OrderService orderService;

    @Autowired
    protected BuyerService buyerService;

    //创建订单
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Result<Map<String ,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确,orderFrom={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultUtil.success(map);
    }

    //订单列表
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result<List<OrderDTO>> List(@RequestParam("openid") String openid,
                                       @RequestParam(value = "page",defaultValue = "0") Integer page,
                                       @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,request);

        return ResultUtil.success(orderDTOPage.getContent());
    }

    @GetMapping("/detail")
    public Result<OrderDTO> detail(@RequestParam(value = "openid",defaultValue = " ") String openid,
                                   @RequestParam("orderid") String orderid){
        OrderDTO orderDTO = buyerService.findOrderOne(openid,orderid);
        return ResultUtil.success(orderDTO);
    }

    @PostMapping("/cancel")
    public Result cancel(@RequestParam(value = "openid",defaultValue = " ") String openid,
                                     @RequestParam("orderid") String orderid){
        buyerService.cancelOrder(openid,orderid);
        return ResultUtil.success();
    }

}
