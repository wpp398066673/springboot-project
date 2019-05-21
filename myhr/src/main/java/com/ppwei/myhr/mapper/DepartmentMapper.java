package com.ppwei.myhr.mapper;

import com.ppwei.myhr.bean.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {

    List<Department> getDepByPid(Long pid);

    List<Department> getAllDeps();

    void addDep(@Param("dep") Department department);

    void deleteDep(@Param("dep") Department department);

}
