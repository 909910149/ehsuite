package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.FieldRights;
import java.util.List;

public abstract interface FieldRightsDao extends BaseDao<FieldRights>
{
  public abstract List<FieldRights> getByMappingFieldTaskName(Long paramLong1, Long paramLong2, String paramString);

  public abstract List<FieldRights> getByMappingIdAndTaskName(Long paramLong, String paramString);

  public abstract List<FieldRights> getByMappingId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.FieldRightsDao
 * JD-Core Version:    0.6.0
 */