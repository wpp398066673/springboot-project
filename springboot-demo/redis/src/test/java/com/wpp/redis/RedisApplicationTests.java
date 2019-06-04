package com.wpp.redis;

import com.wpp.redis.bean.Student;
import com.wpp.redis.dao.StudentDao;
import com.wpp.redis.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private JedisCluster jedisCluster;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test1() throws Exception {
        Student student1 = this.studentService.qureyStudentBySno("001");
        System.out.println("学号" + student1.getSno() + "的学生姓名为：" + student1.getName());

        Student student2 = this.studentService.qureyStudentBySno("001");
        System.out.println("学号" + student2.getSno() + "的学生姓名为：" + student2.getName());
    }

    @Test
    public void test2() throws Exception {
        Student student1 = this.studentService.qureyStudentBySno("001");
        System.out.println("学号" + student1.getSno() + "的学生姓名为：" + student1.getName());
        student1.setName("jack");
        this.studentService.update(student1);
        System.out.println("学号" + student1.getSno() + "的学生姓名为：" + student1.getName());
        //System.out.println("学号" + student2.getSno() + "的学生姓名为：" + student2.getName());
    }

    @Test
    public void test3() throws Exception {
        Student student1 = this.studentService.qureyStudentBySno("001");
        System.out.println("学号" + student1.getSno() + "的学生姓名为：" + student1.getName());

        this.studentService.delete("001");

        Student student2 = this.studentService.qureyStudentBySno("001");
        System.out.println("学号" + student2.getSno() + "的学生姓名为：" + student2.getName());

    }
    /*
    * redis集群测试
    * */
    @Test
    public void test4() throws Exception {

        this.jedisCluster.set("hubei","wuhan");

    }

}
