package com.htsoft.oa.service.customer;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.customer.Customer;

public abstract interface CustomerService extends BaseService<Customer>
{
  public abstract boolean checkCustomerNo(String paramString);
}

/* Location:           E:\Workspace\Template Projects\joffice-mysql-tomcat6\tomcat6\webapps\joffice20\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.customer.CustomerService
 * JD-Core Version:    0.6.0
 */