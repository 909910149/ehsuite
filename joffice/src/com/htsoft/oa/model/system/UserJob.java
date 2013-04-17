package com.htsoft.oa.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;
import com.htsoft.oa.model.hrm.Job;

public class UserJob extends BaseModel {
	public static final Short ISMIAN = Short.valueOf((short) 1);

	@Expose
	protected Long userJobId;

	@Expose
	protected Short isMain;

	@Expose
	protected Job job;

	@Expose
	protected AppUser appUser;

	public UserJob() {
	}

	public UserJob(Long in_userJobId) {
		setUserJobId(in_userJobId);
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job in_job) {
		this.job = in_job;
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser in_appUser) {
		this.appUser = in_appUser;
	}

	public Long getUserJobId() {
		return this.userJobId;
	}

	public void setUserJobId(Long aValue) {
		this.userJobId = aValue;
	}

	public Short getIsMain() {
		return this.isMain;
	}

	public void setIsMain(Short aValue) {
		this.isMain = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof UserJob)) {
			return false;
		}
		UserJob rhs = (UserJob) object;
		return new EqualsBuilder().append(this.userJobId, rhs.userJobId)
				.append(this.isMain, rhs.isMain).append(this.job, rhs.job)
				.append(this.appUser, rhs.appUser).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.userJobId).append(this.isMain).append(this.job)
				.append(this.appUser).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("userJobId", this.userJobId)
				.append("isMain", this.isMain).append("job", this.job)
				.append("appUser", this.appUser).toString();
	}
}
