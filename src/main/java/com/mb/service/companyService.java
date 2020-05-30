package com.mb.service;

import com.mb.enity.company;

import java.util.List;

public interface companyService {

    //查询所有战略企业合作伙伴

    //不带缓存
    List<company> allCompany();
    //Mem带缓存
    List<company> MemAllCompany();
    //Reids缓存
    List<company> RedisAllCompany();

    //查询公司数量

    //不带缓存
    int countCompany();
    //Mem缓存
    int MemCountCompany();
    //Redis缓存
    int RedisCountCompany();

}
