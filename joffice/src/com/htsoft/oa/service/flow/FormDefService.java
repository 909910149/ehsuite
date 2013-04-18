package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.FormDef;
import com.htsoft.oa.model.flow.FormTable;
import java.util.List;
import java.util.Map;

public abstract interface FormDefService extends BaseService<FormDef>
{
  public abstract List<FormDef> getByDeployId(String paramString);

  public abstract FormDef getByDeployIdActivityName(String paramString1, String paramString2);

  public abstract FormDef saveFormDef(FormDef paramFormDef, Map<FormTable, String> paramMap);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.FormDefService
 * JD-Core Version:    0.6.0
 */