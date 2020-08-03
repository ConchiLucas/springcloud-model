package com.ccbc;

import com.ccbc.pojo.User;

import java.lang.reflect.Field;

public class Test {


    public static void main(String[] args) {
        User user = new User();
        Field[] fields = user.getClass().getDeclaredFields();
        for(int i = 0;i<fields.length;i++){
            System.out.println(fields[i].getName());
        }
    }
}
