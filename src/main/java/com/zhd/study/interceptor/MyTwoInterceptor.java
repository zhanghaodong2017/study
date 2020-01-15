package com.zhd.study.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-13 19:39
 */
@Intercepts({@Signature(
        type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
),
        @Signature(
                type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class}
        ),
        @Signature(
                type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class}
        ),
        @Signature(
                type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}
        )})
public class MyTwoInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        System.out.println("2" + method.getName());
        Object target = invocation.getTarget();
        if (target instanceof Executor) {
            Executor executor = (Executor) target;
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            Object parameter = null;
            if (invocation.getArgs().length > 1) {
                parameter = invocation.getArgs()[1];
            }
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            String sql = boundSql.getSql();
            System.err.println(sql);
        }
        System.out.println(target.getClass());
        if (invocation.getArgs() != null) {
            for (int i = 0; i < invocation.getArgs().length; i++) {
                if (invocation.getArgs()[i] != null) {
                    System.out.println(invocation.getArgs()[i].getClass());
                }
            }
        }
        return invocation.proceed();
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);

    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println(JSON.toJSONString(properties));

    }
}
