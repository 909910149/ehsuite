package com.htsoft.oa.dao.flow;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.system.AppUser;
import java.util.List;

public abstract interface ProDefinitionDao extends BaseDao<ProDefinition>
{
  public abstract ProDefinition getByDeployId(String paramString);

  public abstract ProDefinition getByName(String paramString);

  public abstract List<ProDefinition> getByRights(AppUser paramAppUser, ProDefinition paramProDefinition, QueryFilter paramQueryFilter);

  public abstract boolean checkNameByVo(ProDefinition paramProDefinition);

  public abstract boolean checkProcessNameByVo(ProDefinition paramProDefinition);

  public abstract List<ProDefinition> findRunningPro(ProDefinition paramProDefinition, Short paramShort, PagingBean paramPagingBean);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.ProDefinitionDao
 * JD-Core Version:    0.6.0
 */