package com.htsoft.oa.dao.admin;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.admin.InStock;

public abstract interface InStockDao extends BaseDao<InStock>
{
  public abstract Integer findInCountByBuyId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.InStockDao
 * JD-Core Version:    0.6.0
 */