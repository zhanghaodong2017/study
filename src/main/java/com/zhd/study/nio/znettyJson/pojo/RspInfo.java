package com.zhd.study.nio.znettyJson.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-30 10:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RspInfo {
    private int code;
    private String msg;
    private Object data;
}
