package com.htsoft.oa.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;

public class Dictionary extends BaseModel {

	@Expose
	protected Long dicId;

	@Expose
	protected String itemName;

	@Expose
	protected String itemValue;

	@Expose
	protected String descp;

	@Expose
	protected Short sn;

	@Expose
	protected GlobalType globalType;

	@Expose
	protected Long proTypeId;

	public Long getProTypeId() {
		return this.proTypeId;
	}

	public void setProTypeId(Long aValue) {
		if (aValue == null)
			this.globalType = null;
		else if (this.globalType == null) {
			this.globalType = new GlobalType(aValue);
		} else
			this.globalType.setProTypeId(aValue);
	}

	public Dictionary() {
	}

	public Dictionary(Long in_dicId) {
		setDicId(in_dicId);
	}

	public Long getDicId() {
		return this.dicId;
	}

	public void setDicId(Long aValue) {
		this.dicId = aValue;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String aValue) {
		this.itemName = aValue;
	}

	public String getItemValue() {
		return this.itemValue;
	}

	public void setItemValue(String aValue) {
		this.itemValue = aValue;
	}

	public String getDescp() {
		return this.descp;
	}

	public void setDescp(String aValue) {
		this.descp = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof Dictionary)) {
			return false;
		}
		Dictionary rhs = (Dictionary) object;
		return new EqualsBuilder().append(this.dicId, rhs.dicId)
				.append(this.itemName, rhs.itemName)
				.append(this.itemValue, rhs.itemValue)
				.append(this.descp, rhs.descp).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.dicId)
				.append(this.itemName).append(this.itemValue)
				.append(this.descp).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("dicId", this.dicId)
				.append("itemName", this.itemName)
				.append("itemValue", this.itemValue)
				.append("descp", this.descp).toString();
	}

	public GlobalType getGlobalType() {
		return this.globalType;
	}

	public void setGlobalType(GlobalType globalType) {
		this.globalType = globalType;
	}

	public Short getSn() {
		return this.sn;
	}

	public void setSn(Short sn) {
		this.sn = sn;
	}
}
