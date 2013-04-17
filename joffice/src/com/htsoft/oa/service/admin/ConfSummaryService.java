package com.htsoft.oa.service.admin;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.admin.ConfSummary;

public abstract interface ConfSummaryService extends BaseService<ConfSummary>
{
  public abstract ConfSummary send(ConfSummary paramConfSummary, String paramString);

  public abstract ConfSummary save(ConfSummary paramConfSummary, String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.ConfSummaryService
 * JD-Core Version:    0.6.0
 */