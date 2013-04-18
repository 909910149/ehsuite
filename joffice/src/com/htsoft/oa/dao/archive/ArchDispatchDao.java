package com.htsoft.oa.dao.archive;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.archive.ArchDispatch;
import com.htsoft.oa.model.system.AppUser;
import java.util.List;

public abstract interface ArchDispatchDao extends BaseDao<ArchDispatch>
{
  public abstract List<ArchDispatch> findByUser(AppUser paramAppUser, PagingBean paramPagingBean);

  public abstract List<ArchDispatch> findRecordByArc(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.archive.ArchDispatchDao
 * JD-Core Version:    0.6.0
 */