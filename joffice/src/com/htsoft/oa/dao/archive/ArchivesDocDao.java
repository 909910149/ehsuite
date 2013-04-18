package com.htsoft.oa.dao.archive;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.archive.ArchivesDoc;
import java.util.List;

public abstract interface ArchivesDocDao extends BaseDao<ArchivesDoc>
{
  public abstract List<ArchivesDoc> findByAid(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.archive.ArchivesDocDao
 * JD-Core Version:    0.6.0
 */