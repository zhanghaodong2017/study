package com.zhd.study.nio.znetty06;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.msgpack.annotation.Message;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/28 14:09
 * Modified by :
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Message
public class MsgDTO {
    private int code;
    private String msg;
    private String data;

    @Override
    public String toString() {
        return "MessageDTO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
