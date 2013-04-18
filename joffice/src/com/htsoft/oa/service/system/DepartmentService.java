package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.Department;
import java.util.List;

public abstract interface DepartmentService extends BaseService<Department>
{
  public abstract List<Department> findByParentId(Long paramLong);

  public abstract List<Department> findByDepName(String paramString);

  public abstract List<Department> findByPath(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.DepartmentService
 * JD-Core Version:    0.6.0
 */