package com.zhd.study.nio.znetty07;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-29 13:41
 */
@Data
@AllArgsConstructor
public class Response implements Serializable {

    private static final long serialVersionUID = 1L;

    private int subReqID;

    private int respCode;

    private String desc;
}
