package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.FileAttach;
import java.util.List;

public abstract interface FileAttachDao extends BaseDao<FileAttach>
{
  public abstract void removeByPath(String paramString);

  public abstract FileAttach getByPath(String paramString);

  public abstract List<FileAttach> fileList(PagingBean paramPagingBean, String paramString, boolean paramBoolean);

  public abstract List<FileAttach> fileList(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.FileAttachDao
 * JD-Core Version:    0.6.0
 */