package com.hy.serverside.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: JsonData
 * @Description: TODO 封装json结果集
 * @Author: Kaiser
 * @Date: 2019/4/22 11:06
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonData {
    private Object result;
    private String msg;
    private boolean status;
}
