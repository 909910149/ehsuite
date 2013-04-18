 package com.htsoft.core.spring;
 
 import java.util.Collection;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.Map;
 import org.hibernate.cfg.Configuration;
 import org.hibernate.impl.SessionFactoryImpl;
 import org.springframework.context.ApplicationEvent;
 import org.springframework.context.ApplicationListener;
 import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
 
 public class MyLocalSessionFactoryBean extends LocalSessionFactoryBean
   implements ApplicationListener
 {
   private static Map<String, String> newModel;
 
   public MyLocalSessionFactoryBean()
   {
     newModel = new HashMap();
   }
 
   public void onApplicationEvent(ApplicationEvent paramApplicationEvent) {
     if ((paramApplicationEvent instanceof SessionFactoryChangeEvent)) {
       newModel = (Map)paramApplicationEvent.getSource();
       Collection localCollection = newModel.values();
       if ((localCollection != null) && (localCollection.size() > 0)) {
         Configuration localConfiguration = new Configuration();
         Iterator localIterator = localCollection.iterator();
         while (localIterator.hasNext()) {
           String str = (String)localIterator.next();
           localConfiguration.addFile(str);
         }
         localConfiguration.doComplie();
         ((SessionFactoryImpl)getObject()).addNewConfig(localConfiguration);
       }
     }
   }
 }

