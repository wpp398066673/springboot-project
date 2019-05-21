package com.ppwei.myhr.mapper;

import com.ppwei.myhr.bean.Hr;
import com.ppwei.myhr.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrMapper {

    Hr loadUserByUsername(String username);

    List<Role> getRolesByHrId(String id);

    List<Hr> getAllHr(@Param("currentId") Long currentId);
}
