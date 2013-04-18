package com.htsoft.oa.dao.communicate;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.communicate.PhoneBook;
import java.util.List;

public abstract interface PhoneBookDao extends BaseDao<PhoneBook>
{
  public abstract List<PhoneBook> sharedPhoneBooks(String paramString1, String paramString2, PagingBean paramPagingBean);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.communicate.PhoneBookDao
 * JD-Core Version:    0.6.0
 */