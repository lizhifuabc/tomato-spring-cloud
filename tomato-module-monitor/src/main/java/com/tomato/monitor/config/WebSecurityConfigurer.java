package com.tomato.monitor.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * spring security 默认的安全策略
 * @author lizhifu
 */
@EnableWebSecurity
public class WebSecurityConfigurer {

	private final String adminContextPath;

	public WebSecurityConfigurer(AdminServerProperties adminServerProperties) {
		this.adminContextPath = adminServerProperties.getContextPath();
	}

	/**
	 * spring security 默认的安全策略
	 * @param http security注入点
	 * @return SecurityFilterChain
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setTargetUrlParameter("redirectTo");
		successHandler.setDefaultTargetUrl(adminContextPath + "/");
		http.authorizeRequests()
				// 1.配置所有静态资源和登录页可以公开访问
				.antMatchers(adminContextPath + "/assets/**").permitAll()
				.antMatchers(adminContextPath + "/login").permitAll()
				.anyRequest().authenticated()
				.and()
				// 2.配置登录和登出路径
				.formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
				.logout().logoutUrl(adminContextPath + "/logout").and()
				// 3.开启http basic支持，admin-client注册时需要使用
				.httpBasic().and()
				.csrf()
				// 4.开启基于cookie的csrf保护
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				// 5.忽略这些路径的csrf保护以便admin-client注册
				.ignoringAntMatchers(
						adminContextPath + "/instances",
						adminContextPath + "/actuator/**"
				);
		return http.build();
	}
}
