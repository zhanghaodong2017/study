package com.zhd.study.proxy.asm;

import jdk.internal.org.objectweb.asm.*;

import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-29 12:51
 */
public class ASMHelper implements Opcodes {

    public static void main(String[] args) throws Exception {
        ClassReader cr = new ClassReader("com.zhd.study.proxy.asm.Foo");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor cv = new MyClassVisitor(ASM5, cw);
        cr.accept(cv, ClassReader.SKIP_FRAMES);
        Files.write(Paths.get("Foo2.class"), cw.toByteArray());
    }

    static class MyMethodVisitor extends MethodVisitor {
        private MethodVisitor mv;

        public MyMethodVisitor(int api, MethodVisitor mv) {
            super(api, null);
            this.mv = mv;
        }

        @Override
        public void visitCode() {
            mv.visitCode();
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Hello, World!");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
    }

    static class MyClassVisitor extends ClassVisitor {

        public MyClassVisitor(int api, ClassVisitor cv) {
            super(api, cv);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
                                         String[] exceptions) {
            MethodVisitor visitor = super.visitMethod(access, name, descriptor, signature, exceptions);
            if ("main".equals(name)) {
                return new MyMethodVisitor(ASM5, visitor);
            }
            return visitor;
        }
    }

}
