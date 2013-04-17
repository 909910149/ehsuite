package com.htsoft.core.util;

import com.htsoft.core.menu.TopModule;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class MenuUtil {
	private static Log logger = LogFactory.getLog(MenuUtil.class);

	public static Map<String, Document> getAllOrgMenus() {
		Map menusMap = new LinkedHashMap();

		String menuDir = AppUtil.getAppAbsolutePath() + "/js/menu";

		Document allDoc = XmlUtil.load(menuDir + "/menu-all.xml");

		Element root = allDoc.getRootElement();

		Iterator it = root.elementIterator();

		while (it.hasNext()) {
			Element el = (Element) it.next();

			String name = el.attributeValue("name");
			String file = el.attributeValue("file");
			if (logger.isDebugEnabled()) {
				logger.debug("name:" + name + " file=" + file);
			}
			if ((name == null) || (file == null))
				continue;
			logger.info("load the menu config:" + menuDir + "/" + file);

			Document subDoc = XmlUtil.load(menuDir + "/" + file);

			if (subDoc != null) {
				menusMap.put(name, subDoc);
			}

		}

		return menusMap;
	}

	public static Map<String, Document> convertByXsl(
			Map<String, Document> orgMenus, String xslStyle) {
		Map covMenus = new LinkedHashMap();
		Iterator keys = orgMenus.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			Document doc = (Document) orgMenus.get(key);
			try {
				Document covDoc = XmlUtil.styleDocument(doc, xslStyle);
				covMenus.put(key, covDoc);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		return covMenus;
	}

	public static Map<String, Document> getAllItemsMenus(
			Map<String, Document> orgMenus) {
		String xslStyle = AppUtil.getAppAbsolutePath()
				+ "/js/menu/menu-items.xsl";
		return convertByXsl(orgMenus, xslStyle);
	}

	public static Map<String, Document> getAllGrantedMenus(
			Map<String, Document> orgMenus) {
		String xslStyle = AppUtil.getAppAbsolutePath()
				+ "/js/menu/menu-grant.xsl";
		return convertByXsl(orgMenus, xslStyle);
	}

	public static Document mergeOneDoc(Map<String, Document> covMenus) {
		Document allDoc = DocumentHelper.createDocument();

		Element root = allDoc.addElement("Modules");
		Iterator keys = covMenus.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			Document doc = (Document) covMenus.get(key);
			Element docRoot = doc.getRootElement();
			if (docRoot != null) {
				root.add(docRoot.createCopy());
			}
		}

		logger.isDebugEnabled();

		return allDoc;
	}

	public static Map<String, TopModule> getTopModules(Document modDoc) {
		Map topMap = new LinkedHashMap();
		Element modulesEl = modDoc.getRootElement();
		Iterator menusIt = modulesEl.elementIterator();

		while (menusIt.hasNext()) {
			Element modEl = (Element) menusIt.next();
			String id = modEl.attributeValue("id");
			String text = modEl.attributeValue("text");
			String iconCls = modEl.attributeValue("iconCls");

			TopModule topModule = new TopModule(id, text, iconCls);
			topMap.put(id, topModule);
		}

		return topMap;
	}

	public static void main(String[] args) {
		Map orgMenus = getAllOrgMenus();

		Map covMenus = getAllItemsMenus(orgMenus);

		Document doc = mergeOneDoc(covMenus);

		System.out.println(doc.asXML());
	}
}
