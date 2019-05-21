package com.ppwei.myhr.mapper;

import com.ppwei.myhr.bean.Menu;

import java.util.List;
public interface MenuMapper {

    List<Menu> getAllMenu();

    //通过登录人id获取，所有资源
    List<Menu> getMenusByHrId(Long hrId);

    List<Menu> menuTree();

    List<Long> getMenusByRid(Long rid);
}
