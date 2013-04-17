package com.htsoft.oa.dao.communicate;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.communicate.OutMailFolder;
import java.util.List;

public abstract interface OutMailFolderDao extends BaseDao<OutMailFolder>
{
  public abstract List<OutMailFolder> getAllUserFolderByParentId(Long paramLong1, Long paramLong2);

  public abstract List<OutMailFolder> getUserFolderByParentId(Long paramLong1, Long paramLong2);

  public abstract List<OutMailFolder> getFolderLikePath(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.communicate.OutMailFolderDao
 * JD-Core Version:    0.6.0
 */