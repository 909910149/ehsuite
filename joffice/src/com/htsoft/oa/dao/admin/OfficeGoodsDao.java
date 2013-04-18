package com.htsoft.oa.dao.admin;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.admin.OfficeGoods;
import java.util.List;

public abstract interface OfficeGoodsDao extends BaseDao<OfficeGoods>
{
  public abstract List<OfficeGoods> findByWarm();
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.OfficeGoodsDao
 * JD-Core Version:    0.6.0
 */