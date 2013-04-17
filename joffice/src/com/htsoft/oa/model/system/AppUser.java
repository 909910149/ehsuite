package com.htsoft.oa.model.system;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

import org.jbpm.api.identity.User;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

import com.google.gson.annotations.Expose;
import com.htsoft.core.menu.TopModule;
import com.htsoft.core.model.BaseModel;

public class AppUser extends BaseModel implements UserDetails, User {
	public static final Long SYSTEM_USER = new Long(-1L);

	public static final Long SUPER_USER = new Long(1L);

	public static final Short DYNPWD_STATUS_BIND = Short.valueOf((short) 1);
	public static final Short DYNPWD_STATUS_UNBIND = Short.valueOf((short) 0);

	@Expose
	protected Long userId;

	@Expose
	protected String username;
	protected String password;

	@Expose
	protected String email;

	@Expose
	protected Department department;

	@Expose
	protected Long jobId;

	@Expose
	protected String phone;

	@Expose
	protected String mobile;

	@Expose
	protected String fax;

	@Expose
	protected String address;

	@Expose
	protected String zip;

	@Expose
	protected String photo;

	@Expose
	protected Date accessionTime;

	@Expose
	protected Short status;

	@Expose
	protected String education;

	@Expose
	protected Short title;

	@Expose
	protected String fullname;

	@Expose
	protected Short delFlag;

	@Expose
	protected String dynamicPwd;

	@Expose
	protected Short dyPwdStatus;

	@XmlTransient
	protected Set<AppRole> roles;
	private Map<String, TopModule> topModules = new LinkedHashMap();

	protected Set<String> rights = new HashSet();

	public Set<String> getRights() {
		return this.rights;
	}

	public Map<String, TopModule> getTopModules() {
		return this.topModules;
	}

	public void setTopModules(Map<String, TopModule> topModules) {
		this.topModules = topModules;
	}

	public String getFunctionRights() {
		StringBuffer sb = new StringBuffer();

		Iterator it = this.rights.iterator();

		while (it.hasNext()) {
			sb.append((String) it.next()).append(",");
		}

		if (this.rights.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString();
	}

	public void setRights(Set<String> rights) {
		this.rights = rights;
	}

	public AppUser() {
	}

	public AppUser(Long in_userId) {
		setUserId(in_userId);
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long aValue) {
		this.userId = aValue;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String aValue) {
		this.username = aValue;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String aValue) {
		this.password = aValue;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String aValue) {
		this.email = aValue;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getJobId() {
		return this.jobId;
	}

	public void setJobId(Long aValue) {
		this.jobId = aValue;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String aValue) {
		this.phone = aValue;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String aValue) {
		this.mobile = aValue;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String aValue) {
		this.fax = aValue;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String aValue) {
		this.address = aValue;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String aValue) {
		this.zip = aValue;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String aValue) {
		this.photo = aValue;
	}

	public Date getAccessionTime() {
		return this.accessionTime;
	}

	public void setAccessionTime(Date aValue) {
		this.accessionTime = aValue;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short aValue) {
		this.status = aValue;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String aValue) {
		this.education = aValue;
	}

	public Short getTitle() {
		return this.title;
	}

	public void setTitle(Short aValue) {
		this.title = aValue;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String aValue) {
		this.fullname = aValue;
	}

	public Short getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Short delFlag) {
		this.delFlag = delFlag;
	}

	public String getDynamicPwd() {
		return this.dynamicPwd;
	}

	public void setDynamicPwd(String dynamicPwd) {
		this.dynamicPwd = dynamicPwd;
	}

	public Short getDyPwdStatus() {
		return this.dyPwdStatus;
	}

	public void setDyPwdStatus(Short dyPwdStatus) {
		this.dyPwdStatus = dyPwdStatus;
	}

	public String getFirstKeyColumnName() {
		return "userId";
	}

	public Set<AppRole> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<AppRole> roles) {
		this.roles = roles;
	}

	public GrantedAuthority[] getAuthorities() {
		GrantedAuthority[] rights = (GrantedAuthority[]) this.roles
				.toArray(new GrantedAuthority[this.roles.size() + 1]);
		rights[(rights.length - 1)] = new GrantedAuthorityImpl("ROLE_PUBLIC");
		return rights;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return this.status.shortValue() == 1;
	}

	public String getId() {
		return this.userId.toString();
	}

	public String getBusinessEmail() {
		return this.email;
	}

	public String getFamilyName() {
		return this.fullname;
	}

	public String getGivenName() {
		return this.fullname;
	}

	public boolean isSupperManage() {
		Set roles = getRoles();
		boolean flag = false;
		for (Iterator it = roles.iterator(); it.hasNext();) {
			AppRole role = (AppRole) it.next();
			if (role.getRoleId().shortValue() != AppRole.SUPER_ROLEID
					.shortValue())
				continue;
			flag = true;
		}

		return flag;
	}
}
