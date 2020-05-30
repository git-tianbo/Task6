package com.mb.service;


import com.mb.enity.student;

import java.util.List;


public interface studentService  {

    //查询学员总数量
    int countStudent();

    //    工资>900的学员
    int countSatisfiedSalary();

    //    根据职业名称搜索学员人数
    int count(String professionName);

    //查询所有学员  并且按工资排序
    List<student> allStudent();


    //Mem缓存的三个实现类

    int MemCountStudent();

    int MemCountSatisfiedSalary();

    List<student> MemAllStudent();


    //Reids的三个实现类

    int  RedisCountStudent();

    int RedisCountSatisfiedSalary();

    List<student> RedisAllStudent();



    /*
    以下是Json缓存的代码
    1. FindAll查询所有
    2. AddStudent添加一个学生
     */

    //Mem有缓存的情况
    List<student>  FindAll();

    //没有缓存的情况
    List<student> NoMemFindAll();

    //Redis缓存
    List<student> RedisFindAll();

    int  AddStudent(student student);





}
