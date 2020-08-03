package com.ccbc.pojo;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
    Male, Female;

    private String name;
    private static Map<String, Gender> maps;

    public String toString() {
        return this.name;
    }

    public static Gender fromString(String s) {
        return ((Gender) maps.get(s));
    }

    static {
        maps = new HashMap();

        for (Gender gender : maps.values())
            maps.put(gender.name, gender);
    }
}