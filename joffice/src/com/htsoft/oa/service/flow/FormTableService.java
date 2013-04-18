package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.flow.FormTable;
import com.htsoft.oa.model.system.AppUser;
import java.util.List;

public abstract interface FormTableService extends BaseService<FormTable>
{
  public abstract List<FormTable> getListFromPro(String paramString1, String paramString2, AppUser paramAppUser, PagingBean paramPagingBean);

  public abstract List<FormTable> getAllAndFields();

  public abstract List<FormTable> findByTableKey(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.FormTableService
 * JD-Core Version:    0.6.0
 */