package com.zhd.study.nio.znettyCustom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-03-30 13:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NettyMessage {

    private Header header;
    private Object body;


}
