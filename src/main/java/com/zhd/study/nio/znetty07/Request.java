package com.zhd.study.nio.znetty07;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-29 13:38
 */
@Data
@AllArgsConstructor
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    private int subReqID;

    private String userName;

    private String address;

}
