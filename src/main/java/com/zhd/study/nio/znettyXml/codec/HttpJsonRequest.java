package com.zhd.study.nio.znettyXml.codec;

import io.netty.handler.codec.http.FullHttpRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/29 21:19
 * Modified by :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpJsonRequest {
    private FullHttpRequest request;
    private Object body;

}
