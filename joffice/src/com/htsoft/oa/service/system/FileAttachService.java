package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.FileAttach;
import java.util.List;

public abstract interface FileAttachService extends BaseService<FileAttach>
{
  public abstract void removeByPath(String paramString);

  public abstract FileAttach getByPath(String paramString);

  public abstract void mutilDel(String paramString);

  public abstract List<FileAttach> fileList(PagingBean paramPagingBean, String paramString, boolean paramBoolean);

  public abstract List<FileAttach> fileList(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.FileAttachService
 * JD-Core Version:    0.6.0
 */