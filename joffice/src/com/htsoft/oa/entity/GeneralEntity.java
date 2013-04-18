package com.htsoft.oa.entity;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "swf_general")
public class GeneralEntity extends FormEntity {
	private static final long serialVersionUID = -394073212728617070L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long entityId;
	private String itemSubject;
	private String itemDescp;
	private Date createtime;
	private Long runId;

	public Long getEntityId() {
		return this.entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getItemSubject() {
		return this.itemSubject;
	}

	public void setItemSubject(String itemSubject) {
		this.itemSubject = itemSubject;
	}

	public String getItemDescp() {
		return this.itemDescp;
	}

	public void setItemDescp(String itemDescp) {
		this.itemDescp = itemDescp;
	}

	public Long getRunId() {
		return this.runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getHtml() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer sb = new StringBuffer();
		sb.append("<b>事项标题:</b>" + this.itemSubject + "<br/><b>事项描述:</b>"
				+ this.itemDescp);
		if (this.createtime != null) {
			sb.append("<br/>创建时间:").append(sdf.format(this.createtime));
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Class cls = GeneralEntity.class;
		System.out.println("name:" + cls.getName());
		GeneralEntity gen = new GeneralEntity();
		gen.setEntityId(Long.valueOf(10L));
		gen.setItemSubject("title");
		gen.setItemDescp("descpe");

		System.out.println("html:" + gen.getHtml());
	}
}
