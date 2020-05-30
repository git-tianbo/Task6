package com.mb.controller;


import com.mb.enity.company;
import com.mb.enity.student;
import com.mb.service.companyService;
import com.mb.service.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class homePageController {

    @Autowired
    studentService studentService;
    @Autowired
    companyService companyService;

    @RequestMapping(value = "/u/homePage", method = RequestMethod.GET)
    public String allStudent(Model model) {

        try {

            List<student> students = studentService.allStudent();
            List<company> company = companyService.allCompany();
            int countStudent = studentService.countStudent();
            int countCompany = companyService.countCompany();
            int countSatisfiedSalary = studentService.countSatisfiedSalary();

            model.addAttribute("students", students);
            model.addAttribute("company", company);
            model.addAttribute("countStudent", countStudent);
            model.addAttribute("countCompany", countCompany);
            model.addAttribute("countSatisfiedSalary", countSatisfiedSalary);
            return "homePage";

        } catch (Exception e) {
            return "index";
        }
    }

    //带缓存
    @RequestMapping(value = "/MemhomePage", method = RequestMethod.GET)
    public String MemallStudent(Model model) {

        try {
            List<student> students = studentService.MemAllStudent();
            List<company> company = companyService.MemAllCompany();
            int countStudent = studentService.MemCountStudent();
            int countCompany = companyService.MemCountCompany();
            int countSatisfiedSalary = studentService.MemCountSatisfiedSalary();

            model.addAttribute("students", students);
            model.addAttribute("company", company);
            model.addAttribute("countStudent", countStudent);
            model.addAttribute("countCompany", countCompany);
            model.addAttribute("countSatisfiedSalary", countSatisfiedSalary);
            return "homePage";

        } catch (Exception e) {
            return "index";
        }
    }



    //带缓存
    @RequestMapping(value = "/RedishomePage", method = RequestMethod.GET)
    public String RedisallStudent(Model model) {

        try {
            List<student> students = studentService.RedisAllStudent();
            List<company> company = companyService.RedisAllCompany();
            int countStudent = studentService.RedisCountStudent();
            int countCompany = companyService.RedisCountCompany();
            int countSatisfiedSalary = studentService.RedisCountSatisfiedSalary();

            model.addAttribute("students", students);
            model.addAttribute("company", company);
            model.addAttribute("countStudent", countStudent);
            model.addAttribute("countCompany", countCompany);
            model.addAttribute("countSatisfiedSalary", countSatisfiedSalary);
            return "homePage";

        } catch (Exception e) {
            return "index";
        }
    }





    //推荐页面 返回时间
    @RequestMapping(value = "/testA", method = RequestMethod.GET)
    public String testA(Model model) {

        long time = System.currentTimeMillis();
        //设置格式
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeText=format.format(time);
        System.out.println(timeText);

        model.addAttribute("time",timeText);
        return "recommend";
    }

//推荐页面 没有时间
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        return "recommend";
    }
        }
