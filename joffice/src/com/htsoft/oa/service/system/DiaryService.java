package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.Diary;
import java.util.List;

public abstract interface DiaryService extends BaseService<Diary>
{
  public abstract List<Diary> getAllBySn(PagingBean paramPagingBean);

  public abstract List<Diary> getSubDiary(String paramString, PagingBean paramPagingBean);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.DiaryService
 * JD-Core Version:    0.6.0
 */