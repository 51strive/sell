package com.imooc.sell.handle;

import com.imooc.sell.dataobject.Result;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liuzhang on 2017/8/16.
 */

@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof SellException){
            SellException sellException = (SellException) e;
            return ResultUtil.error(sellException.getCode(),sellException.getMessage());
        }else {
            return ResultUtil.error(-1,"未知错误");
        }
    }
}
