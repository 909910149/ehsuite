package com.htsoft.oa.service.info;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.ShortMessage;
import java.util.Date;
import java.util.List;

public abstract interface ShortMessageService extends BaseService<ShortMessage>
{
  public abstract List<ShortMessage> findAll(Long paramLong, PagingBean paramPagingBean);

  public abstract List<ShortMessage> findByUser(Long paramLong);

  public abstract List searchShortMessage(Long paramLong, ShortMessage paramShortMessage, Date paramDate1, Date paramDate2, PagingBean paramPagingBean, Short paramShort);

  public abstract ShortMessage save(Long paramLong, String paramString1, String paramString2, Short paramShort);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.info.ShortMessageService
 * JD-Core Version:    0.6.0
 */