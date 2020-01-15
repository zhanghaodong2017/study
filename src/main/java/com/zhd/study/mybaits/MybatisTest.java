package com.zhd.study.mybaits;

import com.alibaba.fastjson.JSON;
import com.zhd.study.mybaits.entity.UserInfo;
import com.zhd.study.mybaits.mapping.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultAttribute;
import org.springframework.util.Assert;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-13 19:36
 */
public class MybatisTest {

    public static void main(String[] args) throws Exception {
        doWork2();
    }

    private static void doWork2() throws Exception {
        //模拟初始化mybaits
        String fileName = "mapper/PersonMapper.xml";
        String pathName = MybatisTest.class.getClassLoader().getResource(fileName).getPath();
        System.out.println(pathName);
        File file = new File(pathName);
        System.out.println(file.exists());

        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        Element mapperRoot = document.getRootElement();
        Namespace namespace = mapperRoot.getNamespace();
        List<Element> elements = mapperRoot.elements();

        for (Iterator<Element> it = elements.iterator(); it.hasNext(); ) {
            Element ele = it.next();

            String name = ele.getName();
            Attribute idAttr = ele.attribute("id");
            Attribute resultTypeAttr = ele.attribute("resultType");

            List<DefaultAttribute> attributes = ele.attributes();
            for (DefaultAttribute attribute : attributes) {
                System.err.println(attribute.getName() + ":" + attribute.getValue());
            }
            System.err.println(ele.getTextTrim());

        }

        //获取mapper

        PersonMapper personMapper = getSessionFactory().openSession().getMapper(PersonMapper.class);

        UserInfo userInfo2 = personMapper.queryUserByGuid("scdsadcsvxvsdsdsd");

        System.out.println(JSON.toJSONString(userInfo2));

    }

    private static void doWork1() {
        SqlSessionFactory sessionFactory = getSessionFactory();

        Assert.notNull(sessionFactory, "sessionFactory创建失败");

        SqlSession sqlSession = sessionFactory.openSession();

        Assert.notNull(sqlSession, "sqlSession创建失败");

     /*   UserInfoMapper userMapper = sqlSession.getMapper(UserInfoMapper.class);

        UserInfo userInfo = userMapper.queryUserByName("admin");

        System.out.println(JSON.toJSONString(userInfo));*/

        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);

        UserInfo userInfo2 = personMapper.queryUserByGuid("scdsadcsvxvsdsdsd");

        System.out.println(JSON.toJSONString(userInfo2));

    }

    //Mybatis 通过SqlSessionFactory获取SqlSession, 然后才能通过SqlSession与数据库进行交互
    private static SqlSessionFactory getSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        String resource = "configuration.xml";
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource), "dev");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

}
