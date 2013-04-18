package com.htsoft.oa.service.archive;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.archive.ArchivesDoc;
import java.util.List;

public abstract interface ArchivesDocService extends BaseService<ArchivesDoc>
{
  public abstract List<ArchivesDoc> findByAid(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.ArchivesDocService
 * JD-Core Version:    0.6.0
 */