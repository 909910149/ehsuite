package com.htsoft.oa.service.archive;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.archive.ArchFlowConf;

public abstract interface ArchFlowConfService extends BaseService<ArchFlowConf>
{
  public abstract ArchFlowConf getByFlowType(Short paramShort);

  public abstract Long getDefId(Short paramShort);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.ArchFlowConfService
 * JD-Core Version:    0.6.0
 */