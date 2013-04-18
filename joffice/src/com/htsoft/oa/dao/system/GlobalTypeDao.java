package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.GlobalType;
import java.util.List;

public abstract interface GlobalTypeDao extends BaseDao<GlobalType>
{
  public abstract List<GlobalType> getByParentIdCatKey(Long paramLong, String paramString);

  public abstract List<GlobalType> getByParentIdCatKeyUserId(Long paramLong1, String paramString, Long paramLong2);

  public abstract Integer getCountsByParentId(Long paramLong);

  public abstract List<GlobalType> getByParentId(Long paramLong);

  public abstract List<GlobalType> getByPath(String paramString);

  public abstract GlobalType findByTypeName(String paramString);

  public abstract List<GlobalType> getByRightsCatKey(AppUser paramAppUser, String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.GlobalTypeDao
 * JD-Core Version:    0.6.0
 */