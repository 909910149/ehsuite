package com.htsoft.oa.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class TypeKey extends BaseModel {
	protected Long typekeyId;
	protected String typeName;
	protected String typeKey;
	protected Integer sn;

	public TypeKey() {
	}

	public TypeKey(Long in_typekeyId) {
		setTypekeyId(in_typekeyId);
	}

	public Long getTypekeyId() {
		return this.typekeyId;
	}

	public void setTypekeyId(Long aValue) {
		this.typekeyId = aValue;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String aValue) {
		this.typeName = aValue;
	}

	public String getTypeKey() {
		return this.typeKey;
	}

	public void setTypeKey(String aValue) {
		this.typeKey = aValue;
	}

	public Integer getSn() {
		return this.sn;
	}

	public void setSn(Integer aValue) {
		this.sn = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof TypeKey)) {
			return false;
		}
		TypeKey rhs = (TypeKey) object;
		return new EqualsBuilder().append(this.typekeyId, rhs.typekeyId)
				.append(this.typeName, rhs.typeName)
				.append(this.typeKey, rhs.typeKey).append(this.sn, rhs.sn)
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.typekeyId).append(this.typeName)
				.append(this.typeKey).append(this.sn).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("typekeyId", this.typekeyId)
				.append("typeName", this.typeName)
				.append("typeKey", this.typeKey).append("sn", this.sn)
				.toString();
	}
}
