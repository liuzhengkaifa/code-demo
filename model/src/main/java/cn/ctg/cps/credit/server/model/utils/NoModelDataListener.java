package cn.ctg.cps.credit.server.model.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liuzheng
 * @date 2022年09月23日 10:40
 * @Description TODO
 */
@Slf4j
public class NoModelDataListener extends AnalysisEventListener<Map<Integer, String>> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    private List<Map<Integer, String>> cachedDataList = new ArrayList<>(BATCH_COUNT);

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        cachedDataList.add(data);
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            cachedDataList = new ArrayList<>(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库 此处待编辑
     */
    private void saveData() {
        List sqlList = new ArrayList<>();
        cachedDataList.forEach(data -> {
            String sql = "INSERT INTO CITSONLINE.ACCOUNT_GROUP_LIMIT (ACCOUNT, LATEST_GROUP_DATE, ADD_USER, ADD_DATE, UPD_USER, UPD_DATE) VALUES ('%s', TO_TIMESTAMP('2022-09-22 13:53:28.000000', 'YYYY-MM-DD HH24:MI:SS.FF6'), 'pancw', TO_TIMESTAMP('2022-09-22 13:53:37.000000', 'YYYY-MM-DD HH24:MI:SS.FF6'), null, null)";
            String account = data.get(0);
            String closteFlag = data.get(4);
            if(!"不关".equals(closteFlag)){
                sql = String.format(sql,account);
                sqlList.add(sql);
            }
        });

        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        log.info("待处理SQL数量 {}，SQL {}",sqlList.size(),JSON.toJSONString(sqlList));
        try {
            Connection connecter = DBRunnerUtils.connecter();
            DBRunnerUtils dbRunnerUtils = new DBRunnerUtils();
            dbRunnerUtils.batchWithoutCommit(connecter,sqlList,true);
        }catch (Exception e){
            log.error("更新失败",e);
        }
        log.info("存储数据库成功！");
    }

}
