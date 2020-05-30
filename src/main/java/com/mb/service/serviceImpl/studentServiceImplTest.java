package com.mb.service.serviceImpl;

import com.danga.MemCached.MemCachedClient;
import com.mb.dao.studentMapper;
import com.mb.enity.student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLOutput;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class studentServiceImplTest {


    @Autowired
    studentServiceImpl s;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    studentMapper studentMapper;

    @Test
    public void xx() {

        Integer test = (Integer) redisTemplate.opsForValue().get("aaa");
        System.out.println("结果"+test);

        if (test!= null ) {
            System.out.println("有缓存"+test);

        } else {

            System.out.println("没没");
            int testa= studentMapper.countStudent();
            System.out.println("查询结果");

            try {
                redisTemplate.opsForValue().set("aaa", testa);
                System.out.println("放入成功");
            } catch (Exception e) {
                System.out.println("放入失败" + e.toString());
            }
        }
    }

//        redisTemplate.opsForValue().set("aaa","前期去");
//
//        String a = (String) redisTemplate.opsForValue().get("aaa");
//
//        System.out.println(a);

}

