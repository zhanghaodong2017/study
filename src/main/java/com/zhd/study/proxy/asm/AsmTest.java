package com.zhd.study.proxy.asm;


import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-29 12:43
 */
public class AsmTest {
    public static void main(String[] args) throws IOException {
        ClassReader cr = new ClassReader("com.zhd.study.proxy.asm.Foo");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cr.accept(cw, ClassReader.SKIP_FRAMES);
        Files.write(Paths.get("Foo2.class"), cw.toByteArray());
    }
}
