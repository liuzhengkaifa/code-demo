package cn.ctg.cps.credit.server.model.utils;

import cn.ctg.cps.credit.server.model.utils.back.DbUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * @author liuzheng
 * @date 2022年09月23日 11:06
 * @Description TODO
 */
@Slf4j
public class DBRunnerUtils extends QueryRunner {

    public static  Connection connecter() throws Exception, SQLException, ClassNotFoundException  {
        //阿里云数据库
        /*    Properties props = new Properties();
            String url = "jdbc:mysql://101.132.140.20:3306/health?serverTimezone=Asia/Shanghai&useSSL=false";
            DbUtils.loadDriver("com.mysql.jdbc.Driver");
            props.put("user", "root");
            props.put("password","xx");*/

        //测试库
            Properties props = new Properties();
            String url = "jdbc:oracle:thin:@xx.xx.xxx.xx:1521:racdev";
            DbUtils.loadDriver("oracle.jdbc.OracleDriver");
            props.put("user", "citsonline");
            props.put("password","xx");

        Connection con = DriverManager.getConnection(url,props);
        return con;
    }

    /**
     * @param sqlList 需要执行的sql问的list  logerror:true/false是否打印sql
     * @return The number of rows updated per statement.
     * @throws SQLException
     */
    public int[] batchWithoutCommit(Connection conn, List sqlList, boolean logerror) throws SQLException {
        if(sqlList == null || sqlList.size() <=0) {
            return null;
        }
        int[] rows = null;
        Statement stmt     = null;
        try {
            stmt = conn.createStatement();
            for (int i = 0; i < sqlList.size(); i++) {
                if(logerror) {
                    log.debug(sqlList.get(i).toString());
                }
                if(!"".equals(sqlList.get(i).toString())){
                    stmt.addBatch(sqlList.get(i).toString());
                }
            }
            rows = stmt.executeBatch();
        } catch (SQLException e) {
            if(logerror) {
                log.debug(String.format("batch excute error, SQL: [{}]", JSON.toJSONString(sqlList)), e);
            }
            throw(e);
        } finally {
            DbUtils.closeQuietly(stmt);
            DbUtils.closeQuietly(conn);
        }
        return rows;
    }

    /**
     *
     * @param conn
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public ResultSet rsQuery(Connection conn, String sql, Object[] params)
            throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = this.prepareStatement(conn, sql);
            this.fillStatement(stmt, params);

            rs = stmt.executeQuery();

        } catch (SQLException e) {
            this.rethrow(e, sql, params);

        } finally {

        }
        return rs;
    }
}
