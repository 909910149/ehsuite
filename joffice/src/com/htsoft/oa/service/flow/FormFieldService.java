package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.FormField;

public abstract interface FormFieldService extends BaseService<FormField>
{
  public abstract FormField find(Long paramLong, Short paramShort);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.FormFieldService
 * JD-Core Version:    0.6.0
 */