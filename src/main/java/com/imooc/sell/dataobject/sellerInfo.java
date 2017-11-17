package com.imooc.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class sellerInfo {

    @Id
    private Integer sellerId;

    private String userName;

    private String password;

    private String openid;
}
