package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.FormField;
import java.util.List;

public abstract interface FormFieldDao extends BaseDao<FormField>
{
  public abstract FormField find(Long paramLong, Short paramShort);

  public abstract List<FormField> getByForeignTableAndKey(String paramString1, String paramString2);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.FormFieldDao
 * JD-Core Version:    0.6.0
 */