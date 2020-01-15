package com.zhd.study.codeGenerator;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-04 14:38
 */
public class JDBCUtils {

    private static final String url = "jdbc:mysql://115.29.108.117:3306/zhd";
    private static final String root = "root";
    private static final String password = "123";

    public static List<Map<String, Object>> executeQuery2(String sql, Object... params) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            RowSet rowSet = executeQuery(sql, params);
            ResultSetMetaData metaData = rowSet.getMetaData();
            while (rowSet.next()) {
                Map<String, Object> resultMap = new HashMap<>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnName(i);
                    resultMap.put(columnName, rowSet.getObject(columnName));
                }
                list.add(resultMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static RowSet executeQuery(String sql, Object... params) {
        //获取连接
        //创建statement对象
        ResultSet rs = null;
        CachedRowSet rowSet = null;
        try (Connection con = DriverManager.getConnection(url, root, password);
             PreparedStatement statement = con.prepareStatement(sql)) {

            if (params != null) {
                for (int i = 1; i <= params.length; i++) {
                    statement.setObject(i, params[i - 1]);
                }
            }
            //发送并执行sql
            rs = statement.executeQuery();
            RowSetFactory rowSetFactory = RowSetProvider.newFactory();
            //创建指定的RowSet
            rowSet = rowSetFactory.createCachedRowSet();
            //将ResultSet放到RowSet中
            rowSet.populate(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6 释放资源
            close(rs);
        }
        return rowSet;
    }

    private static void close(AutoCloseable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
