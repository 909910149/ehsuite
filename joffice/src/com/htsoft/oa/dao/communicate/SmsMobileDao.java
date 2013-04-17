package com.htsoft.oa.dao.communicate;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.communicate.SmsMobile;
import java.util.List;

public abstract interface SmsMobileDao extends BaseDao<SmsMobile>
{
  public abstract List<SmsMobile> getNeedToSend();
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.communicate.SmsMobileDao
 * JD-Core Version:    0.6.0
 */