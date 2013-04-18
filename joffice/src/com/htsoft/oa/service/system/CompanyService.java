package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.Company;
import java.util.List;

public abstract interface CompanyService extends BaseService<Company>
{
  public abstract List<Company> findByHql(String paramString);

  public abstract List<Company> findCompany();
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.CompanyService
 * JD-Core Version:    0.6.0
 */