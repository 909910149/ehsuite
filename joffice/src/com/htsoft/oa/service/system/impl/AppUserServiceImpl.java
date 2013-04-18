package com.htsoft.oa.service.system.impl;

import com.google.gson.Gson;
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.core.dynamicPwd.HttpClient;
import com.htsoft.oa.core.dynamicPwd.YooeResponse;
import com.htsoft.oa.dao.system.AppUserDao;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Department;
import com.htsoft.oa.model.system.IndexDisplay;
import com.htsoft.oa.model.system.PanelItem;
import com.htsoft.oa.model.system.SysConfig;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.IndexDisplayService;
import com.htsoft.oa.service.system.SysConfigService;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;

public class AppUserServiceImpl extends BaseServiceImpl<AppUser> implements
		AppUserService {
	private AppUserDao dao;

	@Resource
	private SysConfigService sysConfigService;

	@Resource
	IndexDisplayService indexDisplayService;

	public AppUserServiceImpl(AppUserDao dao) {
		super(dao);
		this.dao = dao;
	}

	public AppUser findByUserName(String username) {
		return this.dao.findByUserName(username);
	}

	public List<AppUser> findByDepartment(String path, PagingBean pb) {
		return this.dao.findByDepartment(path, pb);
	}

	public List<AppUser> findByDepartment(String path, String userIds,
			PagingBean pb) {
		return this.dao.findByDepartment(path, pb);
	}

	public List<AppUser> findByRole(Long roleId, PagingBean pb) {
		return this.dao.findByRole(roleId, pb);
	}

	public List<AppUser> findByRoleId(Long roleId) {
		return this.dao.findByRole(roleId);
	}

	public List<AppUser> findSubAppUser(String path, Set<Long> userIds,
			PagingBean pb) {
		return this.dao.findSubAppUser(path, userIds, pb);
	}

	public List<AppUser> findSubAppUserByRole(Long roleId, Set<Long> userIds,
			PagingBean pb) {
		return this.dao.findSubAppUserByRole(roleId, userIds, pb);
	}

	public List<AppUser> findByDepId(Long depId) {
		return this.dao.findByDepId(depId);
	}

	public String initDynamicPwd(HashMap<String, String> input, String function) {
		SysConfig dynamicPwdConfig = this.sysConfigService
				.findByKey("dynamicUri");
		URI base_uri = URI.create(dynamicPwdConfig.getDataValue());
		HttpClient client = new HttpClient(base_uri);
		try {
			YooeResponse response = client.call_api(function, input);

			String ret_cmd = response.getRetCmd();
			this.logger.debug("=============dynamicPwd status:" + ret_cmd);

			HashMap output = response.getVarsDict();
			Iterator i = output.keySet().iterator();
			String result = (String) output.get("ret");

			while (i.hasNext()) {
				String name = (String) i.next();
				String value = (String) output.get(name);
				this.logger.debug("==============dynamicPwd info:" + name + "="
						+ value);
			}

			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return "\"" + function + "\"失败，异常：" + e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return "\"" + function + "\"失败，异常：" + e.getMessage();
		}
	}

	public List<AppUser> findUsersByRoleIds(String roleIds) {
		return this.dao.findUsersByRoleIds(roleIds);
	}

	public List<AppUser> findRelativeUsersByUserId(Long userId, Short level) {
		return this.dao.findRelativeUsersByUserId(userId, level);
	}

	public List<AppUser> getUsersByRoleId(Long roleId) {
		return this.dao.getUsersByRoleId(roleId);
	}

	public String getCurUserInfo() {
		AppUser currentUser = ContextUtil.getCurrentUser();
		Department curDep = currentUser.getDepartment();
		if (curDep == null) {
			curDep = new Department();
			curDep.setDepId(Long.valueOf(0L));
			curDep.setDepName(AppUtil.getCompanyName());
		}

		List<IndexDisplay> list = this.indexDisplayService
				.findByUser(currentUser.getUserId());
		List items = new ArrayList();
		for (IndexDisplay id : list) {
			PanelItem pi = new PanelItem();
			pi.setPanelId(id.getPortalId());
			pi.setColumn(id.getColNum().intValue());
			pi.setRow(id.getRowNum().intValue());
			items.add(pi);
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{success:true,user:{userId:'")
				.append(currentUser.getUserId()).append("',fullname:'")
				.append(currentUser.getFullname()).append("',username:'")
				.append(currentUser.getUsername()).append("',depId:'")
				.append(curDep.getDepId()).append("',depName:'")
				.append(curDep.getDepName()).append("',rights:'");
		sb.append(currentUser.getRights().toString().replace("[", "")
				.replace("]", ""));

		Gson gson = new Gson();
		sb.append("',topModules:");
		sb.append(gson.toJson(currentUser.getTopModules().values()));
		sb.append(",items:").append(gson.toJson(items).toString());
		sb.append("},sysConfigs:{");

		List<SysConfig> sysConfigs = this.sysConfigService.getAll();
		for (SysConfig sysConfig : sysConfigs) {
			sb.append("'").append(sysConfig.getConfigKey()).append("':'")
					.append(sysConfig.getDataValue()).append("',");
		}
		if (sysConfigs.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("}}");

		return sb.toString().replaceAll("\"", "'");
	}

	public String findByfullname(String fullname) {
		List<AppUser> list = this.dao.findByfullName(fullname);
		StringBuffer sb = new StringBuffer("");
		int i = 0;
		for (AppUser appUser : list) {
			sb.append(appUser.getUserId() + ",");
			i++;
		}
		if (i > 0) {
			sb.delete(sb.length() - 1, sb.length());
		}
		return sb.toString();
	}
}
