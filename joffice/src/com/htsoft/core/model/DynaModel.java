package com.htsoft.core.model;

import com.htsoft.oa.model.flow.FormField;
import com.htsoft.oa.model.flow.FormTable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

public class DynaModel {
	private String primaryFieldName;
	private String subjectFieldName;
	private String entityName;
	private Map<String, Object> datas = new HashMap();

	private Map<String, Class> types = new HashMap();

	private Map<String, String> formats = new HashMap();

	private Map<String, String> labels = new HashMap();

	public DynaModel() {
	}

	public DynaModel(String entityName) {
		this.entityName = entityName;
	}

	public DynaModel(FormTable formTable) {
		this.entityName = formTable.getEntityName();
		Iterator it = formTable.getFormFields().iterator();
		while (it.hasNext()) {
			FormField field = (FormField) it.next();
			this.types.put(field.getFieldName(), field.getFieldJavaClass());
			this.labels.put(field.getFieldName(), field.getFieldLabel());
			if (StringUtils.isNotEmpty(field.getShowFormat())) {
				this.formats.put(field.getFieldName(), field.getShowFormat());
			}

			if (FormField.FLOW_TITLE.equals(field.getIsFlowTitle())) {
				this.subjectFieldName = field.getFieldName();
			}

			if (FormField.PRIMARY_KEY.equals(field.getIsPrimary()))
				this.primaryFieldName = field.getFieldName();
		}
	}

	public static void main(String[] args) {
	}

	public String getPrimaryFieldName() {
		return this.primaryFieldName;
	}

	public void setPrimaryFieldName(String primaryFieldName) {
		this.primaryFieldName = primaryFieldName;
	}

	public String getEntityName() {
		return this.entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Map<String, Object> getDatas() {
		return this.datas;
	}

	public void setDatas(Map<String, Object> datas) {
		this.datas = datas;
	}

	public Map<String, Class> getTypes() {
		return this.types;
	}

	public void setTypes(Map<String, Class> types) {
		this.types = types;
	}

	public Map<String, String> getFormats() {
		return this.formats;
	}

	public void setFormats(Map<String, String> formats) {
		this.formats = formats;
	}

	public Object get(String filedName) {
		return this.datas.get(filedName);
	}

	public void set(String filedName, Object value) {
		this.datas.put(filedName, value);
	}

	public Class getType(String filedName) {
		return (Class) this.types.get(filedName);
	}

	public void setType(String filedName, Class types) {
		this.types.put(filedName, types);
	}

	public void setFormat(String fieldName, String format) {
		this.formats.put(fieldName, format);
	}

	public String getFormat(String fieldName) {
		return (String) this.formats.get(fieldName);
	}

	public String getSubjectFieldName() {
		return this.subjectFieldName;
	}

	public void setSubjectFieldName(String subjectFieldName) {
		this.subjectFieldName = subjectFieldName;
	}

	public Map<String, String> getLabels() {
		return this.labels;
	}

	public void setLabels(Map<String, String> labels) {
		this.labels = labels;
	}

	public String getLabel(String fieldName) {
		return (String) this.labels.get(fieldName);
	}

	public void setLabel(String filedName, String fieldLabel) {
		this.labels.put(fieldLabel, fieldLabel);
	}

	public Serializable getPkValue() {
		return (Serializable) this.datas.get(this.primaryFieldName);
	}
}
