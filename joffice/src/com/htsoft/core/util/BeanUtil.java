package com.htsoft.core.util;

import com.htsoft.core.dao.impl.DynamicDaoImpl;
import com.htsoft.core.model.DynaModel;
import com.htsoft.core.service.DynamicService;
import com.htsoft.core.service.impl.DynamicServiceImpl;
import com.htsoft.core.struts.BeanDateConnverter;
import com.htsoft.oa.util.FlowUtil;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.collection.PersistentBag;
import org.hibernate.proxy.map.MapProxy;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

public class BeanUtil {
	private static Log logger = LogFactory.getLog(BeanUtil.class);

	public static ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();

	static {
		convertUtilsBean.register(new BeanDateConnverter(), Date.class);
		convertUtilsBean.register(new LongConverter(null), Long.class);
	}

	public static void copyNotNullProperties(Object dest, Object orig)
			throws IllegalAccessException, InvocationTargetException {
		BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();

		if (dest == null) {
			throw new IllegalArgumentException("No destination bean specified");
		}
		if (orig == null) {
			throw new IllegalArgumentException("No origin bean specified");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("BeanUtils.copyProperties(" + dest + ", " + orig + ")");
		}

		if ((orig instanceof DynaBean)) {
			DynaProperty[] origDescriptors = ((DynaBean) orig).getDynaClass()
					.getDynaProperties();
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();

				if ((!beanUtils.getPropertyUtils().isReadable(orig, name))
						|| (!beanUtils.getPropertyUtils().isWriteable(dest,
								name)))
					continue;
				Object value = ((DynaBean) orig).get(name);
				beanUtils.copyProperty(dest, name, value);
			}
		} else if ((orig instanceof Map)) {
			Iterator entries = ((Map) orig).entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = (Map.Entry) entries.next();
				String name = (String) entry.getKey();
				if (beanUtils.getPropertyUtils().isWriteable(dest, name))
					beanUtils.copyProperty(dest, name, entry.getValue());
			}
		} else {
			PropertyDescriptor[] origDescriptors = beanUtils.getPropertyUtils()
					.getPropertyDescriptors(orig);
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if ("class".equals(name)) {
					continue;
				}
				if ((!beanUtils.getPropertyUtils().isReadable(orig, name))
						|| (!beanUtils.getPropertyUtils().isWriteable(dest,
								name)))
					continue;
				try {
					Object value = beanUtils.getPropertyUtils()
							.getSimpleProperty(orig, name);
					if (value != null)
						beanUtils.copyProperty(dest, name, value);
				} catch (NoSuchMethodException localNoSuchMethodException) {
				}
			}
		}
	}

	public static String mapEntity2Html(Map<String, Object> mapData,
			String entityName) {
		StringBuffer sb = new StringBuffer("<ul>");
		DynaModel dynaModel = (DynaModel) FlowUtil.DynaModelMap.get(entityName);
		Iterator entryIt = mapData.entrySet().iterator();
		int i = 0;
		while (entryIt.hasNext()) {
			Map.Entry entry = (Map.Entry) entryIt.next();
			String key = (String) entry.getKey();
			String label = dynaModel.getLabel(key);
			if (label == null)
				label = key;
			label = "<b>" + label + "</b>";
			Object val = entry.getValue();

			if ((key.equals("$type$")) || (key.equals("runId"))
					|| (key.equals(dynaModel.getPrimaryFieldName()))
					|| (key.equals(entityName)) || ((val instanceof MapProxy))
					|| ((val instanceof Map)))
				continue;
			if ((val instanceof PersistentBag)) {
				String subEntityName = key.substring(0, key.length() - 1);
				sb.append("<ol><i>明细：</i>");
				Iterator bagIt = ((PersistentBag) val).iterator();
				while (bagIt.hasNext()) {
					Map map = (Map) bagIt.next();
					sb.append("<li>")
							.append(mapEntity2Html(map, subEntityName))
							.append("</li><hr/>");
				}
				sb.append("</ol>");
			} else if ((val instanceof Date)) {
				String formatStyle = dynaModel.getFormat(key);
				if (formatStyle == null) {
					formatStyle = "yyyy-MM-dd HH:mm:ss";
				}
				SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
				String result = sdf.format((Date) val);
				sb.append("<li>").append(label).append(":").append(result)
						.append("</li>");
			} else {
				sb.append("<li>").append(label).append(":").append(val)
						.append("</li>");
			}
			i++;
		}
		sb.append("</ul>");
		return sb.toString();
	}

	public static Map<String, Object> populateEntity(
			HttpServletRequest request, DynaModel dynaModel) {
		Map valuesMap = request.getParameterMap();
		return populateEntity(valuesMap, dynaModel);
	}

	public static Map<String, Object> populateEntity(Map valuesMap,
			DynaModel dynaModel) {
		Iterator typeIt = dynaModel.getTypes().entrySet().iterator();

		HashMap datas = new HashMap();

		while (typeIt.hasNext()) {
			Map.Entry entry = (Map.Entry) typeIt.next();
			String fieldName = (String) entry.getKey();
			Class fieldType = (Class) entry.getValue();

			Object val = valuesMap.get(fieldName);
			if (val != null) {
				datas.put(fieldName,
						convertValue(convertUtilsBean, val, fieldType));
			}
		}
		return datas;
	}

	public static Object populateEntity(HttpServletRequest request,
			Object entity, String preName) throws IllegalAccessException,
			InvocationTargetException {
		HashMap map = new HashMap();
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();

			String propetyName = name;
			if (StringUtils.isNotEmpty(preName)) {
				propetyName = propetyName.replace(preName + ".", "");
			}
			map.put(propetyName, request.getParameterValues(name));
		}
		getBeanUtils().populate(entity, map);
		return entity;
	}

	public static DynamicService getDynamicServiceBean(String entityName) {
		LocalSessionFactoryBean sessionFactory = (LocalSessionFactoryBean) AppUtil
				.getBean("&sessionFactory");
		DynamicDaoImpl dao = new DynamicDaoImpl(entityName);
		dao.setSessionFactory((SessionFactory) sessionFactory.getObject());
		dao.setEntityClassName(entityName);
		DynamicServiceImpl service = new DynamicServiceImpl(dao);
		return service;
	}

	public static Object convertValue(ConvertUtilsBean convertUtil,
			Object value, Class type) {
		Converter converter = convertUtil.lookup(type);
		if (converter == null)
			return value;

		Object newValue = null;
		if ((value instanceof String))
			newValue = converter.convert(type, (String) value);
		else if ((value instanceof String[]))
			newValue = converter.convert(type, ((String[]) value)[0]);
		else {
			newValue = converter.convert(type, value);
		}
		return newValue;
	}

	public static BeanUtilsBean getBeanUtils() {
		BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean,
				new PropertyUtilsBean());
		return beanUtilsBean;
	}

	public static Map getMapFromRequest(HttpServletRequest request) {
		Map reqMap = request.getParameterMap();

		HashMap datas = new HashMap();
		Iterator it = reqMap.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			String[] val = (String[]) entry.getValue();
			if (val.length == 1)
				datas.put(key, val[0]);
			else {
				datas.put(key, val);
			}
		}
		return datas;
	}
}
