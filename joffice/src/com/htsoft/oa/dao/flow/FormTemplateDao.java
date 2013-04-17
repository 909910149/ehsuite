package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.FormTemplate;
import java.util.List;

public abstract interface FormTemplateDao extends BaseDao<FormTemplate>
{
  public abstract List<FormTemplate> getByMappingId(Long paramLong);

  public abstract FormTemplate getByMappingIdNodeName(Long paramLong, String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.FormTemplateDao
 * JD-Core Version:    0.6.0
 */