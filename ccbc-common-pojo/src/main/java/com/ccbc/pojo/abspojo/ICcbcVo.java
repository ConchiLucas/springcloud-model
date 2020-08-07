package com.ccbc.pojo.abspojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class ICcbcVo {

    /**
     * 具体的vo名称
     */
    private String voName;

    public ICcbcVo() {
        setVoName(this.getClass().getSimpleName());
    }



    public abstract String getCapitalSide();
}
