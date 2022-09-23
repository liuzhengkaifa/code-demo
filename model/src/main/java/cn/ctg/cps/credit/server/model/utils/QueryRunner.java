package cn.ctg.cps.credit.server.model.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author liuzheng
 * @date 2022年09月23日 14:39
 * @Description TODO
 */
public class QueryRunner {

    protected PreparedStatement prepareStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    protected void fillStatement(PreparedStatement stmt, Object[] params) throws SQLException {
        if (params != null) {
            for(int i = 0; i < params.length; ++i) {
                if (params[i] != null) {
                    stmt.setObject(i + 1, params[i]);
                } else {
                    stmt.setNull(i + 1, 1111);
                }
            }

        }
    }

    protected void rethrow(SQLException cause, String sql, Object[] params) throws SQLException {
        StringBuffer msg = new StringBuffer(cause.getMessage());
        msg.append(" Query: ");
        msg.append(sql);
        msg.append(" Parameters: ");
        if (params == null) {
            msg.append("[]");
        } else {
            msg.append(Arrays.asList(params));
        }

        SQLException e = new SQLException(msg.toString());
        e.setNextException(cause);
        throw e;
    }

}
