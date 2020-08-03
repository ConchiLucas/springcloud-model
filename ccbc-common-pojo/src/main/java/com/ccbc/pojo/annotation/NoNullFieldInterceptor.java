
package com.ccbc.pojo.annotation;

import com.alibaba.fastjson.JSON;
import com.ccbc.pojo.resp.DdResp;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class NoNullFieldInterceptor extends HandlerInterceptorAdapter{
	 //在请求处理之前进行调用（Controller方法调用之前
   @Override
   public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
	   httpServletRequest.setCharacterEncoding("UTF-8");
	   httpServletResponse.setCharacterEncoding("UTF-8");

       Field[] fields = o.getClass().getDeclaredFields();
       String getFields = "";
       for (Field field : fields) {
           String fieldName = field.getName();
           System.out.println(fieldName);
           NoNull declaredAnnotation = field.getDeclaredAnnotation(NoNull.class);
           if(declaredAnnotation == null){
               httpServletResponse.getWriter().write(JSON.toJSONString(new DdResp("-1",fieldName+"为空")));
               break;
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
