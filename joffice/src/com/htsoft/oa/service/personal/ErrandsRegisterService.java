package com.htsoft.oa.service.personal;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.action.flow.FlowRunInfo;
import com.htsoft.oa.model.personal.ErrandsRegister;

public abstract interface ErrandsRegisterService extends BaseService<ErrandsRegister>
{
  public abstract Integer saveRegister(FlowRunInfo paramFlowRunInfo);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.personal.ErrandsRegisterService
 * JD-Core Version:    0.6.0
 */