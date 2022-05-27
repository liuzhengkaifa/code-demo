package cn.ctg.cps.credit.server;

import cn.ctg.boot.base.common.annotation.EnableBase;
import cn.ctg.cloud.base.common.setting.web.StartApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 主启动类
 * @author auto
 */
@SpringBootApplication
@EnableBase
@EnableFeignClients
@EnableDiscoveryClient
public class CREDITApplication {
    public static void main(String[] args) {
        StartApplication.start(CREDITApplication.class, args);
    }
}