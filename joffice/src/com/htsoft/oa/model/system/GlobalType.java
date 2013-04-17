package com.htsoft.oa.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class GlobalType extends BaseModel {
	public static final String CAT_PRODUCT_TYPE = "PT";
	public static final String CAT_CAL_UNIT = "CT";
	public static final String CAT_DICTIONARY = "DIC";
	public static final String CAT_BANK_CASH = "BKCH";
	public static final String CAT_ARCH_FOND = "AR_FD";
	public static final String CAT_ARCH_ROLL = "AR_RL";
	public static final String CAT_ROLL_FILE = "AR_RLF";
	protected Long proTypeId;
	protected String typeName;
	protected String path;
	protected Integer depth;
	protected Long parentId;
	protected String nodeKey;
	protected String catKey;
	protected Integer sn;
	protected Long userId;

	public GlobalType() {
	}

	public GlobalType(Long in_proTypeId) {
		setProTypeId(in_proTypeId);
	}

	public Long getProTypeId() {
		return this.proTypeId;
	}

	public void setProTypeId(Long aValue) {
		this.proTypeId = aValue;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String aValue) {
		this.typeName = aValue;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String aValue) {
		this.path = aValue;
	}

	public Integer getDepth() {
		return this.depth;
	}

	public void setDepth(Integer aValue) {
		this.depth = aValue;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long aValue) {
		this.parentId = aValue;
	}

	public String getNodeKey() {
		return this.nodeKey;
	}

	public void setNodeKey(String aValue) {
		this.nodeKey = aValue;
	}

	public String getCatKey() {
		return this.catKey;
	}

	public void setCatKey(String aValue) {
		this.catKey = aValue;
	}

	public Integer getSn() {
		return this.sn;
	}

	public void setSn(Integer aValue) {
		this.sn = aValue;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean equals(Object object) {
		if (!(object instanceof GlobalType)) {
			return false;
		}
		GlobalType rhs = (GlobalType) object;
		return new EqualsBuilder().append(this.proTypeId, rhs.proTypeId)
				.append(this.typeName, rhs.typeName)
				.append(this.path, rhs.path).append(this.depth, rhs.depth)
				.append(this.parentId, rhs.parentId)
				.append(this.nodeKey, rhs.nodeKey)
				.append(this.catKey, rhs.catKey).append(this.sn, rhs.sn)
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.proTypeId).append(this.typeName).append(this.path)
				.append(this.depth).append(this.parentId).append(this.nodeKey)
				.append(this.catKey).append(this.sn).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("proTypeId", this.proTypeId)
				.append("typeName", this.typeName).append("path", this.path)
				.append("depth", this.depth).append("parentId", this.parentId)
				.append("nodeKey", this.nodeKey).append("catKey", this.catKey)
				.append("sn", this.sn).toString();
	}
}
