package com.ppwei.myhr.controller;

import com.ppwei.myhr.bean.Menu;
import com.ppwei.myhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/sysmenu")
    public List<Menu> sysmenu(){
        return menuService.getMenusByHrId();
    }
}
