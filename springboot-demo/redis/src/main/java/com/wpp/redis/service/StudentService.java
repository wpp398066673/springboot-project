package com.wpp.redis.service;

import com.wpp.redis.bean.Student;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "student")
public interface StudentService {
    @CacheEvict(key="#p0")
    int delete(String sno);

    @CachePut(key= "#p0.sno")
    Student update(Student student);

    @Cacheable(key = "#p0")
    Student qureyStudentBySno(String sno);

}
