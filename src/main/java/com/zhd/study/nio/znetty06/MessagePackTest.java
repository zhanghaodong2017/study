package com.zhd.study.nio.znetty06;


import freemarker.template.Template;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.Arrays;

/**
 * @Auther : zhd
 * @Description :
 * Date : Created in 2020/3/28 14:12
 * Modified by :
 */
public class MessagePackTest {

    public static void main(String[] args) {

        try {
            MsgDTO msgDTO = new MsgDTO(0, "test", "哈哈哈");
            MessagePack messagePack = new MessagePack();
            byte[] bytes = messagePack.write(msgDTO);
            System.out.println(Arrays.toString(bytes));

            MsgDTO dto = messagePack.read(bytes, MsgDTO.class);
            System.out.println(dto.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
