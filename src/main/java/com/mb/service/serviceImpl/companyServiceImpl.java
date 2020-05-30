package com.mb.service.serviceImpl;

import com.danga.MemCached.MemCachedClient;
import com.mb.dao.companyMapper;
import com.mb.enity.company;
import com.mb.service.companyService;
import jdk.nashorn.internal.ir.CatchNode;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class companyServiceImpl implements companyService {

    Logger logger =Logger.getLogger(companyServiceImpl.class);

    @Autowired
    companyMapper companyMapper;

    @Autowired
    MemCachedClient client;

    @Autowired
    RedisTemplate redisTemplate;


//==============  统计所有公司  1.不带缓存 2.Mem缓存 3.Redis缓存=================
    @Override
    public List<company> allCompany() {
        return companyMapper.allCompany();
    }

    //带缓存
    @Override
    public List<company> MemAllCompany(){

        //取出所有学生的缓存
        List<company> company = (List<company>) client.get("MemCompany");
        logger.info("查询出的缓存值为" + company);

        if (company != null) {
            logger.info("返回缓存");
            return company;

        } else {

            logger.info("缓存为空，现在从数据库查询数据");
            List<company> companys = companyMapper.allCompany();
            logger.info("从数据库中查询处的数据为" + companys);

                logger.info("把数据放入缓存");
                boolean state = client.set("MemCompany",companys);
                logger.info("添加新缓存是否成功" + state);

            return companys;
        }
    }

    //带缓存redis
    @Override
    public List<company> RedisAllCompany() {

        List<company> company = (List<company>)redisTemplate.opsForValue().get("RedisCompany");
        logger.info("查询出的缓存值为" + company);

        if (company != null && company.size()>0) {
            logger.info("返回缓存");
            return company;

        } else {

            logger.info("缓存为空，现在从数据库查询数据");
            List<company> companys = companyMapper.allCompany();
            logger.info("从数据库中查询处的数据为" + companys);

            try {
                logger.info("把数据放入缓存");
                redisTemplate.opsForValue().set("RedisCompany",companys);
                logger.info("添加缓存成功");
            }catch (Exception e){
                logger.info(e.toString());
                logger.info("放入缓存失败！");
            }
            return companys;
        }
    }



//==============  统计所有公司数量  1.不带缓存 2.Mem缓存 3.Redis缓存=================

    @Override
    public int countCompany(){
        return companyMapper.countCompany();
    }



    //Mem缓存的countCompany
    @Override
    public int MemCountCompany() {

            Integer rs = (Integer) client.get("MemCountCompany");

            if(rs!=null) {
                logger.info("有缓存，缓存为" + rs + "现在开始返回缓存");
                return rs;

            }else {

                //如果缓存为空   返回缓存数据
                logger.info("没有缓存,现在从数据库查询数据");
                int rss = companyMapper.countCompany();
                logger.info("从数据库中查询处的数据为" + rss);

                try {

                    logger.info("现在开始把数据放入缓存");
                    boolean state = client.set("MemCountCompany", rss);
                    logger.info("添加新缓存是否成功" + state);
                    return rss;

                }catch (Exception e){
                    logger.info(e.toString());
                    return rss;
                }
        }
    }

    //Redis缓存
    @Override
    public int RedisCountCompany() {
        Integer rs =(Integer) redisTemplate.opsForValue().get("RedisCountCompany");

        if(rs!= null){

            logger.info("有缓存，缓存为" + rs + "现在开始返回缓存");
            return rs;

        }else{

            //如果缓存为空   返回缓存数据
            logger.info("没有缓存,现在从数据库查询数据");
            int rss = companyMapper.countCompany();

            logger.info("从数据库中查询处的数据为" + rss);

            try {
                logger.info("现在开始把数据放入缓存");
                redisTemplate.opsForValue().set("RedisCountCompany",rss);
                logger.info("放入缓存成功");

            }catch (Exception e){
                logger.info(e.toString());
                logger.info("放入缓存失败！");
            }
            return rss;
        }
    }
}