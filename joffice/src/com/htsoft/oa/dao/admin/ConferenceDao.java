package com.htsoft.oa.dao.admin;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.admin.Conference;
import java.util.Date;
import java.util.List;

public abstract interface ConferenceDao extends BaseDao<Conference>
{
  public abstract List<Conference> getConfTopic(String paramString, PagingBean paramPagingBean);

  public abstract Conference send(Conference paramConference, String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract Conference temp(Conference paramConference, String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract String baseUserIdSearchFullName(String paramString);

  public abstract String judgeBoardRoomNotUse(Date paramDate1, Date paramDate2, Long paramLong);

  public abstract String apply(Long paramLong, String paramString, boolean paramBoolean);

  public abstract List<Conference> myJoin(Conference paramConference, Boolean paramBoolean, PagingBean paramPagingBean);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.ConferenceDao
 * JD-Core Version:    0.6.0
 */