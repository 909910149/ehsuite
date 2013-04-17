package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.FieldRights;
import java.util.List;

public abstract interface FieldRightsService extends BaseService<FieldRights>
{
  public abstract List<FieldRights> getByMappingFieldTaskName(Long paramLong1, Long paramLong2, String paramString);

  public abstract List<FieldRights> getByMappingIdAndTaskName(Long paramLong, String paramString);

  public abstract List<FieldRights> getByMappingId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.FieldRightsService
 * JD-Core Version:    0.6.0
 */