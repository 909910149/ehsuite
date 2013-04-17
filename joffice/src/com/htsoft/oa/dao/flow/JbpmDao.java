package com.htsoft.oa.dao.flow;

public abstract interface JbpmDao
{
  public abstract String getDefXmlByDeployId(String paramString);

  public abstract void wirteDefXml(String paramString1, String paramString2);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.JbpmDao
 * JD-Core Version:    0.6.0
 */