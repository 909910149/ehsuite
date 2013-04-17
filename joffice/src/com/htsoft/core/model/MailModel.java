package com.htsoft.core.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class MailModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String subject;
	private String from;
	private String to;
	private Date sendDate;
	private String content;
	private String mailTemplate;
	private Map mailData = null;

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return this.to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMailTemplate() {
		return this.mailTemplate;
	}

	public void setMailTemplate(String mailTemplate) {
		this.mailTemplate = mailTemplate;
	}

	public Map getMailData() {
		return this.mailData;
	}

	public void setMailData(Map mailData) {
		this.mailData = mailData;
	}
}
