package com.zhd.study.reflection;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-15 15:17
 */
public class JavaStringCompiler {

    JavaCompiler compiler;
    StandardJavaFileManager stdManager;

    public JavaStringCompiler() {
        this.compiler = ToolProvider.getSystemJavaCompiler();
        this.stdManager = compiler.getStandardFileManager(null, null, null);
    }

    public Map<String, byte[]> compile(String fileName, String source) {
        try (MemoryJavaFileManager manager = new MemoryJavaFileManager(this.stdManager)) {
            JavaFileObject javaFileObject = manager.makeStringSource(fileName, source);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, Arrays.asList(javaFileObject));
            Boolean result = task.call();
            if (result == null || !result.booleanValue()) {
                throw new RuntimeException("Compilation failed.");
            }
            return manager.getClassBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Class<?> loadClass(String name, Map<String, byte[]> classBytes) throws ClassNotFoundException, IOException {
        try (MemoryClassLoader classLoader = new MemoryClassLoader(classBytes)) {
            return classLoader.loadClass(name);
        }
    }
}
