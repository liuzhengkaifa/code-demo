package cn.ctg.cps.credit.server.model.utils;


import com.alibaba.excel.EasyExcel;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;

/**
 * @author liuzheng
 * @date 2022年09月23日 9:40
 * @Description 时间工具类
 */
public class DateCommonUtils {

    public static void mai2n(String[] args) {
        //1.获取当前日期
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        System.out.println("LocalDateTime now = " + localDateTimeNow);

        LocalDate now1 = LocalDate.now();
        System.out.println("LocalDate now = " + now1);

        Date date = new Date();
        System.out.println("Date now = " + date);


        Calendar instance = Calendar.getInstance();
        System.out.println("instance = " + instance);

        long time = System.currentTimeMillis();
        System.out.println("time = " + time);

        long time1 = instance.getTime().getTime();
        System.out.println("time1 = " + time1);

        Timestamp timestamp = new Timestamp(time);
        Timestamp timestamp2 = new Timestamp(time1);
        System.out.println("timestamp = " + timestamp);


        //2.日期格式化
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        int i = timestamp.compareTo(timestamp2);
        long time2 = timestamp.getTime();
        int i1 = timestamp.compareTo(new Date());


        /**
         * adjustInto 方法
         * 把指定的值赋值给传入的对象，使其拥有相同的时间。
         * passTemporal : 2022-09-24T10:19:19.071639400
         * adjustInto   : 2022-09-24T23:59:59
         */
        LocalTime local = LocalTime.parse("23:59:59");

        LocalDateTime passTemporal = LocalDateTime.now().plusDays(1L);

        System.out.println("passTemporal : " + passTemporal);

        Temporal returnTemporal = local.adjustInto(passTemporal);

        LocalDateTime from = LocalDateTime.from(returnTemporal);
        System.out.println("adjustInto = " + from);

        LocalDateTime of = LocalDateTime.of(2022, 9, 23, 10, 24);
        System.out.println("of = " + of);
    }


    public static void main(String[] args) {
        String fileName = "D:\\data\\code.xlsx";
        // 这里 只要，然后读取第一个sheet 同步读取会自动finish
        EasyExcel.read(fileName, new NoModelDataListener()).sheet().doRead();
    }










}
