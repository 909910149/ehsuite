package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.FormDefMapping;
import com.htsoft.oa.model.flow.FormTemplate;
import java.util.List;

public abstract interface FormTemplateService extends BaseService<FormTemplate>
{
  public abstract List<FormTemplate> getByMappingId(Long paramLong);

  public abstract void batchAddDefault(List<String> paramList, FormDefMapping paramFormDefMapping);

  public abstract FormTemplate getByMappingIdNodeName(Long paramLong, String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.FormTemplateService
 * JD-Core Version:    0.6.0
 */