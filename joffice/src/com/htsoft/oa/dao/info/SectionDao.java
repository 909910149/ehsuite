package com.htsoft.oa.dao.info;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.info.Section;

public abstract interface SectionDao extends BaseDao<Section>
{
  public abstract Integer getLastColumn();
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.info.SectionDao
 * JD-Core Version:    0.6.0
 */