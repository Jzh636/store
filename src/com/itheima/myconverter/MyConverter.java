package com.itheima.myconverter;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义转换器
 */
public class MyConverter implements Converter {
    @Override
    public Object convert(Class aClass, Object value) {
        //class 要转换成的类型
        //页面上传入的值

        //将object 转成 data
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse((String) value);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
