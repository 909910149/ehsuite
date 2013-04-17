package com.htsoft.oa.service.admin;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.admin.BookSn;
import java.util.List;

public abstract interface BookSnService extends BaseService<BookSn>
{
  public abstract List<BookSn> findByBookId(Long paramLong);

  public abstract List<BookSn> findBorrowByBookId(Long paramLong);

  public abstract List<BookSn> findReturnByBookId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.BookSnService
 * JD-Core Version:    0.6.0
 */