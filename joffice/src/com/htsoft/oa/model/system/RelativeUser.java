package com.htsoft.oa.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class RelativeUser extends BaseModel {
	public static Short SUPER_FLAG_TRUE = Short.valueOf((short) 1);

	public static Short SUPER_FLAG_FALSE = Short.valueOf((short) 0);
	protected Long relativeUserId;
	protected Short isSuper;
	protected RelativeJob relativeJob;
	protected AppUser appUser;
	protected AppUser jobUser;

	public RelativeUser() {
	}

	public RelativeUser(Long in_relativeUserId) {
		setRelativeUserId(in_relativeUserId);
	}

	public RelativeJob getRelativeJob() {
		return this.relativeJob;
	}

	public void setRelativeJob(RelativeJob in_relativeJob) {
		this.relativeJob = in_relativeJob;
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser in_appUser) {
		this.appUser = in_appUser;
	}

	public Long getRelativeUserId() {
		return this.relativeUserId;
	}

	public void setRelativeUserId(Long aValue) {
		this.relativeUserId = aValue;
	}

	public Long getReJobId() {
		return getRelativeJob() == null ? null : getRelativeJob().getReJobId();
	}

	public void setReJobId(Long aValue) {
		if (aValue == null) {
			this.relativeJob = null;
		} else if (this.relativeJob == null) {
			this.relativeJob = new RelativeJob(aValue);
			this.relativeJob.setVersion(new Integer(0));
		} else {
			this.relativeJob.setReJobId(aValue);
		}
	}

	public Long getUserId() {
		return getAppUser() == null ? null : getAppUser().getUserId();
	}

	public void setUserId(Long aValue) {
		if (aValue == null) {
			this.appUser = null;
		} else if (this.appUser == null) {
			this.appUser = new AppUser(aValue);
			this.appUser.setVersion(new Integer(0));
		} else {
			this.appUser.setUserId(aValue);
		}
	}

	public Short getIsSuper() {
		return this.isSuper;
	}

	public void setIsSuper(Short aValue) {
		this.isSuper = aValue;
	}

	public AppUser getJobUser() {
		return this.jobUser;
	}

	public void setJobUser(AppUser jobUser) {
		this.jobUser = jobUser;
	}

	public boolean equals(Object object) {
		if (!(object instanceof RelativeUser)) {
			return false;
		}
		RelativeUser rhs = (RelativeUser) object;
		return new EqualsBuilder()
				.append(this.relativeUserId, rhs.relativeUserId)
				.append(this.jobUser, rhs.jobUser)
				.append(this.isSuper, rhs.isSuper)
				.append(this.appUser, rhs.appUser)
				.append(this.relativeJob, rhs.relativeJob).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.relativeUserId).append(this.jobUser)
				.append(this.isSuper).append(this.appUser)
				.append(this.relativeJob).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this)
				.append("relativeUserId", this.relativeUserId)
				.append("jobUser", this.jobUser)
				.append("isSuper", this.isSuper)
				.append("appUser", this.appUser)
				.append("relativeJob", this.relativeJob).toString();
	}
}
