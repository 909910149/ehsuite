package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.FormDefMapping;
import java.util.List;

public abstract interface FormDefMappingDao extends BaseDao<FormDefMapping>
{
  public abstract FormDefMapping getByDeployId(String paramString);

  public abstract FormDefMapping findByDefId(Long paramLong);

  public abstract List<FormDefMapping> getByFormDef(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.FormDefMappingDao
 * JD-Core Version:    0.6.0
 */