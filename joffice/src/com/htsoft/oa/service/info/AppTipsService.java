package com.htsoft.oa.service.info;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.info.AppTips;

public abstract interface AppTipsService extends BaseService<AppTips>
{
  public abstract AppTips findByName(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.info.AppTipsService
 * JD-Core Version:    0.6.0
 */