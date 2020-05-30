package com.mb.controller;

import com.danga.MemCached.MemCachedClient;
import com.mb.enity.student;
import com.mb.service.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

    @Controller
        public class MemcachedController {
            @Autowired
            studentService studentService;

            //Mem缓存返回json
            @ResponseBody
            @RequestMapping(value = "/memFindTest",method = RequestMethod.GET)
            public List<student> memFindTest() {

                List<student> list = studentService.FindAll();
                return list;
            }


            //无缓存返回json
            @ResponseBody
            @RequestMapping(value = "/NomemFindTest",method = RequestMethod.GET)
            public List<student> NoMemFindTest() {

                List<student> list =  studentService.NoMemFindAll();
                return  list;
            }


        //无缓存返回json
        @ResponseBody
        @RequestMapping(value = "/RedisFindTest",method = RequestMethod.GET)
        public List<student> RedisFindTest() {

            List<student> list =  studentService.RedisFindAll();
            return  list;
        }


            @ResponseBody
            @RequestMapping(value = "/add",method = RequestMethod.POST)
        public int add(student student){

                int a = studentService.AddStudent(student);
                return  a;
            }


}