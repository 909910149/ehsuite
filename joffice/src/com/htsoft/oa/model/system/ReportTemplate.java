package com.htsoft.oa.model.system;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class ReportTemplate extends BaseModel {
	protected Long reportId;
	protected String title;
	protected String descp;
	protected String reportLocation;
	protected Date createtime;
	protected Date updatetime;
	private Short isDefaultIn;
	protected String reportKey;

	public String getReportKey() {
		return this.reportKey;
	}

	public void setReportKey(String reportKey) {
		this.reportKey = reportKey;
	}

	public Short getIsDefaultIn() {
		return this.isDefaultIn;
	}

	public void setIsDefaultIn(Short isDefaultIn) {
		this.isDefaultIn = isDefaultIn;
	}

	public ReportTemplate() {
	}

	public ReportTemplate(Long in_reportId) {
		setReportId(in_reportId);
	}

	public Long getReportId() {
		return this.reportId;
	}

	public void setReportId(Long aValue) {
		this.reportId = aValue;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String aValue) {
		this.title = aValue;
	}

	public String getDescp() {
		return this.descp;
	}

	public void setDescp(String aValue) {
		this.descp = aValue;
	}

	public String getReportLocation() {
		return this.reportLocation;
	}

	public void setReportLocation(String aValue) {
		this.reportLocation = aValue;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date aValue) {
		this.createtime = aValue;
	}

	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date aValue) {
		this.updatetime = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof ReportTemplate)) {
			return false;
		}
		ReportTemplate rhs = (ReportTemplate) object;
		return new EqualsBuilder().append(this.reportId, rhs.reportId)
				.append(this.title, rhs.title).append(this.descp, rhs.descp)
				.append(this.reportLocation, rhs.reportLocation)
				.append(this.createtime, rhs.createtime)
				.append(this.updatetime, rhs.updatetime).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.reportId)
				.append(this.title).append(this.descp)
				.append(this.reportLocation).append(this.createtime)
				.append(this.updatetime).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("reportId", this.reportId)
				.append("title", this.title).append("descp", this.descp)
				.append("reportLocation", this.reportLocation)
				.append("createtime", this.createtime)
				.append("updatetime", this.updatetime).toString();
	}

	public String getFirstKeyColumnName() {
		return "reportId";
	}

	public Long getId() {
		return this.reportId;
	}
}
