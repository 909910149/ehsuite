package com.htsoft.oa.model.system;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;

public class FileAttach extends BaseModel {
	public static final Integer FLAG_DEL = Integer.valueOf(1);

	public static final Integer FLAG_NOT_DEL = Integer.valueOf(0);

	@Expose
	protected Long fileId;

	@Expose
	protected String fileName;

	@Expose
	protected String filePath;

	@Expose
	protected Date createtime;

	@Expose
	protected String ext;

	@Expose
	protected String fileType;

	@Expose
	protected String note;

	@Expose
	protected String creator;

	@Expose
	protected Long creatorId;

	@Expose
	protected Long totalBytes;

	@Expose
	protected Integer delFlag;

	public FileAttach() {
	}

	public FileAttach(Long in_fileId) {
		setFileId(in_fileId);
	}

	public Long getFileId() {
		return this.fileId;
	}

	public void setFileId(Long aValue) {
		this.fileId = aValue;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String aValue) {
		this.fileName = aValue;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String aValue) {
		this.filePath = aValue;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date aValue) {
		this.createtime = aValue;
	}

	public String getExt() {
		return this.ext;
	}

	public void setExt(String aValue) {
		this.ext = aValue;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String aValue) {
		this.fileType = aValue;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String aValue) {
		this.note = aValue;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String aValue) {
		this.creator = aValue;
	}

	public Long getTotalBytes() {
		return this.totalBytes;
	}

	public void setTotalBytes(Long totalBytes) {
		this.totalBytes = totalBytes;
	}

	public Integer getDelFlag() {
		return this.delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Long getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public boolean equals(Object object) {
		if (!(object instanceof FileAttach)) {
			return false;
		}
		FileAttach rhs = (FileAttach) object;
		return new EqualsBuilder().append(this.fileId, rhs.fileId)
				.append(this.fileName, rhs.fileName)
				.append(this.filePath, rhs.filePath)
				.append(this.createtime, rhs.createtime)
				.append(this.ext, rhs.ext).append(this.fileType, rhs.fileType)
				.append(this.note, rhs.note).append(this.creator, rhs.creator)
				.append(this.delFlag, rhs.delFlag)
				.append(this.creatorId, rhs.creatorId).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.fileId)
				.append(this.fileName).append(this.filePath)
				.append(this.createtime).append(this.ext).append(this.fileType)
				.append(this.note).append(this.creator).append(this.delFlag)
				.append(this.creatorId).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("fileId", this.fileId)
				.append("fileName", this.fileName)
				.append("filePath", this.filePath)
				.append("createtime", this.createtime).append("ext", this.ext)
				.append("type", this.fileType).append("note", this.note)
				.append("creator", this.creator)
				.append("creatorId", this.creatorId)
				.append("delFlag", this.delFlag).toString();
	}

	public String getFirstKeyColumnName() {
		return "fileId";
	}

	public Long getId() {
		return this.fileId;
	}
}
