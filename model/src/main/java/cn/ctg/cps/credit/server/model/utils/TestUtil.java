package cn.ctg.cps.credit.server.model.utils;

import com.alibaba.excel.EasyExcel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzheng
 * @date 2022年09月23日 11:27
 * @Description TODO
 */
public class TestUtil {

    public void testQuery() throws Exception {
        Connection connecter = DBRunnerUtils.connecter();
        DBRunnerUtils dbRunnerUtils = new DBRunnerUtils();
        String sql = "select * from COMPANY where NAME = ?";
        ResultSet resultSet = dbRunnerUtils.rsQuery(connecter, sql, new Object[]{"中国旅行社总社（上海）有限公司"});
        while (resultSet.next()){
            String name = resultSet.getString("name");
            String sex = resultSet.getString("company_id");
            System.out.println("name = " + name);
        }
    }

    public void testUpdate() throws Exception {
        Connection connecter = DBRunnerUtils.connecter();
        DBRunnerUtils dbRunnerUtils = new DBRunnerUtils();
        String sql = "update t_member set name = 'liuzheng'  where idCard = '234234145432121345'";
        List list = new ArrayList<>();
        list.add(sql);
        dbRunnerUtils.batchWithoutCommit(connecter,list,true);

    }

    public void testImportExcel(){
        String fileName = "D:\\data\\code1.xlsx";
        // 这里 只要，然后读取第一个sheet 同步读取会自动finish
        EasyExcel.read(fileName, new NoModelDataListener()).sheet().doRead();
    }
    public static void main(String[] args) throws Exception {
        TestUtil testUtil = new TestUtil();
        testUtil.testImportExcel();
    }
}
