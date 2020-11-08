package com.example.yunxi.mybatis.demo.interceptor;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @Author : zhouguanya
 * @Project : java-interview-guide-part2
 * @Date : 2020-11-08 23:02
 * @Version : V1.0
 * @Description : 分页插件
 */
@Intercepts({
        @Signature(
                type = Executor.class,    // 目标类
                method = "query",    // 目标方法
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        )
})
public class MySqlPagingPlugin implements Interceptor {

    private static final Integer MAPPED_STATEMENT_INDEX = 0;
    private static final Integer PARAMETER_INDEX = 1;
    private static final Integer ROW_BOUNDS_INDEX = 2;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        RowBounds rb = (RowBounds) args[ROW_BOUNDS_INDEX];
        // 无需分页
        if (rb == RowBounds.DEFAULT) {
            return invocation.proceed();
        }

        // 将原 RowBounds 参数设为 RowBounds.DEFAULT，关闭 MyBatis 内置的分页机制
        args[ROW_BOUNDS_INDEX] = RowBounds.DEFAULT;

        MappedStatement ms = (MappedStatement) args[MAPPED_STATEMENT_INDEX];
        BoundSql boundSql = ms.getBoundSql(args[PARAMETER_INDEX]);

        // 获取 SQL 语句，拼接 limit 语句
        String sql = boundSql.getSql();
        String limit = String.format("LIMIT %d,%d", rb.getOffset(), rb.getLimit());
        sql = sql + " " + limit;

        // 创建一个 StaticSqlSource，并将拼接好的 sql 传入
        SqlSource sqlSource = new StaticSqlSource(ms.getConfiguration(), sql, boundSql.getParameterMappings());

        // 通过反射获取并设置 MappedStatement 的 sqlSource 字段
        Field field = MappedStatement.class.getDeclaredField("sqlSource");
        field.setAccessible(true);
        field.set(ms, sqlSource);

        // 执行被拦截方法
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
