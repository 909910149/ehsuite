package com.htsoft.oa.dao.archive;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.archive.ArchHasten;
import java.util.Date;

public abstract interface ArchHastenDao extends BaseDao<ArchHasten>
{
  public abstract Date getLeastRecordByUser(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.archive.ArchHastenDao
 * JD-Core Version:    0.6.0
 */