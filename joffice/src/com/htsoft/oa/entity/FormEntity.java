package com.htsoft.oa.entity;

import com.htsoft.core.util.FunctionsUtil;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

public class FormEntity implements Serializable {
	public String getHtml() {
		StringBuffer sb = new StringBuffer();
		Field[] fields = getClass().getDeclaredFields();
		for (Field field : fields)
			try {
				Method getMethod = getClass().getDeclaredMethod(
						"get"
								+ FunctionsUtil.makeFirstLetterUpperCase(field
										.getName()), new Class[0]);
				if (getMethod != null) {
					Object val = getMethod.invoke(this, new Object[0]);
					if (field.getType().toString().indexOf("java.util.Date") != -1) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						val = sdf.format(val);
					}

					sb.append("<b>" + field.getName() + ":</b>")
							.append(val.toString()).append("<br/>");
				}
			} catch (Exception localException) {
			}
		return sb.toString();
	}
}
