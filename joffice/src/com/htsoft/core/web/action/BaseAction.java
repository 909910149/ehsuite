package com.htsoft.core.web.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htsoft.core.engine.MailEngine;
import com.htsoft.core.web.paging.PagingBean;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.mail.MailSender;

public class BaseAction {
	public static final String SUCCESS = "success";
	public static final String INPUT = "input";
	private String successResultValue = "/jsonString.jsp";
	public static final String JSON_SUCCESS = "{success:true}";
	protected String dir;
	protected String sort;
	protected Integer limit = Integer.valueOf(25);

	protected Integer start = Integer.valueOf(0);

	protected String jsonString = "{success:true}";
	private static final long serialVersionUID = 1L;
	protected final transient Log logger = LogFactory.getLog(getClass());
	protected MailEngine mailEngine;
	protected MailSender mailSender;
	public final String CANCEL = "cancel";

	public final String VIEW = "view";

	public String getSuccessResultValue() {
		return this.successResultValue;
	}

	public void setSuccessResultValue(String successResultValue) {
		this.successResultValue = successResultValue;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public String getJsonString() {
		return this.jsonString;
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	protected PagingBean getInitPagingBean() {
		PagingBean pb = new PagingBean(this.start.intValue(),
				this.limit.intValue());
		return pb;
	}

	public void setMailEngine(MailEngine mailEngine) {
		this.mailEngine = mailEngine;
	}

	public MailEngine getMailEngine() {
		return this.mailEngine;
	}

	public String list() {
		return "success";
	}

	public String edit() {
		return "input";
	}

	public String save() {
		return "input";
	}

	public String delete() {
		return "success";
	}

	public String multiDelete() {
		return "success";
	}

	public String multiSave() {
		return "success";
	}

	public String getDir() {
		return this.dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getLimit() {
		return this.limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getStart() {
		return this.start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public String execute() throws Exception {
		HttpServletRequest request = getRequest();
		String uri = request.getRequestURI();
		String url = uri.substring(request.getContextPath().length());
		url = url.replace(".do", ".jsp");
		url = "/pages" + url;

		if (this.logger.isInfoEnabled()) {
			this.logger.info("forward url:" + url);
		}
		setSuccessResultValue(url);
		return "success";
	}

	public String gsonFormat(List listData, int totalItems,
			boolean onlyIncludeExpose) {
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(totalItems).append(",result:");

		Gson gson = null;
		if (onlyIncludeExpose)
			gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
					.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		else {
			gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
		}
		buff.append(gson.toJson(listData));

		buff.append("}");

		return buff.toString();
	}

	public String gsonFormat(List listData, int totalItems) {
		return gsonFormat(listData, totalItems, false);
	}
}
