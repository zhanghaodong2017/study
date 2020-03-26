package com.zhd.study.reflection;

import com.zhd.study.proxy.UserManager;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author: zhanghaodong
 * @description https://www.cnblogs.com/yanjie-java/p/7940929.html
 * https://github.com/michaelliao/compiler
 * @date: 2020-01-15 15:42
 */
public class CompilerTest {
    static final String JAVA_SOURCE_CODE =
            "package com.zhd.study.reflection;                   "
                    + "import com.zhd.study.proxy.UserManagerImpl;       "
                    + "public class UserManagerImplProxy extends UserManagerImpl {    "
                    + "    public UserManagerImplProxy() {                            "
                    + "        System.out.println(\"UserManagerImplProxy构建了\");     "
                    + "    }                                                          "
                    + "    public void doWork2(){                                    "
                    + "    System.out.println(\"doWork2\");         "
                    + "    }                                                          "
                    + "}                                                              ";

    public static void main(String[] args) throws Exception {
        JavaStringCompiler compiler = new JavaStringCompiler();
        Map<String, byte[]> results = compiler.compile("UserManagerImplProxy.java", JAVA_SOURCE_CODE);
        Class<?> clazz = compiler.loadClass("com.zhd.study.reflection.UserManagerImplProxy", results);
        // try instance:
        UserManager user = (UserManager) clazz.newInstance();
        Method doWorkMethod = clazz.getMethod("doWork2");
        user.doWork("lisisisis");
        doWorkMethod.invoke(user);
    }
}
