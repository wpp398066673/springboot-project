package com.wpp.redis.dao;

import com.wpp.redis.bean.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheConfig;

@Mapper
//@CacheConfig(cacheNames = "student")
public interface StudentDao {
    @Delete("delete from student where sno = #{sno}")
    int deleteBySno(String sno);

    @Update("update student set sname = #{name} where sno = #{sno}")
    int update(Student student);

    @Select("select * from student where sno = #{sno}")
    @Results(id = "student", value = {
            @Result(property = "sno", column = "sno", javaType = String.class),
            @Result(property = "name", column = "sname", javaType = String.class),
            @Result(property = "sex", column = "ssex", javaType = String.class
    )})
    Student qureyStudentBySno(String sno);


}
