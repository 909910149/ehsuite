package com.htsoft.oa.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class DepUsers extends BaseModel {
	public static final Short ISMAIN = Short.valueOf((short) 1);
	protected Long depUserId;
	protected Short isMain;
	protected Integer sn;
	protected Department department;
	protected AppUser appUser;

	public DepUsers() {
	}

	public DepUsers(Long in_depUserId) {
		setDepUserId(in_depUserId);
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department in_department) {
		this.department = in_department;
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser in_appUser) {
		this.appUser = in_appUser;
	}

	public Long getDepUserId() {
		return this.depUserId;
	}

	public void setDepUserId(Long aValue) {
		this.depUserId = aValue;
	}

	public Long getUserid() {
		return getAppUser() == null ? null : getAppUser().getUserId();
	}

	public void setUserid(Long aValue) {
		if (aValue == null) {
			this.appUser = null;
		} else if (this.appUser == null) {
			this.appUser = new AppUser(aValue);
			this.appUser.setVersion(new Integer(0));
		} else {
			this.appUser.setUserId(aValue);
		}
	}

	public Long getDepid() {
		return getDepartment() == null ? null : getDepartment().getDepId();
	}

	public void setDepid(Long aValue) {
		if (aValue == null) {
			this.department = null;
		} else if (this.department == null) {
			this.department = new Department(aValue);
			this.department.setVersion(new Integer(0));
		} else {
			this.department.setDepId(aValue);
		}
	}

	public Short getIsMain() {
		return this.isMain;
	}

	public void setIsMain(Short aValue) {
		this.isMain = aValue;
	}

	public Integer getSn() {
		return this.sn;
	}

	public void setSn(Integer aValue) {
		this.sn = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof DepUsers)) {
			return false;
		}
		DepUsers rhs = (DepUsers) object;
		return new EqualsBuilder().append(this.depUserId, rhs.depUserId)
				.append(this.isMain, rhs.isMain).append(this.sn, rhs.sn)
				.append(this.department, rhs.department)
				.append(this.appUser, rhs.appUser).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.depUserId).append(this.isMain).append(this.sn)
				.append(this.department).append(this.appUser).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("depUserId", this.depUserId)
				.append("isMain", this.isMain).append("sn", this.sn)
				.append("department", this.department)
				.append("appUser", this.appUser).toString();
	}
}
