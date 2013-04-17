 package com.htsoft.core.dao.impl;
 
 import com.htsoft.core.dao.BaseDao;
 
 public class BaseDaoImpl<T> extends GenericDaoImpl<T, Long>
   implements BaseDao<T>
 {
   public BaseDaoImpl(Class persistType)
   {
     super(persistType);
   }


@Override
public void remove(T paramT) {
	// TODO Auto-generated method stub
	
}

@Override
public void flush() {
	// TODO Auto-generated method stub
	
}

@Override
public Long update(String paramString, Object[] paramArrayOfObject) {
	// TODO Auto-generated method stub
	return null;
} }

