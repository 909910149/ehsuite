package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.ProHandleComp;
import java.util.List;

public abstract interface ProHandleCompDao extends BaseDao<ProHandleComp>
{
  public abstract List<ProHandleComp> getByDeployIdActivityName(String paramString1, String paramString2);

  public abstract List<ProHandleComp> getByDeployIdActivityNameHandleType(String paramString1, String paramString2, Short paramShort);

  public abstract ProHandleComp getProHandleComp(String paramString1, String paramString2, String paramString3);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.ProHandleCompDao
 * JD-Core Version:    0.6.0
 */