package com.wpp.redis.controller;

import com.wpp.redis.bean.User;
import com.wpp.redis.service.RedisClusterDemo;
import com.wpp.redis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private RedisClusterDemo redisClusterDemo;

    @RequestMapping("/getUser")
    @Cacheable(value = "user-key")
    public User getUser() {
        User user = new User((long) 0, "aa@126.com", "aa", "aa123456", "aa", "123");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    @RequestMapping("/getStudent")
    public Object getStudent(String sno) {
        return studentService.qureyStudentBySno(sno);
    }

   /* @RequestMapping("/findRedis")
    public String findRedis(){
        return redisClusterDemo.findRedis();
    }*/
}
