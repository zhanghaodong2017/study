package com.zhd.study.codeGenerator;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.Map;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-04 14:41
 */
public class TemplateUtils {

    private static final String projectRoot = "/Users/zhanghaodong/work/myproject/sociology/";

    private static final String targetJava = "src/main/java/";
    private static final String targetResources = "src/main/resources/";
    private static final String controller = "com/zhd/ultimate/sociology/controller/";
    private static final String service = "com/zhd/ultimate/sociology/service/";
    private static final String serviceImpl = "com/zhd/ultimate/sociology/service/impl/";
    private static final String html = "templates/";


    public static void createQueryHtml(Map<String, Object> paramsMap, File parentFile, String tableEntityName) {
        File file = new File(parentFile, tableEntityName + "-query.html");
        System.out.println(file);
        writeTemplate(paramsMap, file, "query.ftl");
    }

    public static void createAddHtml(Map<String, Object> paramsMap, File parentFile, String tableEntityName) {
        File file = new File(parentFile, tableEntityName + "-add.html");
        System.out.println(file);
        writeTemplate(paramsMap, file, "add.ftl");
    }

    public static void createUpdateHtml(Map<String, Object> paramsMap, File parentFile, String tableEntityName) {
        File file = new File(parentFile, tableEntityName + "-update.html");
        System.out.println(file);
        writeTemplate(paramsMap, file, "update.ftl");
    }

    public static void createControllerCode(Map<String, Object> paramsMap, String firstUpTableName) {
        File file = new File(projectRoot + targetJava + controller, firstUpTableName + "Controller.java");
        System.out.println(file);
        writeTemplate(paramsMap, file, "controller.ftl");
    }

    public static void createServiceCode(Map<String, Object> paramsMap, String firstUpTableName) {
        File fileService = new File(projectRoot + targetJava + service, firstUpTableName + "Service.java");
        System.out.println(fileService);
        writeTemplate(paramsMap, fileService, "service.ftl");
    }

    public static void createServiceImplCode(Map<String, Object> paramsMap, String firstUpTableName) {
        File fileServiceImpl = new File(projectRoot + targetJava + serviceImpl, firstUpTableName + "ServiceImpl.java");
        System.out.println(fileServiceImpl);
        writeTemplate(paramsMap, fileServiceImpl, "serviceImpl.ftl");
    }

    public static void writeTemplate(Map<String, Object> paramsMap, File file, String templateName) {
        try {
            Template template = getTemplate(templateName);
            OutputStream fos = new FileOutputStream(file);
            Writer out = new OutputStreamWriter(fos);
            template.process(paramsMap, out);
            fos.flush();
            out.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printTemplate(Map<String, Object> paramsMap, String templateName) {
        try {
            Template template = getTemplate(templateName);
            Writer out = new OutputStreamWriter(System.out);
            template.process(paramsMap, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Template getTemplate(String templateName) {
        try {
            //配置类
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);

            File dir = new File(projectRoot + "src/test/java/com/zhd/ultimate/sociology/codeGenerator");
            cfg.setDirectoryForTemplateLoading(dir);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            return cfg.getTemplate(templateName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
