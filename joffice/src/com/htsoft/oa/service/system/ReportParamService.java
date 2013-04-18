package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.ReportParam;
import java.util.List;

public abstract interface ReportParamService extends BaseService<ReportParam>
{
  public abstract List<ReportParam> findByRepTemp(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.ReportParamService
 * JD-Core Version:    0.6.0
 */