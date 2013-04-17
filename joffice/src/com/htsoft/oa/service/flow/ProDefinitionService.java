package com.htsoft.oa.service.flow;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.system.AppUser;
import java.util.List;

public abstract interface ProDefinitionService extends BaseService<ProDefinition>
{
  public abstract ProDefinition getByDeployId(String paramString);

  public abstract ProDefinition getByName(String paramString);

  public abstract List<ProDefinition> getByRights(AppUser paramAppUser, ProDefinition paramProDefinition, QueryFilter paramQueryFilter);

  public abstract boolean checkNameByVo(ProDefinition paramProDefinition);

  public abstract boolean checkProcessNameByVo(ProDefinition paramProDefinition);

  public abstract List<ProDefinition> findRunningPro(ProDefinition paramProDefinition, Short paramShort, PagingBean paramPagingBean);

  public abstract String defSave(ProDefinition paramProDefinition, Boolean paramBoolean);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.ProDefinitionService
 * JD-Core Version:    0.6.0
 */