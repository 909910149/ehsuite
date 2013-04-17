package com.htsoft.oa.service.communicate;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.communicate.SmsMobile;
import java.util.List;

public abstract interface SmsMobileService extends BaseService<SmsMobile>
{
  public abstract List<SmsMobile> getNeedToSend();

  public abstract void saveSms(String paramString1, String paramString2);

  public abstract void sendSms();

  public abstract void sendOneSms(SmsMobile paramSmsMobile);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.SmsMobileService
 * JD-Core Version:    0.6.0
 */