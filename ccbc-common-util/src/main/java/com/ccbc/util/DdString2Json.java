package com.ccbc.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ccbc.pojo.req.DdReq;

public class DdString2Json {
	
	/**
	 * 
	 * DD请求类转Map
	 * @param req
	 * @return
	 */
	public static Map toMap(DdReq req){
		String bizContent = req.getBizContent();
		JSONObject map =JSON.parseObject(bizContent);
		if (map.getString("loanAmount")!=null) {
			map.put("loanAmount",getLoanAmount(map.getString("loanAmount")));
		}
		return map;
	}
	/**
	 * DD请求类转jsonObject
	 */
	public static JSONObject toJsonObject(DdReq req){
		String bizContent = req.getBizContent();
		JSONObject reqObject = JSON.parseObject(bizContent);
		if (reqObject.getString("loanAmount")!=null) {
			reqObject.put("loanAmount",getLoanAmount(reqObject.getString("loanAmount")));
		}
		return reqObject;
	}
	
	public static String getLoanAmount(String string){
    	int i = Integer.parseInt(string)/100;
    	return String.valueOf(i);
    }
}
