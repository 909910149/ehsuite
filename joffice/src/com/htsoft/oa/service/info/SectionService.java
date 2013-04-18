package com.htsoft.oa.service.info;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.info.Section;

public abstract interface SectionService extends BaseService<Section>
{
  public abstract Integer getLastColumn();
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.info.SectionService
 * JD-Core Version:    0.6.0
 */