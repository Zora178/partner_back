package com.zora178.user_back.once;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class userInfo {
    /**
     * id
     */
    @ExcelProperty("成员编号")
    private String planetCode;
    /**
     * 用户昵称
     */
    @ExcelProperty("成员呢称")
    private String username;
}