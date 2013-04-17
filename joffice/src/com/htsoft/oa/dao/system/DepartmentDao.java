package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.Department;
import java.util.List;

public abstract interface DepartmentDao extends BaseDao<Department>
{
  public abstract List<Department> findByParentId(Long paramLong);

  public abstract List<Department> findByVo(Department paramDepartment, PagingBean paramPagingBean);

  public abstract List<Department> findByDepName(String paramString);

  public abstract List<Department> findByPath(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.DepartmentDao
 * JD-Core Version:    0.6.0
 */