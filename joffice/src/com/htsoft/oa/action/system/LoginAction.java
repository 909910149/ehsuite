package com.htsoft.oa.action.system;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import nl.captcha.Captcha;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationManager;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;

import com.htsoft.core.util.StringUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.SysConfig;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.SysConfigService;

public class LoginAction extends BaseAction {
	private AppUser user;
	private String username;
	private String password;
	private String checkCode;
	private String key = "RememberAppUser";

	@Resource
	private AppUserService userService;

	@Resource
	private SysConfigService sysConfigService;

	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager = null;

	public String login() {
		StringBuffer msg = new StringBuffer("{msg:'");

		SysConfig codeConfig = this.sysConfigService.findByKey("codeConfig");

		Captcha captcha = (Captcha) getSession().getAttribute("simpleCaptcha");
		Boolean login = Boolean.valueOf(false);

		String newPassword = null;

		if ((!"".equals(this.username)) && (this.username != null)) {
			//查询用户
			setUser(this.userService.findByUserName(this.username));

			if (this.user != null) {
				if (org.apache.commons.lang.StringUtils
						.isNotEmpty(this.password)) {
					newPassword = StringUtil.encryptSha256(this.password);

					if (this.user.getPassword().equalsIgnoreCase(newPassword)) {
						if ((codeConfig != null)
								&& (codeConfig.getDataValue().equals("1"))) {
							if (captcha == null) {
								msg.append("请刷新验证码再登录.'");
							} else if (captcha.isCorrect(this.checkCode))
								login = Boolean.valueOf(dyPwdCheck(msg,
										login.booleanValue()));
							else {
								msg.append("验证码不正确.'");
							}
						} else
							login = Boolean.valueOf(dyPwdCheck(msg,
									login.booleanValue()));
					} else
						msg.append("密码不正确.'");
				} else {
					msg.append("密码不能为空.'");
				}
			} else
				msg.append("用户不存在.'");
		}
		if (login.booleanValue()) {
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					this.username, this.password);
			SecurityContext securityContext = SecurityContextHolder
					.getContext();
			Authentication auth = this.authenticationManager.authenticate(authRequest);
			securityContext.setAuthentication(auth);
			SecurityContextHolder.setContext(securityContext);
			getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME",
					this.username);
			String rememberMe = getRequest().getParameter(
					"_spring_security_remember_me");

			if ((rememberMe != null) && (rememberMe.equals("on"))) {
				long tokenValiditySeconds = 1209600L;
				long tokenExpiryTime = System.currentTimeMillis()
						+ tokenValiditySeconds * 1000L;

				String signatureValue = DigestUtils.md5Hex(this.username + ":"
						+ tokenExpiryTime + ":" + this.user.getPassword() + ":"
						+ this.key);
				String tokenValue = this.username + ":" + tokenExpiryTime + ":"
						+ signatureValue;
				String tokenValueBase64 = new String(
						Base64.encodeBase64(tokenValue.getBytes()));
				getResponse().addCookie(
						makeValidCookie(tokenExpiryTime, tokenValueBase64));
			}
			setJsonString("{success:true}");
			try {
				this.username = URLEncoder.encode(this.username, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			Cookie cookie = new Cookie("jforumSSOCookieNameUser", this.username
					+ "," + this.user.getPassword() + ","
					+ this.user.getEmail());
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			getResponse().addCookie(cookie);
		} else {
			msg.append(",failure:true}");
			setJsonString(msg.toString());
		}

		return "success";
	}

	protected Cookie makeValidCookie(long expiryTime, String tokenValueBase64) {
		HttpServletRequest request = getRequest();
		Cookie cookie = new Cookie("SPRING_SECURITY_REMEMBER_ME_COOKIE",
				tokenValueBase64);
		cookie.setMaxAge(157680000);
		cookie.setPath(org.springframework.util.StringUtils.hasLength(request
				.getContextPath()) ? request.getContextPath() : "/");
		return cookie;
	}

	public AppUser getUser() {
		return this.user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCheckCode() {
		return this.checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	private boolean dyPwdCheck(StringBuffer msg, boolean login) {
		SysConfig dyPwdConfig = this.sysConfigService.findByKey("dynamicPwd");

		if ((dyPwdConfig != null) && (dyPwdConfig.getDataValue().equals("1"))) {
			if (this.user.getUserId().longValue() == AppUser.SUPER_USER
					.longValue()) {
				login = true;
			} else if (org.apache.commons.lang.StringUtils.isEmpty(this.user
					.getDynamicPwd())) {
				msg.append("此用户未有令牌,请联系管理员.'");
			} else if (this.user.getDyPwdStatus().shortValue() == AppUser.DYNPWD_STATUS_UNBIND
					.shortValue()) {
				msg.append("此用户令牌未绑定,请联系管理员.'");
			} else {
				String curDynamicPwd = getRequest().getParameter(
						"curDynamicPwd");
				HashMap input = new HashMap();
				input.put("app", "demoauthapp");
				input.put("user", this.user.getDynamicPwd());
				input.put("pw", curDynamicPwd);

				String result = this.userService
						.initDynamicPwd(input, "verify");
				if (result.equals("ok")) {
					if (this.user.getStatus().shortValue() == 1)
						login = true;
					else
						msg.append("此用户已被禁用.'");
				} else
					msg.append("令牌不正确,请重新输入.'");

			}

		} else if ((this.user.getStatus().shortValue() == 1)
				|| (this.user.getUserId().longValue() == AppUser.SUPER_USER
						.longValue()))
			login = true;
		else {
			msg.append("此用户已被禁用.'");
		}

		return login;
	}
}
