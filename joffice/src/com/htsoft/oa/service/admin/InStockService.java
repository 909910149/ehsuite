package com.htsoft.oa.service.admin;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.admin.InStock;

public abstract interface InStockService extends BaseService<InStock>
{
  public abstract Integer findInCountByBuyId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.InStockService
 * JD-Core Version:    0.6.0
 */