package com.htsoft.oa.dao.admin;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.admin.BookSn;
import java.util.List;

public abstract interface BookSnDao extends BaseDao<BookSn>
{
  public abstract List<BookSn> findByBookId(Long paramLong);

  public abstract List<BookSn> findBorrowByBookId(Long paramLong);

  public abstract List<BookSn> findReturnByBookId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.BookSnDao
 * JD-Core Version:    0.6.0
 */