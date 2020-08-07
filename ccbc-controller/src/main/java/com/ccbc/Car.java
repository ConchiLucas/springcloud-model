package com.ccbc;

import com.ccbc.pojo.aop.notnull.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Car {

    @NotNull
    private String name;
    @NotNull
    private String password;

}
