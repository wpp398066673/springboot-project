package com.ppwei.myhr.service;

import com.ppwei.myhr.bean.Department;
import com.ppwei.myhr.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> getDepByPid(Long pid){
        return departmentMapper.getDepByPid(pid);
    }

    public List<Department> getAllDeps(){
        return departmentMapper.getAllDeps();
    }

    public int addDep(Department department){
        department.setEnabled(true);
        departmentMapper.addDep(department);
        return department.getResult();

    }

    public int deleteDep(Long did){
        Department department = new Department();
        department.setId(did);
        departmentMapper.deleteDep(department);
        return department.getResult();
    }
}
