package com.mb.service.serviceImpl;


import com.danga.MemCached.MemCachedClient;
import com.mb.dao.studentMapper;
import com.mb.enity.student;
import com.mb.service.studentService;
import javassist.bytecode.stackmap.BasicBlock;
import org.apache.commons.collections.BoundedCollection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class studentServiceImpl implements studentService {

    Logger logger = Logger.getLogger(studentServiceImpl.class);

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    studentMapper studentMapper;

    @Autowired
    MemCachedClient client;

//============================================统计所有学员人数  1.不带缓存 2.Mem缓存 3.Redis缓存====================


    //不带缓存的countStudent
    @Override
    public int countStudent() { return studentMapper.countStudent(); }

    //带缓存的countStudent
    @Override
    public int MemCountStudent() {

        Integer rs = (Integer) client.get("MemCountStudent");

        if (rs!=null){
            logger.info("有缓存，缓存为" + rs + "现在开始返回缓存");
            return rs;

        }else{
            //如果缓存为空   返回缓存数据
            logger.info("没有缓存,现在从数据库查询数据");
            int rss = studentMapper.countStudent();
            logger.info("从数据库中查询处的数据为" + rss);

            try {
                logger.info("现在开始把数据放入缓存");
                boolean state = client.set("MemCountStudent", rss);
                logger.info("添加新缓存是否成功" + state);
                return rss;
            }catch (Exception e){
                logger.info(e.toString());
                return rss;
            }
        }
    }




    @Override
    public int RedisCountStudent() {

        Integer rs =(Integer) redisTemplate.opsForValue().get("RedisCountStudent");

        if(rs!= null){

            logger.info("有缓存，缓存为" + rs + "现在开始返回缓存");
            return rs;

        }else{

            //如果缓存为空   返回缓存数据
            logger.info("没有缓存,现在从数据库查询数据");
            int rss = studentMapper.countStudent();

            logger.info("从数据库中查询处的数据为" + rss);

            try {
                logger.info("现在开始把数据放入缓存");
                redisTemplate.opsForValue().set("RedisCountStudent",rss);
                logger.info("放入缓存成功");
            }catch (Exception e){
                logger.info(e.toString());
                logger.info("放入缓存失败！");
            }
            return rss;
        }
        }


//        redisTemplate.opsForValue().set("aaa","前期去");
//
//        String a = (String) redisTemplate.opsForValue().get("aaa");


//===============================================统计工资满意学员人数  1.不带缓存 2.Mem缓存 3.Redis缓存====================


    //不带缓存
    @Override
    public int countSatisfiedSalary() {
        return studentMapper.countSatisfiedSalary();
    }

    //带缓存
    @Override
    public int MemCountSatisfiedSalary() {

        Integer rs = (Integer) client.get("MemCountSalary");

        if (rs != null) {

            logger.info("有缓存，缓存为"+rs+"现在开始返回缓存");
            return rs;

        }else {

            //如果缓存为空   返回缓存数据
            logger.info("没有缓存,现在从数据库查询数据");
            int rss = studentMapper.countSatisfiedSalary();
            logger.info("从数据库中查询处的数据为" + rss);

            try {
                logger.info("现在开始把数据放入缓存");
                boolean state = client.set("MemCountSalary", rss);
                logger.info("添加新缓存是否成功" + state);
                return rss;
            }catch (Exception e){
                logger.info(e.toString());
                return rss;
            }
        }
    }


    @Override
    public int RedisCountSatisfiedSalary() {
        Integer rs =(Integer) redisTemplate.opsForValue().get("RedisCountSalary");

        if(rs!= null){

            logger.info("有缓存，缓存为" + rs + "现在开始返回缓存");
            return rs;

        }else{

            //如果缓存为空   返回缓存数据
            logger.info("没有缓存,现在从数据库查询数据");
            int rss = studentMapper.countSatisfiedSalary();

            logger.info("从数据库中查询处的数据为" + rss);

            try {
                logger.info("现在开始把数据放入缓存");
                redisTemplate.opsForValue().set("RedisCountSalary",rss);
                logger.info("放入缓存成功");

            }catch (Exception e){
                logger.info(e.toString());
                logger.info("放入缓存失败！");
            }
            return rss;
        }
    }



//===============================================统计所有学员  1.不带缓存 2.Mem缓存 3.Redis缓存====================

    //不带缓存
    @Override
    public List<student> allStudent() {
        return studentMapper.allStudent();
    }


    //带缓存
    @Override
    public List<student> MemAllStudent() {

        //取出所有学生的缓存
        List<student> student = (List<student>) client.get("Memstudent");
        logger.info("查询出的缓存值为" + student);

        if (student != null) {
            logger.info("返回缓存");
            return student;

        } else {

            logger.info("缓存为空，现在从数据库查询数据");
            List<student> students = studentMapper.allStudent();
            logger.info("从数据库中查询处的数据为" + students);

            try {
                logger.info("把数据放入缓存");
                boolean state = client.set("Memstudent", students);
                logger.info("添加新缓存是否成功" + state);
            }catch (Exception e){
                e.printStackTrace();
            }
            return students;
        }
    }


    @Override
    public List<student> RedisAllStudent() {

        List<student> student = (List<student>)redisTemplate.opsForValue().get("RedisStudent");
        logger.info("查询出的缓存值为" + student);

        if (student != null && student.size()>0) {
            logger.info("返回缓存");
            return student;

        } else {

            logger.info("缓存为空，现在从数据库查询数据");
            List<student> students = studentMapper.allStudent();
            logger.info("从数据库中查询处的数据为" + students);

            try {
                logger.info("把数据放入缓存");
                redisTemplate.opsForValue().set("RedisStudent",students);
                logger.info("添加缓存成功");
            }catch (Exception e){
                logger.info(e.toString());
                logger.info("放入缓存失败！");
            }
            return students;
        }
    }

//=============================统计某个发展方向的人数===================================


    @Override
    public int count(String professionName) {
        return studentMapper.count(professionName);
    }


//==============================Json返回数据的代码 1. =======================================


    /**
      * @Author: TianBo
      * @Description:   查询所有学员 ，有缓存取缓存  没缓存新增一个缓存
      * @Date: 2020/5/23
      * @return: java.util.List<com.mb.enity.student>
      **/

    @Override
    public List<student> FindAll() {
        //取出所有学生的缓存
        List<student> rs = (List<student>) client.get("JsonMem");
        logger.info("查询出的缓存值为" + rs);
        //如果缓存为空   返回缓存数据
        if (rs != null) {
            logger.info("缓存值不为空，现在返回缓存");
            return rs;
        } else {
            //如果没有该条缓存，则从数据库里进行查询   再添加新缓存
            logger.info("缓存为空，现在从数据库查询数据");
            List<student> rss = studentMapper.FindAll();
            logger.info("从数据库中查询处的数据为" + rss);
            //把从数据库查出的的数据放进缓存
            try {
                boolean state = client.set("JsonMem", rss);
                logger.info("添加新缓存的key为:AllStudent ========= value为" + rss);
                logger.info("是否已经进缓存"+state);

            } catch (Exception e) {
                logger.info("添加新缓存失败");
                e.printStackTrace();
            }
            return rss;
        }
    }

    /**
      * @Author: TianBo
      * @Description: 查询所有学员  不填加缓存
      * @Date: 2020/5/23
      * @return: java.util.List<com.mb.enity.student>
      **/

    @Override
    public List<student> NoMemFindAll() {

        logger.info("现在从数据库查询数据");
        List<student> rss = studentMapper.FindAll();
        logger.info("从数据库中查询处的数据为" + rss);
        return rss;
    }

    @Override
    public List<student> RedisFindAll() {
        //取出所有学生的缓存
        List<student> rs = (List<student>) redisTemplate.opsForValue().get("JsonRedis");
        logger.info("查询出的缓存值为" + rs);
        //如果缓存为空   返回缓存数据
        if (rs != null) {
            logger.info("缓存值不为空，现在返回缓存");
            return rs;
        } else {
            //如果没有该条缓存，则从数据库里进行查询   再添加新缓存
            logger.info("缓存为空，现在从数据库查询数据");
            List<student> rss = studentMapper.FindAll();
            logger.info("从数据库中查询处的数据为" + rss);
            //把从数据库查出的的数据放进缓存
            try {
                redisTemplate.opsForValue().set("JsonRedis",rss);
                logger.info("添加新缓存的key为:AllStudent ========= value为" + rss);
            } catch (Exception e) {
                logger.info("添加新缓存失败");
                e.printStackTrace();
            }
            return rss;
        }
    }

    /**
      * @Author: TianBo
      * @Description:  插入一条新数据，并且更新缓存
      * @Date: 2020/5/23
      * @Param student:
      * @return: int
      **/

    @Override
    public int AddStudent(student student) {
        logger.info("添加的数据为" + student);
        //添加数据
        studentMapper.AddStudent(student);
        //刷新缓存

        //取出增加所有学员后所有学生的缓存
        List<student> rs = (List<student>) client.get("AllStudent");
        logger.info("查看现有缓存值：" + rs);

        //如果缓存不为空  更改缓存数据
        if (rs != null) {
            logger.info("缓存值不为空");

            //更改缓存数据
            try {
                logger.info("开始更新缓存");
                boolean state = client.replace("AllStudent", rs);
                logger.info("更新缓存结果+"+state );
            } catch (Exception e) {
                logger.info("更改缓存值失败"+e.toString());
                e.printStackTrace();
            }
        }else{
            try {
                logger.info("开始新增缓存");
                boolean state = client.set("AllStudent",rs);
                logger.info("新增缓存结果+"+state );
            } catch (Exception e) {
                logger.info("更改缓存值失败"+e.toString());
                e.printStackTrace();
            }
        }
        //返回刚刚插入用户的id
        return student.getId();
    }
}




//    @Override
//    public boolean PutStudent(student student) {
//
//        //需要更新的数据
//        logger.info("更改的数据为" + student);
//        //更新DB
//        boolean state = studentMapper.PutStudent(student);
//        //更新DB成功
//        if(state = true) {
//            logger.info("更改DB成功！");
//            //删除缓存
//            boolean Delstate = client.delete("AllStudent");
//          if(Delstate=true){
//              logger.info("删除缓存成功！");
//              return true;
//          }else{
//              //虽然删除缓存失败，但还是要返回正确的更改DB结果
//              logger.info("删除缓存失败！");
//              return true;
//          }
//        }else {
//            logger.info("更改DB失败！");
//            return false;
//        }
//}