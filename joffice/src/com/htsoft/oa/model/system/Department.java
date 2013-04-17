package com.htsoft.oa.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;

public class Department extends BaseModel {

	@Expose
	protected Long depId;

	@Expose
	protected String depName;

	@Expose
	protected String depDesc;

	@Expose
	protected Integer depLevel;

	@Expose
	protected Long parentId;

	@Expose
	protected String path;

	public Department() {
	}

	public Department(Long depId) {
		setDepId(depId);
	}

	public Long getDepId() {
		return this.depId;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}

	public String getDepName() {
		return this.depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getDepDesc() {
		return this.depDesc;
	}

	public void setDepDesc(String depDesc) {
		this.depDesc = depDesc;
	}

	public Integer getDepLevel() {
		return this.depLevel;
	}

	public void setDepLevel(Integer depLevel) {
		this.depLevel = depLevel;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.depId)
				.append(this.depName).append(this.depDesc)
				.append(this.depLevel).append(this.parentId).append(this.path)
				.toHashCode();
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Department))
			return false;
		Department dep = (Department) obj;
		return new EqualsBuilder().append(this.depId, dep.depId)
				.append(this.depName, dep.depName)
				.append(this.depDesc, dep.depDesc)
				.append(this.depLevel, dep.depLevel)
				.append(this.parentId, dep.parentId)
				.append(this.path, dep.path).isEquals();
	}

	public String toString() {
		return new ToStringBuilder(this).append("depId", this.depId)
				.append("depName", this.depName)
				.append("depDesc", this.depDesc)
				.append("depLevel", this.depLevel)
				.append("parentId", this.parentId).append("path", this.path)
				.toString();
	}
}
