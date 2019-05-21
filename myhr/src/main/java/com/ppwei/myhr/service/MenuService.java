package com.ppwei.myhr.service;

import com.ppwei.myhr.bean.Menu;
import com.ppwei.myhr.common.HrUtils;
import com.ppwei.myhr.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> getAllMenu(){
        return menuMapper.getAllMenu();
    }

    public List<Menu> getMenusByHrId(){
       return menuMapper.getMenusByHrId(HrUtils.getCurrentHr().getId());
    }

    public List<Menu> menuTree() {
        return menuMapper.menuTree();
    }

    public List<Long> getMenusByRid(Long rid) {
        return menuMapper.getMenusByRid(rid);
    }
}
