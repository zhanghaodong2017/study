package com.zhd.study.codeGenerator;

import freemarker.template.Configuration;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.sql.RowSet;
import java.io.File;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: zhanghaodong
 * @description 代码生成器-基于数据库：减少不必要的重复代码
 * @date: 2019-12-29 13:53
 * 操作步骤：1设计表结构，2生成mapper文件和代码，3生成Service、Controller和html代码
 */
public class ZenCode {


    private static final String projectRoot = "/Users/zhanghaodong/work/myproject/sociology/";

    private static final String targetJava = "src/main/java/";
    private static final String targetResources = "src/main/resources/";
    private static final String controller = "com/zhd/ultimate/sociology/controller/";
    private static final String service = "com/zhd/ultimate/sociology/service/";
    private static final String serviceImpl = "com/zhd/ultimate/sociology/service/impl/";
    private static final String html = "templates/";

    private static final String sqlTableColumn = "select COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT,COLUMN_KEY from information_schema.COLUMNS where table_name = ? order by ORDINAL_POSITION";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败！");
        }
    }

    private Configuration config;

    public static void main(String[] args) {
        String tableName = "student_grade";
        String tableEntityName = getEntityName(tableName);
        String firstUpTableName = getFirstUp(tableEntityName);

//        createJavaCode(tableEntityName, firstUpTableName);

        List<MyTableColumn> columns = getColumnByTableName(tableName);
//        System.out.println(JSON.toJSONString(columns));

//        createHtmlCode(tableEntityName, firstUpTableName, columns);

        createSelectCode(columns, tableEntityName);

    }

    private static void createSelectCode(List<MyTableColumn> columns, String tableEntityName) {
        for (MyTableColumn tableColumn : columns) {
            String comment = tableColumn.getComment();
            if (comment.contains("[") && comment.contains("]")) {
//                printSelectEnum(tableColumn);
                printSelectCode(tableColumn, tableEntityName);
            }
        }
    }

    private static void printSelectCode(MyTableColumn tableColumn, String tableEntityName) {
        LinkedHashMap<String, String> columnCommentMap = getColumnCommentMap(tableColumn.getComment());
        String entityName = tableColumn.getEntityName();
        StringBuilder builder = new StringBuilder();
        builder.append("\n\n                            <select name=\"" + tableEntityName + "." + entityName + "\" id=\"" + entityName + "\" class=\"form-control\" >\n");
        builder.append("                                <option></option>\n");
        for (String key : columnCommentMap.keySet()) {
            builder.append("                                <option value=\"" + key + "\">" + columnCommentMap.get(key) + "</option>\n");
        }
        builder.append("                            </select>");

        System.err.println(builder.toString());
        StringBuilder builder2 = new StringBuilder();
        builder2.append("\n\n                            <select name=\"" + tableEntityName + "." + entityName + "\" id=\"" + entityName + "\" class=\"form-control\" >\n");
        builder2.append("                                <option></option>\n");
        for (String key : columnCommentMap.keySet()) {
            builder2.append("                                <option th:selected=\"${" + tableEntityName + "." + entityName + " == " + key + "}\" value=\"" + key + "\">" + columnCommentMap.get(key) + "</option>\n");
        }
        builder2.append("                            </select>");

        System.err.println(builder2.toString());
    }

    private static void printSelectEnum(MyTableColumn tableColumn) {
        String comment = tableColumn.getComment();
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("firstUpColumnName", tableColumn.getFirstUpEntityName());
        paramsMap.put("columnName", tableColumn.getEntityName());

        LinkedHashMap<String, String> columnCommentMap = getColumnCommentMap(comment);
        paramsMap.put("columnCommentMap", columnCommentMap);

        TemplateUtils.printTemplate(paramsMap, "enums.ftl");
    }

    private static LinkedHashMap<String, String> getColumnCommentMap(String comment) {
        String enumsStr = comment.substring(comment.indexOf("[") + 1, comment.indexOf("]"));
        String[] split = enumsStr.split(",");
        LinkedHashMap<String, String> columnCommentMap = new LinkedHashMap<>();
        for (int i = 0; i < split.length; i++) {
            String[] note = split[i].split(":");
            columnCommentMap.put(note[0], note[1]);
        }
        return columnCommentMap;
    }


    private static void createHtmlCode(String tableEntityName, String firstUpTableName, List<MyTableColumn> columns) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("tableEntityName", tableEntityName);
        paramsMap.put("firstUpTableName", firstUpTableName);
        List<String> columnCommentList = columns.stream().map(MyTableColumn::getComment).collect(Collectors.toList());
        paramsMap.put("columnCommentList", columnCommentList);
        List<String> columnList = columns.stream().map(MyTableColumn::getEntityName).collect(Collectors.toList());
        paramsMap.put("columnList", columnList);
        LinkedHashMap<Object, Object> columnMap = new LinkedHashMap<>();
        for (MyTableColumn tableColumn : columns) {
            columnMap.put(tableColumn.getEntityName(), tableColumn.getComment());
        }
        paramsMap.put("columnMap", columnMap);

        File parentFile = new File(projectRoot + targetResources + html + tableEntityName);
        if (!parentFile.exists()) {
            parentFile.mkdir();
        }

        TemplateUtils.createQueryHtml(paramsMap, parentFile, tableEntityName);
        TemplateUtils.createAddHtml(paramsMap, parentFile, tableEntityName);
        TemplateUtils.createUpdateHtml(paramsMap, parentFile, tableEntityName);
    }


    private static void createJavaCode(String tableEntityName, String firstUpTableName) {

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("tableEntityName", tableEntityName);
        paramsMap.put("firstUpTableName", firstUpTableName);

        TemplateUtils.createControllerCode(paramsMap, firstUpTableName);
        TemplateUtils.createServiceCode(paramsMap, firstUpTableName);
        TemplateUtils.createServiceImplCode(paramsMap, firstUpTableName);
    }


    //获取表字段
    public static List<MyTableColumn> getColumnByTableName(String tableName) {
        RowSet rowSet = JDBCUtils.executeQuery(sqlTableColumn, tableName);
        List<MyTableColumn> tableColumns = new ArrayList<>();
        if (rowSet == null) {
            return tableColumns;
        }
        try {
            while (rowSet.next()) {
                String columnName = rowSet.getString("COLUMN_NAME");
                String dataType = rowSet.getString("DATA_TYPE");
                String comment = rowSet.getString("COLUMN_COMMENT");
                String columnKey = rowSet.getString("COLUMN_KEY");
                MyTableColumn column = new MyTableColumn();
                column.setColumnName(columnName);
                String entityName = getEntityName(columnName);
                column.setEntityName(entityName);
                String entityNameUp = getFirstUp(entityName);
                column.setFirstUpEntityName(entityNameUp);
                column.setDataType(dataType);
                column.setComment(comment);
                if (StringUtils.equals("PRI", columnKey)) {
                    column.setPriKey(true);
                }
                tableColumns.add(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableColumns;
    }

    private static String getFirstUp(String name) {
        return name.substring(0, 1).toUpperCase().concat(name.substring(1));
    }

    private static String getEntityName(String columnName) {
        if (StringUtils.isBlank(columnName)) {
            return "";
        }
        char[] chars = columnName.toCharArray();
        StringBuilder builder = new StringBuilder();
        boolean isUp = false;
        for (int i = 0; i < chars.length; i++) {
            String str = String.valueOf(chars[i]);
            if (StringUtils.equals(str, "_")) {
                isUp = true;
                continue;
            }
            if (isUp) {
                builder.append(str.toUpperCase());
                isUp = false;
            } else {
                builder.append(str);
            }
        }
        return builder.toString();

    }


    @Data
    public static class MyTableColumn {
        private String columnName;
        private String entityName;
        private String firstUpEntityName;
        private String dataType;
        private String comment;
        private boolean priKey;

    }

}
