package com.htsoft.oa.service.document;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.document.DocFolder;
import java.util.List;

public abstract interface DocFolderService extends BaseService<DocFolder>
{
  public abstract List<DocFolder> getUserFolderByParentId(Long paramLong1, Long paramLong2);

  public abstract List<DocFolder> getFolderLikePath(String paramString);

  public abstract List<DocFolder> getPublicFolderByParentId(Long paramLong);

  public abstract List<DocFolder> findByParentId(Long paramLong);

  public abstract List<DocFolder> findByUserAndName(Long paramLong, String paramString);

  public abstract List<DocFolder> getOnlineFolderByParentId(Long paramLong);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.document.DocFolderService
 * JD-Core Version:    0.6.0
 */