package com.zhd.study.nio.znettyXml.pojo;

import lombok.Data;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/29 21:44
 * Modified by :
 */
@Data
public class Order {
    private int orderId;
    private Customer customer;
    private Address address;

}
