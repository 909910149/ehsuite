package com.htsoft.oa.service.communicate;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.communicate.MailBox;
import java.util.List;

public abstract interface MailBoxService extends BaseService<MailBox>
{
  public abstract Long CountByFolderId(Long paramLong);

  public abstract List<MailBox> findByFolderId(Long paramLong);

  public abstract List<MailBox> findBySearch(String paramString, PagingBean paramPagingBean);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.MailBoxService
 * JD-Core Version:    0.6.0
 */