package com.htsoft.oa.dao.admin;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.admin.ConfSummary;

public abstract interface ConfSummaryDao extends BaseDao<ConfSummary>
{
  public abstract ConfSummary send(ConfSummary paramConfSummary, String paramString);

  public abstract ConfSummary save(ConfSummary paramConfSummary, String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.ConfSummaryDao
 * JD-Core Version:    0.6.0
 */