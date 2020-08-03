package com.ccbc;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class Car {

    @NonNull
    private String name;
    @NonNull
    private String password;

}
