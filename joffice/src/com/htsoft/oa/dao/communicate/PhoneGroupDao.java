package com.htsoft.oa.dao.communicate;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.communicate.PhoneGroup;
import java.util.List;

public abstract interface PhoneGroupDao extends BaseDao<PhoneGroup>
{
  public abstract Integer findLastSn(Long paramLong);

  public abstract PhoneGroup findBySn(Integer paramInteger, Long paramLong);

  public abstract List<PhoneGroup> findBySnUp(Integer paramInteger, Long paramLong);

  public abstract List<PhoneGroup> findBySnDown(Integer paramInteger, Long paramLong);

  public abstract List<PhoneGroup> getAll(Long paramLong);

  public abstract Integer findPublicLastSn();

  public abstract PhoneGroup findPublicBySn(Integer paramInteger);

  public abstract List<PhoneGroup> findPublicBySnUp(Integer paramInteger);

  public abstract List<PhoneGroup> findPublicBySnDown(Integer paramInteger);

  public abstract List<PhoneGroup> getPublicAll();
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.communicate.PhoneGroupDao
 * JD-Core Version:    0.6.0
 */