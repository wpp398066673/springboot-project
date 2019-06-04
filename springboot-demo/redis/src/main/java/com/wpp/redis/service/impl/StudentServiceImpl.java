package com.wpp.redis.service.impl;

import com.wpp.redis.bean.Student;
import com.wpp.redis.dao.StudentDao;
import com.wpp.redis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//@Repository("studentService")
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public int delete(String sno) {
        return studentDao.deleteBySno(sno);
    }

    @Override
    public Student update(Student student) {
        studentDao.update(student);
        return student;
    }



    @Override
    public Student qureyStudentBySno(String sno) {
        this.redisTemplate.opsForValue().set("test1","test1");
        System.out.println(this.redisTemplate.opsForValue().get("test1"));
        return studentDao.qureyStudentBySno(sno);
    }
}
