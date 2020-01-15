package com.zhd.study.mybaits;

import com.zhd.study.proxy.JdkMapperProxy;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-14 15:04
 */
public class MybtisFactory {

    private static MybtisFactory factory;
    Map<String, SqlXMLConfig> mapperSql = new HashMap<>();

    private MybtisFactory() {
    }

    public static MybtisFactory newInstance() {
        if (factory == null) {
            factory = new MybtisFactory();
        }
        return factory;
    }

    public void init(String[] fileNames) throws Exception {
        if (fileNames == null || fileNames.length == 0) {
            throw new RuntimeException("请输入xml文件");
        }
        for (int i = 0; i < fileNames.length; i++) {
            initMapper(fileNames[i]);
        }
    }

    private void initMapper(String fileName) throws DocumentException {
        String pathName = MybtisFactory.class.getClassLoader().getResource(fileName).getPath();
        File file = new File(pathName);

        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element mapperRoot = document.getRootElement();
        Attribute namespaceAttr = mapperRoot.attribute("namespace");
        List<Element> elements = mapperRoot.elements();

        for (Iterator<Element> it = elements.iterator(); it.hasNext(); ) {
            Element ele = it.next();
            Attribute idAttr = ele.attribute("id");
            Attribute resultTypeAttr = ele.attribute("resultType");
            SqlXMLConfig sqlXMLConfig = new SqlXMLConfig(1, idAttr.getValue(), ele.getTextTrim(), resultTypeAttr.getValue());
            mapperSql.put(namespaceAttr.getValue() + "-" + idAttr.getValue(), sqlXMLConfig);
        }
        System.out.println("初始化完成");
    }

    public <T> T getMapper(Class<T> clazz) {
        JdkMapperProxy<T> jdkMapperProxy = new JdkMapperProxy(clazz, mapperSql);//实例化JDKProxy对象
        return jdkMapperProxy.getJDKProxy();//获取代理对象
    }
}
