package com.mb.dao;

import com.mb.enity.student;

import java.util.List;


public interface studentMapper {

    //查询学员总数量
    int countStudent();

    //    工资>900的学员
    int countSatisfiedSalary();

    //    根据职业名称搜索学员人数
    int count(String professionName);

    //查询所有学员  按工资排序
    List<student> allStudent();

       /*
    以下是测试缓存的代码
    1. FindAll查询所有
    2. AddStudent添加一个学生
     */

    List<student>  FindAll();

    int  AddStudent(student student);
}