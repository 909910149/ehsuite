package com.htsoft.test.flow;

import com.htsoft.test.BaseTestCase;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

public class DynamicServiceTestCase extends BaseTestCase
{

  @Resource(name="sessionFactoryEntity")
  SessionFactory sessionFactoryEntity;

  @Test
  @Rollback(false)
  public void testDynamicGetObject()
  {
  }
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.flow.DynamicServiceTestCase
 * JD-Core Version:    0.6.0
 */