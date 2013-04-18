package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.RelativeJob;
import java.util.List;

public abstract interface RelativeJobDao extends BaseDao<RelativeJob>
{
  public abstract List<RelativeJob> findByParentId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.RelativeJobDao
 * JD-Core Version:    0.6.0
 */