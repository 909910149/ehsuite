 package com.htsoft.core.spring;
 
 import org.springframework.context.ApplicationEvent;
 
 public class SessionFactoryChangeEvent extends ApplicationEvent
 {
   private static final long serialVersionUID = 1L;
 
   public SessionFactoryChangeEvent(Object paramObject)
   {
     super(paramObject);
   }
 }

