package com.htsoft.oa.service.flow;

import com.htsoft.oa.model.flow.ProcessRun;
import javax.servlet.http.HttpServletRequest;

public abstract interface ProcessService
{
  public abstract ProcessRun doStartFlow(HttpServletRequest paramHttpServletRequest)
    throws Exception;

  public abstract ProcessRun doNextFlow(HttpServletRequest paramHttpServletRequest)
    throws Exception;
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.ProcessService
 * JD-Core Version:    0.6.0
 */