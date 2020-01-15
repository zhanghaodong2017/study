package com.zhd.study.mybaits;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-14 15:02
 */
@Data
@AllArgsConstructor
public class SqlXMLConfig {

    private Integer sqlType;

    private String methodName;

    private String sqlText;

    private String resultType;
}
