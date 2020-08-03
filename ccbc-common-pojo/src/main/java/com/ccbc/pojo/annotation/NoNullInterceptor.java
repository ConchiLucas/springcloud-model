
package com.ccbc.pojo.annotation;

import com.alibaba.fastjson.JSON;
import com.ccbc.pojo.resp.DdResp;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class NoNullInterceptor extends HandlerInterceptorAdapter{
	 //在请求处理之前进行调用（Controller方法调用之前
   @Override
   public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
	   httpServletRequest.setCharacterEncoding("UTF-8");
	   httpServletResponse.setCharacterEncoding("UTF-8");
	   //如果不是映射到方法直接通过
       if (!(o instanceof HandlerMethod)) {
           return true;
       }
       HandlerMethod handlerMethod = (HandlerMethod) o;
       Method method = handlerMethod.getMethod();      
       Annotation[][] parameterAnnotations = method.getParameterAnnotations();
       if (parameterAnnotations == null || parameterAnnotations.length == 0) {
             return true;
       }   
       for (int i = 0; i < parameterAnnotations.length; i++) {
            for (int j = 0; j < parameterAnnotations[i].length; j++) {
            //如果该参数前面的注解是CheckParamNull的实例，并且notNull()=true,则进行非空校验
            if (parameterAnnotations[i][j] != null && parameterAnnotations[i][j] instanceof NoNull) {
            		String paramStr = parameterAnnotations[i][j].toString();
            		paramStr = paramStr.substring(paramStr.indexOf("("),paramStr.indexOf(")"));
            		String[] paramArr = paramStr.split(",");            		            		
            		for(String paramArrStr : paramArr) {
                        System.out.println(paramArrStr);
            			if(paramArrStr.indexOf("value=") >= 0) {
            				String value = paramArrStr.substring(paramArrStr.indexOf("value=")+6);
                           	Object obj = httpServletRequest.getParameter(value);
                           	if(obj == null){                           		
                    			httpServletResponse.getWriter().write(JSON.toJSONString(new DdResp("-1",value+"为空")));
                    			return false;
                           	}
            			}
            		}         		
               }
            }
        }
       	return true;
   }

   //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
   @Override
   public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
   	
   }

   //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
   @Override
   public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
       
   }
}
