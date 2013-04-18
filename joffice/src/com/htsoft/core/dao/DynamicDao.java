package com.htsoft.core.dao;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.paging.PagingBean;
import java.io.Serializable;
import java.util.List;

public abstract interface DynamicDao
{
  public abstract Object save(Object paramObject);

  public abstract Object merge(Object paramObject);

  public abstract Object get(Serializable paramSerializable);

  public abstract void remove(Serializable paramSerializable);

  public abstract void remove(Object paramObject);

  public abstract void evict(Object paramObject);

  public abstract List<Object> getAll();

  public abstract List<Object> getAll(PagingBean paramPagingBean);

  public abstract List<Object> getAll(QueryFilter paramQueryFilter);
}

