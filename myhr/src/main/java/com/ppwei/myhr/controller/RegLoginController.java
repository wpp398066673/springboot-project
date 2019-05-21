package com.ppwei.myhr.controller;

import com.ppwei.myhr.bean.RespBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegLoginController {

    @RequestMapping("/login_p")
    public RespBean login(){
        return RespBean.error("尚未登录，请登录!");
    }
}
