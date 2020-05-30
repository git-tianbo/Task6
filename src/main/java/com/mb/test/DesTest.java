package com.mb.test;

import com.mb.Util.DESUtilA;
import org.junit.Test;

public class DesTest {

    @Test
    public void listByName() throws Exception{

        DESUtilA des = new DESUtilA();

//        当前时间
        long loginTime=System.currentTimeMillis();

//        对long类型进行加密
        String str1=des.encryptFromLong(loginTime);

        System.out.println("long转换String后==================="+str1);

//        将解密完的密文转化回long类型
        long str2=des.decryptToLong(str1);

        System.out.println("转化完毕后输出的结果================="+str2);

//        加密String字符串
        String one= des.encrypt("啦啦啦啦啦");

        System.out.println("下面是对字符串啦啦啦啦啦进行加密");
        System.out.println(one);

        //解密String字符串
        System.out.println("下面是对字符串进行解密");
        System.out.println(des.decrypt(one));

    }

}