package com.help.server;

import com.help.server.filter.AdminAuthorize;
import com.help.server.filter.AppHTTPBasicAuthorizeAttribute;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class Application {
	/**
	 * 前段过滤器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean  filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		AppHTTPBasicAuthorizeAttribute httpBasicFilter = new AppHTTPBasicAuthorizeAttribute();
		registrationBean.setFilter(httpBasicFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/appserver/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

	/**
	 * 对后台操作进行拦截
	 * @return
	 */
	@Bean
	public FilterRegistrationBean  filteradminRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		AdminAuthorize httpBasicFilter = new AdminAuthorize();
		registrationBean.setFilter(httpBasicFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/admin/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
