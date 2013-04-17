package com.htsoft.oa.service.system.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.system.AppRoleDao;
import com.htsoft.oa.model.system.AppRole;
import com.htsoft.oa.service.system.AppRoleService;
import java.util.HashMap;
import java.util.Set;
import javax.jws.WebService;

@WebService
public class AppRoleServiceImpl extends BaseServiceImpl<AppRole> implements
		AppRoleService {
	private AppRoleDao dao;

	public AppRoleServiceImpl(AppRoleDao dao) {
		super(dao);
		this.dao = dao;
	}

	public AppRole getByRoleName(String roleName) {
		return this.dao.getByRoleName(roleName);
	}

	public HashMap<String, Set<String>> getSecurityDataSource() {
		return this.dao.getSecurityDataSource();
	}

	public AppRole getById(Long roleId) {
		return (AppRole) get(roleId);
	}
}
