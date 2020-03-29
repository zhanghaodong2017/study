package com.zhd.study.nio.znettyXml.codec;

import io.netty.handler.codec.http.FullHttpResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/29 21:20
 * Modified by :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpJsonResponse {
    private FullHttpResponse httpResponse;
    private Object result;

}
