package com.ccbc.pojo;

import com.ccbc.pojo.annotation.NoNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    @NoNull("value=username")
    private String username;
    @NoNull("value=password")
    private String password;
}
