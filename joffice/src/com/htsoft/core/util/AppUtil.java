package com.htsoft.core.util;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.htsoft.core.DataInit.DataInit;
import com.htsoft.core.jbpm.jpdl.ProcessInit;
import com.htsoft.core.menu.TopModule;
import com.htsoft.core.model.OnlineUser;
import com.htsoft.core.web.filter.SecurityInterceptorFilter;
import com.htsoft.oa.model.system.AppFunction;
import com.htsoft.oa.model.system.AppRole;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Company;
import com.htsoft.oa.model.system.FunUrl;
import com.htsoft.oa.model.system.SysConfig;
import com.htsoft.oa.service.system.AppFunctionService;
import com.htsoft.oa.service.system.CompanyService;
import com.htsoft.oa.service.system.FunUrlService;
import com.htsoft.oa.service.system.SysConfigService;
import com.htsoft.oa.util.FlowUtil;

public class AppUtil implements ApplicationContextAware {
	private static Log logger = LogFactory.getLog(AppUtil.class);

	private static Map configMap = new HashMap();

	private static ServletContext servletContext = null;

	private static Map<String, OnlineUser> onlineUsers = new LinkedHashMap();
	private static ApplicationContext appContext;
	private static Map<String, Document> orgMenus = null;

	private static Map<String, Document> itemsMenus = null;

	private static Map<String, TopModule> allTopModels = null;

	private static Document menuDocument = null;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		appContext = applicationContext;
	}

	public static Map<String, Document> getOrgMenus() {
		return orgMenus;
	}

	public static Map<String, Document> getItemsMenus() {
		return itemsMenus;
	}

	public static Map<String, TopModule> getAllTopModels() {
		return allTopModels;
	}

	public static Document getMenuDocument() {
		return menuDocument;
	}

	public static Object getBean(String beanId) {
		return appContext.getBean(beanId);
	}

	public static Map<String, OnlineUser> getOnlineUsers() {
		return onlineUsers;
	}

	public static void removeOnlineUser(String sessionId) {
		onlineUsers.remove(sessionId);
	}

	public static void addOnlineUser(String sessionId, AppUser user) {
		if (!onlineUsers.containsKey(sessionId)) {
			OnlineUser onlineUser = new OnlineUser();
			onlineUser.setFullname(user.getFullname());
			onlineUser.setSessionId(sessionId);
			onlineUser.setUsername(user.getUsername());
			onlineUser.setUserId(user.getUserId());
			if (!user.getUserId().equals(AppUser.SUPER_USER)) {
				onlineUser.setDepPath("." + user.getDepartment().getPath());
			}
			Set<AppRole> roles = user.getRoles();
			StringBuffer roleIds = new StringBuffer(",");
			for (AppRole role : roles) {
				roleIds.append(role.getRoleId() + ",");
			}
			onlineUser.setRoleIds(roleIds.toString());
			onlineUser.setTitle(user.getTitle());
			onlineUsers.put(sessionId, onlineUser);
		}
	}

	public static String getAppAbsolutePath() {
		return servletContext.getRealPath("/");
	}

	public static String getFlowFormAbsolutePath() {
		String path = (String) configMap.get("app.flowFormPath");
		if (path == null)
			path = "/WEB-INF/FlowForm/";
		return getAppAbsolutePath() + path;
	}

	public static String getMobileFlowFlowAbsPath() {
		return getAppAbsolutePath() + "/mobile/flow/FlowForm/";
	}

	public static void reloadSecurityDataSource() {
		SecurityInterceptorFilter securityInterceptorFilter = (SecurityInterceptorFilter) getBean("securityInterceptorFilter");
		securityInterceptorFilter.loadDataSource();
	}

	public static void init(ServletContext in_servletContext) {
		servletContext = in_servletContext;

		String filePath = servletContext.getRealPath("/WEB-INF/classes/conf/");
		String configFilePath = filePath + "/config.properties";
		Properties props = new Properties();
		try {
			FileInputStream fis = new FileInputStream(configFilePath);
			Reader r = new InputStreamReader(fis, "UTF-8");
			props.load(r);
			Iterator it = props.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				configMap.put(key, props.get(key));
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		reloadSysConfig();

		CompanyService companyService = (CompanyService) getBean("companyService");
		List cList = companyService.findCompany();
		if (cList.size() > 0) {
			Company company = (Company) cList.get(0);
			configMap.put("app.logoPath", company.getLogo());
			configMap.put("app.companyName", company.getCompanyName());
		}

		if (isSetupMode()) {
			logger.info("开始初始化系统的缺省流程...");
			ProcessInit.initFlows(getAppAbsolutePath());
			logger.info("结束初始化系统的缺省流程...");

			logger.info("初始化数据~\t开始...");
			DataInit.init(getAppAbsolutePath());
			logger.info("初始化数据~\t结束...");
			logger.info("更改安装模式为false");
			PropertiesUtil.writeKey(configFilePath, "setupMode", "false");
		}

		reloadMenu();

		logger.info("加载流程动态表单动态实体的结构映射到静态变量... ");
		FlowUtil.initDynModel();
	}

	public static void reloadMenu() {
		orgMenus = MenuUtil.getAllOrgMenus();
		itemsMenus = MenuUtil.getAllItemsMenus(orgMenus);
		menuDocument = MenuUtil.mergeOneDoc(orgMenus);
		allTopModels = MenuUtil.getTopModules(menuDocument);
	}

	public static void synMenu() {
		AppFunctionService appFunctionService = (AppFunctionService) getBean("appFunctionService");
		FunUrlService funUrlService = (FunUrlService) getBean("funUrlService");

		Iterator menuKeys = orgMenus.keySet().iterator();

		while (menuKeys.hasNext()) {
			Document doc = (Document) orgMenus.get(menuKeys.next());

			List funNodeList = doc.getRootElement().selectNodes(
					"/Menus/Items//Item/Function");

			for (int i = 0; i < funNodeList.size(); i++) {
				Element funNode = (Element) funNodeList.get(i);

				String key = funNode.attributeValue("id");
				String name = funNode.attributeValue("text");

				AppFunction appFunction = appFunctionService.getByKey(key);

				if (appFunction == null)
					appFunction = new AppFunction(key, name);
				else {
					appFunction.setFunName(name);
				}

				List urlNodes = funNode.selectNodes("./url");

				appFunctionService.save(appFunction);

				for (int k = 0; k < urlNodes.size(); k++) {
					Node urlNode = (Node) urlNodes.get(k);
					String path = urlNode.getText();
					FunUrl fu = funUrlService.getByPathFunId(path,
							appFunction.getFunctionId());
					if (fu == null) {
						fu = new FunUrl();
						fu.setUrlPath(path);
						fu.setAppFunction(appFunction);
						funUrlService.save(fu);
					}
				}
			}
		}
	}

	public static Document getGrantMenuDocument() {
		String xslStyle = servletContext.getRealPath("/js/menu")
				+ "/menu-grant.xsl";
		Document finalDoc = null;
		try {
			finalDoc = XmlUtil.styleDocument(menuDocument, xslStyle);
		} catch (Exception ex) {
			logger.error("menu-grant.xsl transform has error:"
					+ ex.getMessage());
		}
		return finalDoc;
	}

	public static boolean getIsSynMenu() {
		String synMenu = (String) configMap.get("isSynMenu");

		return "true".equals(synMenu);
	}

	public static Map getSysConfig() {
		return configMap;
	}

	public static void reloadSysConfig() {
		SysConfigService sysConfigService = (SysConfigService) getBean("sysConfigService");
		List<SysConfig> list = sysConfigService.getAll();
		for (SysConfig conf : list)
			configMap.put(conf.getConfigKey(), conf.getDataValue());
	}

	public static String getCompanyLogo() {
//		String defaultLogoPath = "/images/ht-logo.png";
		String defaultLogoPath = "/images/logos/ehsuite.png";
		String path = (String) configMap.get("app.logoPath");
		if (StringUtils.isNotEmpty(path)) {
			defaultLogoPath = "/attachFiles/" + path;
		}
		return defaultLogoPath;
	}

	public static String getCompanyName() {
		String defaultName = "Ehsuite";
		String companyName = (String) configMap.get("app.companyName");
		if (StringUtils.isNotEmpty(companyName)) {
			defaultName = companyName;
		}
		return defaultName;
	}

	public static boolean getSmsPort() {
		String smsPort = (String) configMap.get("smsPort");

		return "true".equals(smsPort);
	}

	public static boolean isSetupMode() {
		String isSetupMode = (String) configMap.get("setupMode");

		return "true".equals(isSetupMode);
	}
}
