package com.htsoft.oa.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class RelativeJob extends BaseModel {
	protected Long reJobId;
	protected String jobName;
	protected String jobCode;
	protected Long parent;
	protected String path;
	protected Integer depath;

	public RelativeJob() {
	}

	public RelativeJob(Long in_reJobId) {
		setReJobId(in_reJobId);
	}

	public Long getReJobId() {
		return this.reJobId;
	}

	public void setReJobId(Long aValue) {
		this.reJobId = aValue;
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String aValue) {
		this.jobName = aValue;
	}

	public String getJobCode() {
		return this.jobCode;
	}

	public void setJobCode(String aValue) {
		this.jobCode = aValue;
	}

	public Long getParent() {
		return this.parent;
	}

	public void setParent(Long aValue) {
		this.parent = aValue;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String aValue) {
		this.path = aValue;
	}

	public Integer getDepath() {
		return this.depath;
	}

	public void setDepath(Integer aValue) {
		this.depath = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof RelativeJob)) {
			return false;
		}
		RelativeJob rhs = (RelativeJob) object;
		return new EqualsBuilder().append(this.reJobId, rhs.reJobId)
				.append(this.jobName, rhs.jobName)
				.append(this.jobCode, rhs.jobCode)
				.append(this.parent, rhs.parent).append(this.path, rhs.path)
				.append(this.depath, rhs.depath).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.reJobId)
				.append(this.jobName).append(this.jobCode).append(this.parent)
				.append(this.path).append(this.depath).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("reJobId", this.reJobId)
				.append("jobName", this.jobName)
				.append("jobCode", this.jobCode).append("parent", this.parent)
				.append("path", this.path).append("depath", this.depath)
				.toString();
	}
}
