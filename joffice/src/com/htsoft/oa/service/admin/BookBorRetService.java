package com.htsoft.oa.service.admin;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.admin.BookBorRet;
import java.util.List;

public abstract interface BookBorRetService extends BaseService<BookBorRet>
{
  public abstract BookBorRet getByBookSnId(Long paramLong);

  public abstract List getBorrowInfo(PagingBean paramPagingBean);

  public abstract List getReturnInfo(PagingBean paramPagingBean);

  public abstract Long getBookBorRetId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.BookBorRetService
 * JD-Core Version:    0.6.0
 */