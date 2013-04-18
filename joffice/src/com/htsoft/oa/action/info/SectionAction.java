package com.htsoft.oa.action.info;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.info.Section;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.SysConfig;
import com.htsoft.oa.service.info.SectionService;
import com.htsoft.oa.service.system.SysConfigService;

public class SectionAction extends BaseAction {

	@Resource
	private SectionService sectionService;

	@Resource
	private SysConfigService sysConfigService;
	private Section section;
	private Long sectionId;

	public Long getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addSorted("rowNumber", "asc");
		List<Section> list = this.sectionService.getAll(filter);

		Type type = new TypeToken<List<Section>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		buff.append(gson.toJson(list, type));
		SysConfig sectionColumn = this.sysConfigService
				.findByKey("sectionColumn");
		buff.append(",columnType:");
		if (sectionColumn != null)
			buff.append(sectionColumn.getDataValue());
		else {
			buff.append("2");
		}
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.sectionService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		Section section = (Section) this.sectionService.get(this.sectionId);

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd").create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(section));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		StringBuffer result = new StringBuffer("{success:true");
		if (this.section.getSectionId() == null) {
			AppUser curUser = ContextUtil.getCurrentUser();
			this.section.setCreatetime(new Date());
			this.section.setUserId(curUser.getUserId());
			this.section.setUsername(curUser.getFullname());

			this.section.setColNumber(Section.COLUMN_ONE);
			this.section.setRowNumber(Integer.valueOf(this.sectionService
					.getLastColumn().intValue() + 1));
			this.sectionService.save(this.section);

			Gson gson = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation()
					.setDateFormat("yyyy-MM-dd").create();
			result.append(",data:").append(gson.toJson(this.section))
					.append("}");
		} else {
			result.append("}");
			Section orgSection = (Section) this.sectionService.get(this.section
					.getSectionId());
			try {
				BeanUtil.copyNotNullProperties(orgSection, this.section);
				this.sectionService.save(orgSection);
			} catch (Exception ex) {
				this.logger.error(ex.getMessage());
			}
		}

		setJsonString(result.toString());
		return "success";
	}

	public String disable() {
		String sectionId = getRequest().getParameter("sectionId");
		if (StringUtils.isNotEmpty(sectionId)) {
			this.section = ((Section) this.sectionService.get(new Long(
					sectionId)));
		}
		if (this.section != null) {
			this.section.setStatus(Section.STATUS_DISABLE);
			this.sectionService.save(this.section);
		}
		setJsonString("{success:true}");
		return "success";
	}

	public String enable() {
		String secIds = getRequest().getParameter("secIds");
		if (StringUtils.isNotEmpty(secIds)) {
			String[] ids = secIds.split(",");
			for (String sectionId : ids) {
				this.section = ((Section) this.sectionService.get(new Long(
						sectionId)));
				if (this.section != null) {
					this.section.setStatus(Section.STATUS_ENABLE);
					this.sectionService.save(this.section);
				}
			}
		}
		setJsonString("{success:true}");
		return "success";
	}

	public String position() {
		String items = getRequest().getParameter("sections");
		Gson gson = new Gson();
		Section[] sections = (Section[]) gson.fromJson(items,
				com.htsoft.oa.model.info.Section[].class);
		AppUser user = ContextUtil.getCurrentUser();

		for (Section sec : sections) {
			Section orgSection = (Section) this.sectionService.get(sec
					.getSectionId());
			orgSection.setColNumber(sec.getColNumber());
			orgSection.setRowNumber(sec.getRowNumber());
			orgSection.setStatus(Section.STATUS_ENABLE);
			this.sectionService.save(orgSection);
		}
		setJsonString("{success:true}");
		return "success";
	}

	public String column() {
		String columnType = getRequest().getParameter("columnType");
		SysConfig sectionColumn = this.sysConfigService
				.findByKey("sectionColumn");
		if (sectionColumn == null) {
			sectionColumn = new SysConfig();
			sectionColumn.setConfigDesc("栏目列数配置");
			sectionColumn.setConfigKey("sectionColumn");
			sectionColumn.setConfigName("栏目列数");
			sectionColumn.setDataType(SysConfig.SYS_DATA_TYPE_INTEGER);
			sectionColumn.setDataValue(columnType);
			sectionColumn.setTypeKey("sectionColumn");
			sectionColumn.setTypeName("栏目列数配置");
			this.sysConfigService.save(sectionColumn);
		} else {
			sectionColumn.setDataValue(columnType);
			this.sysConfigService.save(sectionColumn);
		}

		setJsonString("{success:true}");
		return "success";
	}
}
