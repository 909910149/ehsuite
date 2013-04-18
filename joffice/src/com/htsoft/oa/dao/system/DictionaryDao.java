package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.Dictionary;
import java.util.List;

public abstract interface DictionaryDao extends BaseDao<Dictionary>
{
  public abstract List<String> getAllItems();

  public abstract List<String> getAllByItemName(String paramString);

  public abstract List<Dictionary> getByItemName(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.DictionaryDao
 * JD-Core Version:    0.6.0
 */