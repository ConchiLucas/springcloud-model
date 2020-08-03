package com.ccbc.pojo.resp;

import com.ccbc.pojo.common.RespBaseMsg;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DdResp extends RespBaseMsg {

    private static final long serialVersionUID = 1L;

    private String errorCode;  //响应码
    private String errorMsg; //响应码描述




}
