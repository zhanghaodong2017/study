package com.zhd.study.nio.znetty06;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class MessageDTO {
    private int code;
    private String msg;
    private Object data;

    @Override
    public String toString() {
        return "MessageDTO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
