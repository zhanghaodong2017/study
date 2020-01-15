package com.zhd.study.proxy;

import com.alibaba.fastjson.JSON;
import com.zhd.study.codeGenerator.JDBCUtils;
import com.zhd.study.mybaits.SqlXMLConfig;
import com.zhd.study.utils.AnnotationResolver;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-14 10:42
 */
public class JdkMapperProxy<T> implements InvocationHandler {

    Map<String, SqlXMLConfig> mapperSql;//需要代理的目标对象
    private Class<T> clazz;


    public JdkMapperProxy(Class<T> clazz, Map<String, SqlXMLConfig> mapperSql) {
        this.clazz = clazz;
        this.mapperSql = mapperSql;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        SqlXMLConfig sqlXMLConfig = mapperSql.get(clazz.getName() + "-" + method.getName());
        if (sqlXMLConfig == null) {
            System.out.println("没有找到mapper.xml");
            return null;
        }

        String sqlText = AnnotationResolver.resolver(sqlXMLConfig.getSqlText());
        List<Map<String, Object>> list = JDBCUtils.executeQuery2(sqlText, args);
        System.out.println(JSON.toJSONString(list));
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() == 1) {
            return JSON.parseObject(JSON.toJSONString(list.get(0)), method.getReturnType());
        }
        return JSON.parseArray(JSON.toJSONString(list), method.getReturnType());
    }

    //定义获取代理对象方法
    public T getJDKProxy() {
        //JDK动态代理只能针对实现了接口的类进行代理，newProxyInstance 函数所需参数就可看出
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }
}
