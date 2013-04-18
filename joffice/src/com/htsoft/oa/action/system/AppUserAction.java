package com.htsoft.oa.action.system;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.Constants;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.log.Action;
import com.htsoft.core.model.OnlineUser;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.JsonUtil;
import com.htsoft.core.util.StringUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.AppRole;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.DepUsers;
import com.htsoft.oa.model.system.Department;
import com.htsoft.oa.model.system.IndexDisplay;
import com.htsoft.oa.model.system.PanelItem;
import com.htsoft.oa.model.system.SysConfig;
import com.htsoft.oa.model.system.UserJob;
import com.htsoft.oa.service.system.AppRoleService;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.DepUsersService;
import com.htsoft.oa.service.system.DepartmentService;
import com.htsoft.oa.service.system.IndexDisplayService;
import com.htsoft.oa.service.system.RelativeUserService;
import com.htsoft.oa.service.system.SysConfigService;
import com.htsoft.oa.service.system.UserJobService;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class AppUserAction extends BaseAction {
	private static Long SUPPER_MANAGER_ID = Long.valueOf(-1L);

	@Resource
	private AppUserService appUserService;

	@Resource
	private DepartmentService departmentService;

	@Resource
	private AppRoleService appRoleService;

	@Resource
	private IndexDisplayService indexDisplayService;

	@Resource
	private DepUsersService depUsersService;

	@Resource
	private UserJobService userJobService;

	@Resource
	private SysConfigService sysConfigService;

	@Resource
	private RelativeUserService relativeUserService;
	private AppUser appUser;
	private Long userId;
	private Long depId;
	private Long roleId;

	public Long getDepId() {
		return this.depId;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public String getCurrent() {
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

		setJsonString(sb.toString());
		return "success";
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_delFlag_SN_EQ", Constants.FLAG_UNDELETED.toString());
		List list = this.appUserService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		JSONSerializer serializer = new JSONSerializer();
		serializer.transform(new DateTransformer("yyyy-MM-dd"),
				new String[] { "accessionTime" });
		buff.append(serializer.exclude(new String[] { "password" }).serialize(
				list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String select() {
		PagingBean pb = getInitPagingBean();
		String strDepId = getRequest().getParameter("depId");

		String path = "0.";
		this.appUser = ContextUtil.getCurrentUser();
		if (StringUtils.isNotEmpty(strDepId)) {
			Long depId = Long.valueOf(Long.parseLong(strDepId));
			Department dep = (Department) this.departmentService.get(depId);
			if (dep != null)
				path = dep.getPath();
		} else {
			Department dep = this.appUser.getDepartment();
			if (dep != null) {
				path = dep.getPath();
			}
		}
		List list = this.appUserService.findByDepartment(path, pb);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:");
		JSONSerializer serializer = new JSONSerializer();
		serializer.transform(new DateTransformer("yyyy-MM-dd"),
				new String[] { "accessionTime" });
		buff.append(serializer.exclude(new String[] { "password" }).serialize(
				list));
		buff.append("}");

		this.jsonString = buff.toString();
		return "success";
	}

	public String online() {
		Map<String, OnlineUser> onlineUsers = new HashMap();
		Map onlineUsersByDep = new HashMap();
		Map onlineUsersByRole = new HashMap();

		onlineUsers = AppUtil.getOnlineUsers();

		if (this.depId != null) {
			for (String sessionId : onlineUsers.keySet()) {
				OnlineUser onlineUser = new OnlineUser();
				onlineUser = (OnlineUser) onlineUsers.get(sessionId);

				String path = "";
				if (!onlineUser.getUserId().equals(AppUser.SUPER_USER)) {
					path = onlineUser.getDepPath();
				}
				if (!this.depId.equals(new Long(0L))) {
					if (Pattern.compile("." + this.depId + ".").matcher(path)
							.find())
						onlineUsersByDep.put(sessionId, onlineUser);
				} else {
					onlineUsersByDep.put(sessionId, onlineUser);
				}
			}
		}

		if (this.roleId != null) {
			for (String sessionId : onlineUsers.keySet()) {
				OnlineUser onlineUser = new OnlineUser();
				onlineUser = (OnlineUser) onlineUsers.get(sessionId);

				if (Pattern.compile("," + this.roleId + ",")
						.matcher(onlineUser.getRoleIds()).find()) {
					onlineUsersByRole.put(sessionId, onlineUser);
				}
			}
		}

		Type type = new TypeToken<List<OnlineUser>>() {}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(onlineUsers.size()).append(",result:");

		Gson gson = new Gson();
		if (this.depId != null)
			buff.append(gson.toJson(onlineUsersByDep.values(), type));
		else if (this.roleId != null)
			buff.append(gson.toJson(onlineUsersByRole.values(), type));
		else {
			buff.append(gson.toJson(onlineUsers.values(), type));
		}
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String find() {
		String strRoleId = getRequest().getParameter("roleId");
		PagingBean pb = getInitPagingBean();
		if (StringUtils.isNotEmpty(strRoleId)) {
			List<AppUser> userList = this.appUserService.findByRole(
					Long.valueOf(Long.parseLong(strRoleId)), pb);
			Type type = new TypeToken<List<AppUser> >() {
			}.getType();
			StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
					.append(pb.getTotalItems()).append(",result:");
			Gson gson = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation().create();
			buff.append(gson.toJson(userList, type));
			buff.append("}");

			this.jsonString = buff.toString();
		} else {
			this.jsonString = "{success:false}";
		}
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		StringBuffer buff = new StringBuffer("{success:true");
		if (ids != null) {
			buff.append(",msg:'");
			for (String id : ids) {
				AppUser delUser = (AppUser) this.appUserService
						.get(new Long(id));
				AppRole superManager = (AppRole) this.appRoleService
						.get(SUPPER_MANAGER_ID);
				if (delUser.getRoles().contains(superManager)) {
					buff.append("员工:").append(delUser.getUsername())
							.append("是超级管理员,不能删除!<br><br/>");
				} else if (delUser.getUserId().equals(
						ContextUtil.getCurrentUserId())) {
					buff.append("不能删除自己!<br></br>");
				} else {
					delUser.setStatus(Constants.FLAG_DISABLE);
					delUser.setDelFlag(Constants.FLAG_DELETED);
					delUser.setUsername("__" + delUser.getUsername());
					this.appUserService.save(delUser);
				}
			}
			buff.append("'");
		}
		buff.append("}");
		setJsonString(buff.toString());
		return "success";
	}

	public String get() {
		AppUser appUser = null;
		JSONSerializer json = JsonUtil
				.getJSONSerializer(new String[] { "accessionTime" });
		if (this.userId != null) {
			appUser = (AppUser) this.appUserService.get(this.userId);
		} else {
			json.exclude(new String[] { "accessionTime", "department",
					"password", "status", "position" });
			appUser = ContextUtil.getCurrentUser();
		}

		StringBuffer sb = new StringBuffer("{success:true,totalCounts:1,data:[");
		sb.append(JsonUtil.getJSONSerializer(new String[] { "accessionTime" })
				.serialize(appUser));
		sb.append("]}");
		setJsonString(sb.toString());

		return "success";
	}

	@Action(description = "添加或保存用户信息")
	public String save() {
		Set roles = new HashSet();

		String rolesIds = getRequest().getParameter("AppUserRoles");
		if (rolesIds != null) {
			String[] ids = rolesIds.split(",");
			for (String id : ids) {
				if (!"".equals(id)) {
					AppRole role = (AppRole) this.appRoleService.get(new Long(
							id));
					roles.add(role);
				}
			}
		}

		boolean saveTrue = true;

		this.appUser.setRoles(roles);
		if (this.appUser.getUserId() != null) {
			AppUser old = (AppUser) this.appUserService.get(this.appUser
					.getUserId());
			this.appUser.setDelFlag(old.getDelFlag());
			this.appUser.setPassword(old.getPassword());
			this.appUser.setDynamicPwd(old.getDynamicPwd());
			this.appUser.setDyPwdStatus(old.getDyPwdStatus());
			this.appUserService.merge(this.appUser);
			setJsonString("{success:true}");
		} else if (this.appUserService.findByUserName(this.appUser
				.getUsername()) == null) {
			this.appUser.setDelFlag(Constants.FLAG_UNDELETED);
			this.appUser.setPassword(StringUtil.encryptSha256(this.appUser
					.getPassword()));
			this.appUserService.save(this.appUser);
			setJsonString("{success:true}");
		} else {
			saveTrue = false;
			setJsonString("{success:false,msg:'用户登录账号:"
					+ this.appUser.getUsername() + "已存在,请重新输入账号.'}");
		}

		if (saveTrue) {
			QueryFilter snFilter = new QueryFilter(getRequest());
			snFilter.addSorted("sn", "DESC");
			snFilter.getPagingBean().setStart(Integer.valueOf(0));
			snFilter.getPagingBean().setPageSize(1);
			List snList = this.depUsersService.getAll(snFilter);
			int sn = 0;
			if ((snList != null) && (snList.size() > 0)) {
				sn = ((DepUsers) snList.get(0)).getSn().intValue() + 1;
			}

			String depParams = getRequest().getParameter("depParams");
			DepUsers orgDepUsers;
			if (StringUtils.isNotEmpty(depParams)) {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
						.create();
				DepUsers[] dus = (DepUsers[]) gson.fromJson(depParams,
						com.htsoft.oa.model.system.DepUsers[].class);

				if ((dus != null) && (dus.length > 0)) {
					for (DepUsers du : dus) {
						if (du.getDepUserId() != null) {
							orgDepUsers = (DepUsers) this.depUsersService
									.get(du.getDepUserId());
							try {
								BeanUtil.copyNotNullProperties(orgDepUsers, du);
								orgDepUsers.setAppUser(this.appUser);
								if (orgDepUsers.getSn() == null)
									orgDepUsers.setSn(Integer.valueOf(sn++));
								this.depUsersService.save(orgDepUsers);
							} catch (Exception ex) {
								this.logger.error(ex.getMessage());
							}
						} else {
							du.setAppUser(this.appUser);
							if (du.getSn() == null)
								du.setSn(Integer.valueOf(sn++));
							this.depUsersService.save(du);
						}
					}

				}

			}

			String jobParams = getRequest().getParameter("jobParams");
			if (StringUtils.isNotEmpty(jobParams)) {
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
						.create();
				UserJob[] jus = (UserJob[]) gson.fromJson(jobParams,
						com.htsoft.oa.model.system.UserJob[].class);
				if ((jus != null) && (jus.length > 0)) {
					for (UserJob ju : jus) {
						if (ju.getUserJobId() != null) {
							UserJob orgUserJob = (UserJob) this.userJobService
									.get(ju.getUserJobId());
							try {
								BeanUtil.copyNotNullProperties(orgUserJob, ju);
								orgUserJob.setAppUser(this.appUser);

								this.userJobService.save(orgUserJob);
							} catch (Exception ex) {
								this.logger.error(ex.getMessage());
							}
						} else {
							ju.setAppUser(this.appUser);

							this.userJobService.save(ju);
						}
					}
				}

			}

		}

		return "success";
	}

	public String selectedRoles() {
		if (this.userId != null) {
			setAppUser((AppUser) this.appUserService.get(this.userId));
			Set<AppRole> roles = this.appUser.getRoles();
			StringBuffer sb = new StringBuffer("[");
			for (AppRole role : roles) {
				sb.append("['" + role.getRoleId() + "','" + role.getRoleName()
						+ "'],");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			setJsonString(sb.toString());
		}
		return "success";
	}

	public String chooseRoles() {
		List<AppRole> chooseRoles = this.appRoleService.getAll();
		if (this.userId != null) {
			setAppUser((AppUser) this.appUserService.get(this.userId));
			Set<AppRole> selectedRoles = this.appUser.getRoles();
			for (AppRole role : selectedRoles) {
				chooseRoles.remove(role);
			}
		}
		StringBuffer sb = new StringBuffer("[");
		for (AppRole role : chooseRoles) {
			if (role.getStatus().shortValue() != 0) {
				sb.append("['" + role.getRoleId() + "','" + role.getRoleName()
						+ "'],");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		setJsonString(sb.toString());
		return "success";
	}

	@Action(description = "修改密码")
	public String resetPassword() {
		String userId = getRequest().getParameter("appUserUserId");
		String oldPassword = StringUtil.encryptSha256(getRequest()
				.getParameter("oldPassword"));
		String newPassword = getRequest().getParameter("newPassword");
		String againPassword = getRequest().getParameter("againPassword");
		if (StringUtils.isNotEmpty(userId))
			setAppUser((AppUser) this.appUserService.get(new Long(userId)));
		else {
			setAppUser(ContextUtil.getCurrentUser());
		}
		StringBuffer msg = new StringBuffer("{msg:'");
		boolean pass = false;
		if (oldPassword.equals(this.appUser.getPassword())) {
			if (newPassword.equals(againPassword))
				pass = true;
			else
				msg.append("两次输入不一致.'");
		} else
			msg.append("旧密码输入不正确.'");
		if (pass) {
			this.appUser.setPassword(StringUtil.encryptSha256(newPassword));
			this.appUserService.save(this.appUser);
			setJsonString("{success:true}");
		} else {
			msg.append(",failure:true}");
			setJsonString(msg.toString());
		}
		return "success";
	}

	@Action(description = "重置密码")
	public String createPassword() {
		String userId = getRequest().getParameter("appUserUserId");
		String newPassword = getRequest().getParameter("newpassword");
		String password = getRequest().getParameter("password");
		StringBuffer msg = new StringBuffer("{msg:'");
		if (StringUtils.isNotEmpty(userId))
			setAppUser((AppUser) this.appUserService.get(new Long(userId)));
		else {
			setAppUser(ContextUtil.getCurrentUser());
		}

		if (newPassword.equals(password)) {
			this.appUser.setPassword(StringUtil.encryptSha256(newPassword));
			this.appUserService.save(this.appUser);
			setJsonString("{success:true}");
		} else {
			msg.append("重置失败!,两次输入的密码不一致,请重新输入!.'");
			msg.append(",failure:true}");
			setJsonString(msg.toString());
		}

		return "success";
	}

	public String photo() {
		setAppUser((AppUser) this.appUserService.get(getUserId()));
		this.appUser.setPhoto("");
		this.appUserService.save(this.appUser);
		return "success";
	}

	public String subAdepartment() {
		PagingBean pb = getInitPagingBean();
		String strDepId = getRequest().getParameter("depId");
		String path = "0.";
		AppUser user = ContextUtil.getCurrentUser();
		if (StringUtils.isNotEmpty(strDepId)) {
			Long depId = Long.valueOf(Long.parseLong(strDepId));
			Department dep = (Department) this.departmentService.get(depId);
			if (dep != null)
				path = dep.getPath();
		} else {
			Department dep = user.getDepartment();
			if (dep != null) {
				path = dep.getPath();
			}
		}
		if ("0.".equals(path)) {
			path = null;
		}
		Long uId = user.getUserId();

		List list = this.appUserService.findSubAppUser(path, new HashSet(), pb);
		Type type = new TypeToken<List<AppUser> >() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String subArole() {
		String strRoleId = getRequest().getParameter("roleId");
		PagingBean pb = getInitPagingBean();
		AppUser user = ContextUtil.getCurrentUser();
		if (StringUtils.isNotEmpty(strRoleId)) {
			Long uId = user.getUserId();

			Type type = new TypeToken() {
			}.getType();
			StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
					.append(pb.getTotalItems()).append(",result:");
			Gson gson = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation().create();
			buff.append(gson.toJson(new ArrayList(), type));
			buff.append("}");
			this.jsonString = buff.toString();
		} else {
			this.jsonString = "{success:false}";
		}
		return "success";
	}

	public String onlineAsub() {
		Map<String, OnlineUser> onlineUsers = new HashMap();
		Map onlineUsersBySub = new HashMap();
		onlineUsers = AppUtil.getOnlineUsers();

		AppUser user = ContextUtil.getCurrentUser();
		Long uId = user.getUserId();
		Set userIds = new HashSet();
		userIds.add(uId);
		List<Long> userIdsL = new ArrayList();
		for (Long l : userIdsL) {
			userIds.add(l);
		}
		for (String sessionId : onlineUsers.keySet()) {
			OnlineUser onlineUser = new OnlineUser();
			onlineUser = (OnlineUser) onlineUsers.get(sessionId);
			if (!userIds.contains(onlineUser.getUserId())) {
				onlineUsersBySub.put(sessionId, onlineUser);
			}
		}
		Type type = new TypeToken() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(onlineUsers.size()).append(",result:");
		Gson gson = new Gson();
		buff.append(gson.toJson(onlineUsersBySub.values(), type));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String upUser() {
		Set set = this.relativeUserService.getUpUser(ContextUtil
				.getCurrentUserId());

		StringBuffer buff = new StringBuffer("[");
		for (Iterator it = set.iterator(); it.hasNext();) {
			AppUser user = (AppUser) it.next();
			buff.append("['" + user.getUserId().toString() + "','"
					+ user.getFullname() + "'],");
		}
		if (set.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		setJsonString(buff.toString());
		return "success";
	}

	@Action(description = "修改个人资料")
	public String profile() {
		AppUser old = ContextUtil.getCurrentUser();
		try {
			BeanUtil.copyNotNullProperties(old, this.appUser);
		} catch (Exception e) {
			this.logger.info(e);
		}
		this.appUserService.save(old);
		this.jsonString = "{success:true}";
		return "success";
	}

	public String bindDyPwd() {
		StringBuffer msg = new StringBuffer("{success:true,msg:'");
		String curDynamicPwd = getRequest().getParameter("curDynamicPwd");
		HashMap input = new HashMap();
		input.put("app", "demoauthapp");
		input.put("user", this.appUser.getDynamicPwd());
		input.put("pw", curDynamicPwd);

		String result = this.appUserService.initDynamicPwd(input, "bind");

		if (result.equals("ok")) {
			AppUser orgUser = (AppUser) this.appUserService.get(this.appUser
					.getUserId());
			orgUser.setDynamicPwd(this.appUser.getDynamicPwd());
			orgUser.setDyPwdStatus(AppUser.DYNPWD_STATUS_BIND);
			this.appUserService.save(orgUser);
			msg.append("成功绑定!");
		} else {
			msg.append(result);
		}
		msg.append("'}");
		setJsonString(msg.toString());
		return "success";
	}

	public String unbindDyPwd() {
		StringBuffer msg = new StringBuffer("{success:true,msg:'");
		String curDynamicPwd = getRequest().getParameter("curDynamicPwd");
		HashMap input = new HashMap();
		input.put("app", "demoauthapp");
		input.put("user", this.appUser.getDynamicPwd());
		input.put("pw", curDynamicPwd);

		String result = this.appUserService.initDynamicPwd(input, "unbind");

		if (result.equals("ok")) {
			AppUser orgUser = (AppUser) this.appUserService.get(this.appUser
					.getUserId());
			orgUser.setDyPwdStatus(AppUser.DYNPWD_STATUS_UNBIND);
			this.appUserService.save(orgUser);
			msg.append("解绑成功!");
		} else {
			msg.append(result);
		}
		msg.append("'}");
		setJsonString(msg.toString());
		return "success";
	}
}
