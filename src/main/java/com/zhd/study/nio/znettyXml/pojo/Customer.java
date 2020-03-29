package com.zhd.study.nio.znettyXml.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/29 21:47
 * Modified by :
 */
@Data
public class Customer {
    private String firstName;
    private String lastName;
    private List<String> middleNames;
}