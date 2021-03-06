package com.imooc.sell.dataobject;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * Created by liuzhang
 * 2017-08-03
 */

@Data
public class Result<T> {
    /** 错误代码 **/
    private Integer code;

    /** 提示信息 **/
    private String msg;

    /** 具体的内容 **/
    private T data;

}
