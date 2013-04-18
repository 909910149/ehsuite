package com.htsoft.oa.dao.archive;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.archive.ArchFlowConf;

public abstract interface ArchFlowConfDao extends BaseDao<ArchFlowConf>
{
  public abstract ArchFlowConf getByFlowType(Short paramShort);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.archive.ArchFlowConfDao
 * JD-Core Version:    0.6.0
 */