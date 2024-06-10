package com.zora178.user_back.once;

import com.alibaba.excel.EasyExcel;

import java.util.List;

/**
 * Description:导入用户到数据库
 * @auther
 * Data: 2024/6/10
 */
public class importUserAll {
    public static void main(String[] args) {
//        1、读取
        String fileName = "E:\\项目测试\\伙伴匹配\\partner_back\\src\\main\\resources\\testExcel.xlsx";
//同步读
        List<userInfo> list =
                EasyExcel.read(fileName).head(userInfo.class).sheet().doReadSync();
        for (userInfo data : list) {
            System.out.println(data);
        }
//        2、去重
    }
}
