package com.htsoft.oa.service.communicate;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.communicate.OutMail;
import java.util.List;
import java.util.Map;

public abstract interface OutMailService extends BaseService<OutMail>
{
  public abstract List<OutMail> findByFolderId(Long paramLong);

  public abstract Long CountByFolderId(Long paramLong);

  public abstract Map getUidByUserId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.OutMailService
 * JD-Core Version:    0.6.0
 */