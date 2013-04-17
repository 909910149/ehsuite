package com.htsoft.oa.service.hrm;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.hrm.EmpProfile;

public abstract interface EmpProfileService extends BaseService<EmpProfile>
{
  public abstract boolean checkProfileNo(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.EmpProfileService
 * JD-Core Version:    0.6.0
 */