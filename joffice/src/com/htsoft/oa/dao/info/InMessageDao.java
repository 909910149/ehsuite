package com.htsoft.oa.dao.info;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.InMessage;
import com.htsoft.oa.model.info.ShortMessage;
import java.util.Date;
import java.util.List;

public abstract interface InMessageDao extends BaseDao<InMessage>
{
  public abstract InMessage findByRead(Long paramLong);

  public abstract Integer findByReadFlag(Long paramLong);

  public abstract List<InMessage> findAll(Long paramLong, PagingBean paramPagingBean);

  public abstract List<InMessage> findByShortMessage(ShortMessage paramShortMessage, PagingBean paramPagingBean);

  public abstract List findByUser(Long paramLong, PagingBean paramPagingBean);

  public abstract List findByUser(Long paramLong);

  public abstract List searchInMessage(Long paramLong, InMessage paramInMessage, ShortMessage paramShortMessage, Date paramDate1, Date paramDate2, PagingBean paramPagingBean);

  public abstract InMessage findLatest(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.info.InMessageDao
 * JD-Core Version:    0.6.0
 */