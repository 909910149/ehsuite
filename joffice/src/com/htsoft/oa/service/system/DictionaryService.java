package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.Dictionary;
import java.util.List;

public abstract interface DictionaryService extends BaseService<Dictionary>
{
  public abstract List<String> getAllItems();

  public abstract List<String> getAllByItemName(String paramString);

  public abstract List<Dictionary> getByItemName(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.DictionaryService
 * JD-Core Version:    0.6.0
 */