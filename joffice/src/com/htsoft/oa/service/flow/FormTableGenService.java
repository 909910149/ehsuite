package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.FormTable;

public abstract interface FormTableGenService extends BaseService<FormTable>
{
  public abstract boolean genBean(FormTable[] paramArrayOfFormTable);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.FormTableGenService
 * JD-Core Version:    0.6.0
 */