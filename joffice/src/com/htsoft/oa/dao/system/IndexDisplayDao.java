package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.IndexDisplay;
import java.util.List;

public abstract interface IndexDisplayDao extends BaseDao<IndexDisplay>
{
  public abstract List<IndexDisplay> findByUser(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.IndexDisplayDao
 * JD-Core Version:    0.6.0
 */