package cn.ctg.cps.credit.server.model.utils.back;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author liuzheng
 * @date 2022年09月23日 11:09
 * @Description DbUtils
 */
public final class DbUtils {
    public DbUtils() {
    }

    public static void close(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }

    }

    public static void close(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }

    }

    public static void close(Statement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }

    }

    public static void closeQuietly(Connection conn) {
        try {
            close(conn);
        } catch (SQLException var2) {
        }

    }

    public static void closeQuietly(Connection conn, Statement stmt, ResultSet rs) {
        closeQuietly(rs);
        closeQuietly(stmt);
        closeQuietly(conn);
    }

    public static void closeQuietly(ResultSet rs) {
        try {
            close(rs);
        } catch (SQLException var2) {
        }

    }

    public static void closeQuietly(Statement stmt) {
        try {
            close(stmt);
        } catch (SQLException var2) {
        }

    }

    public static void commitAndClose(Connection conn) throws SQLException {
        if (conn != null) {
            conn.commit();
            conn.close();
        }

    }

    public static void commitAndCloseQuietly(Connection conn) {
        try {
            commitAndClose(conn);
        } catch (SQLException var2) {
        }

    }

    public static boolean loadDriver(String driverClassName) {
        try {
            Class.forName(driverClassName).newInstance();
            return true;
        } catch (ClassNotFoundException var2) {
            return false;
        } catch (IllegalAccessException var3) {
            return true;
        } catch (InstantiationException var4) {
            return false;
        } catch (Throwable var5) {
            return false;
        }
    }

    public static void printStackTrace(SQLException sqle) {
        printStackTrace(sqle, new PrintWriter(System.err));
    }

    public static void printStackTrace(SQLException sqle, PrintWriter pw) {
        SQLException next = sqle;

        while(next != null) {
            next.printStackTrace(pw);
            next = next.getNextException();
            if (next != null) {
                pw.println("Next SQLException:");
            }
        }

    }

    public static void printWarnings(Connection connection) {
        printWarnings(connection, new PrintWriter(System.err));
    }

    public static void printWarnings(Connection conn, PrintWriter pw) {
        if (conn != null) {
            try {
                printStackTrace(conn.getWarnings(), pw);
            } catch (SQLException var3) {
                printStackTrace(var3, pw);
            }
        }

    }

    public static void rollback(Connection conn) throws SQLException {
        if (conn != null) {
            conn.rollback();
        }

    }
}
