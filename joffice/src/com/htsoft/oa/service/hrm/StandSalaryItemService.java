package com.htsoft.oa.service.hrm;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.hrm.StandSalaryItem;
import java.util.List;

public abstract interface StandSalaryItemService extends BaseService<StandSalaryItem>
{
  public abstract List<StandSalaryItem> getAllByStandardId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.StandSalaryItemService
 * JD-Core Version:    0.6.0
 */