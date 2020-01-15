package com.zhd.study.atm;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-11 15:20
 */
@Data
@AllArgsConstructor
public class IPBean {
    public static final int TYPE_HTTP = 1;
    public static final int TYPE_HTTPS = 2;
    private String ip;
    private int port;
    private int type;

}
