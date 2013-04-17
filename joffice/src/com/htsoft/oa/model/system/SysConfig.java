package com.htsoft.oa.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class SysConfig extends BaseModel {
	public static final String DYPWD_OPEN = "1";
	public static final String DYPWD_COLSE = "2";
	public static final String CODE_OPEN = "1";
	public static final String CODE_COLSE = "2";
	public static final String SMS_OPEN = "1";
	public static final String SMS_COLSE = "2";
	public static final Short SYS_DATA_TYPE_INTEGER = Short.valueOf((short) 2);
	protected Long configId;
	protected String configKey;
	protected String configName;
	protected String configDesc;
	protected String typeName;
	protected Short dataType;
	protected String dataValue;
	protected String typeKey;

	public String getDataValue() {
		return this.dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public SysConfig() {
	}

	public SysConfig(Long in_configId) {
		setConfigId(in_configId);
	}

	public Long getConfigId() {
		return this.configId;
	}

	public void setConfigId(Long aValue) {
		this.configId = aValue;
	}

	public String getConfigKey() {
		return this.configKey;
	}

	public void setConfigKey(String aValue) {
		this.configKey = aValue;
	}

	public String getConfigName() {
		return this.configName;
	}

	public void setConfigName(String aValue) {
		this.configName = aValue;
	}

	public String getConfigDesc() {
		return this.configDesc;
	}

	public void setConfigDesc(String aValue) {
		this.configDesc = aValue;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String aValue) {
		this.typeName = aValue;
	}

	public Short getDataType() {
		return this.dataType;
	}

	public void setDataType(Short aValue) {
		this.dataType = aValue;
	}

	public String getTypeKey() {
		return this.typeKey;
	}

	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}

	public boolean equals(Object object) {
		if (!(object instanceof SysConfig)) {
			return false;
		}
		SysConfig rhs = (SysConfig) object;
		return new EqualsBuilder().append(this.configId, rhs.configId)
				.append(this.configKey, rhs.configKey)
				.append(this.configName, rhs.configName)
				.append(this.configDesc, rhs.configDesc)
				.append(this.typeName, rhs.typeName)
				.append(this.dataType, rhs.dataType)
				.append(this.typeKey, rhs.typeKey).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.configId)
				.append(this.configKey).append(this.configName)
				.append(this.configDesc).append(this.typeName)
				.append(this.dataType).append(this.typeKey).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("configId", this.configId)
				.append("configKey", this.configKey)
				.append("configName", this.configName)
				.append("configDesc", this.configDesc)
				.append("typeName", this.typeName)
				.append("dataType", this.dataType)
				.append("typeKey", this.typeKey).toString();
	}
}
