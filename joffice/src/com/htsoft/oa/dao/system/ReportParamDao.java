package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.ReportParam;
import java.util.List;

public abstract interface ReportParamDao extends BaseDao<ReportParam>
{
  public abstract List<ReportParam> findByRepTemp(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.ReportParamDao
 * JD-Core Version:    0.6.0
 */