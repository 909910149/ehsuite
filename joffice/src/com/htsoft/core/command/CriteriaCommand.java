package com.htsoft.core.command;

import org.hibernate.Criteria;

public abstract interface CriteriaCommand
{
  public static final String SORT_DESC = "desc";
  public static final String SORT_ASC = "asc";

  public abstract Criteria execute(Criteria paramCriteria);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.core.command.CriteriaCommand
 * JD-Core Version:    0.6.0
 */