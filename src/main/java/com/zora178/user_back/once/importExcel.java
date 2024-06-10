package com.zora178.user_back.once;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;

/**
 * Description:导入excel数据
 * Data: 2024/6/10
 */
public class importExcel {
    /**
     * 读取数据
     */
    public static void main(String[] args) {
        String fileName = "E:\\项目测试\\伙伴匹配\\partner_back\\src\\main\\resources\\testExcel.xlsx";
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        EasyExcel.read(fileName, userInfo.class, new DemoDataListener()).sheet().doRead();

    }
}
