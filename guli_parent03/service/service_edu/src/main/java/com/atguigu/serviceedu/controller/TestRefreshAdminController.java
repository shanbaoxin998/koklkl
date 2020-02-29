package com.atguigu.serviceedu.controller;

import com.atguigu.commonutils.R;
import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhangjun
 * @create 2020-02-29 15:17
 */
@RestController
@RequestMapping("/admin/edu/testRefresh")
@Api(description = "mq动态刷新测试")
@CrossOrigin //跨域
@RefreshScope
public class TestRefreshAdminController {

    //手动刷新：http://localhost:8110/actuator/bus-refresh
    //接口访问：http://localhost:8110//admin/edu/testRefresh
    @Value("${config.name}")
    private String configName;

    @GetMapping
    public R index() {
        return R.ok().data("configName", configName);
    }
}
