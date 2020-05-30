package com.mb.service.serviceImpl;

import com.danga.MemCached.MemCachedClient;
import com.mb.dao.professionMapper;
import com.mb.enity.profession;
import com.mb.service.professionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class professionServiceImpl implements professionService {

    @Autowired
    professionMapper professionMapper;

    @Autowired
    MemCachedClient client;

    //    根据deve id划分职业方向分类
    @Override
    public List<profession> all(String developmentDirect) {

//
//        if (developmentDirect.equals("前端开发")) {
//            List<profession> listA = (List<profession>) client.get("ListA");
//            return listA;
//        } else {
//            List<profession> listA = professionMapper.allProfession(developmentDirect);
//            boolean stateA = client.set("ListA", listA);
//            return listA;
//        }
//
//        if(developmentDirect.equals("后端开发")) {
//            List<profession> listB = (List<profession>)client.get("ListB");
//            return listB;
//        } else {
//            List<profession> listB = professionMapper.allProfession(developmentDirect);
//                boolean stateB = client.set("ListB", listB);
//                return listB;
//        }
//
//        if (developmentDirect.equals("移动开发")) {
//            List<profession> listC = (List<profession>) client.get("ListC");
//            return listC;
//        } else {
//            List<profession> listC = professionMapper.allProfession(developmentDirect);
//                boolean stateC = client.set("ListC", listC);
//                return listC;
//        }
//
//
//        if (developmentDirect.equals("整站开发")) {
//            List<profession> listD = (List<profession>) client.get("ListD");
//            return listD;
//        } else {
//            List<profession> listD = professionMapper.allProfession(developmentDirect);
//                boolean stateD = client.set("ListD", listD);
//                return listD;
//        }
//
//
//        if (developmentDirect.equals("运行维护")) {
//            List<profession> listE = (List<profession>) client.get("ListE");
//            return listE;
//        } else {
//            List<profession> listE = professionMapper.allProfession(developmentDirect);
//                boolean stateE = client.set("ListE", listE);
//                return listE;
//    }
//
        return professionMapper.allProfession(developmentDirect);
    }
}
