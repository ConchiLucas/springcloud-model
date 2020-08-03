/*
package com.ccbc.pojo.annotation;

import com.ccbc.pojo.resp.DdResp;
import com.sun.istack.internal.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class NoNullAnnotation {

    //https://blog.csdn.net/wwh842005739/article/details/86508803
    public static DdResp validate(T t){
        List<ValidateResult> validateResults = new ArrayList<>();
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field:fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                Object value = null;
                try {
                    value = field.get(t);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (value==null) {
                    NotNull notNull = field.getAnnotation(NotNull.class);
                    ValidateResult validateResult = new ValidateResult();
                    validateResult.setMessage(notNull.fileName()+"不能为空");
                    validateResults.add(validateResult);
                }
            }

        }
        return validateResults;
    }
}
*/
