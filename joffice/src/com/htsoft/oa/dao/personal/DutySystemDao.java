package com.htsoft.oa.dao.personal;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.personal.DutySystem;
import java.util.List;

public abstract interface DutySystemDao extends BaseDao<DutySystem>
{
  public abstract void updateForNotDefult();

  public abstract List<DutySystem> getDefaultDutySystem();
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.personal.DutySystemDao
 * JD-Core Version:    0.6.0
 */