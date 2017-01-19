package com.help.server;

import com.help.server.configurations.FileUploadConfiguration;
import com.help.server.filter.AdminAuthorize;
import com.help.server.filter.AppHTTPBasicAuthorizeAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@Configuration
public class Application  extends SpringBootServletInitializer {
	@Autowired
	FileUploadConfiguration fileUploaderConfiguration;
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
	@Bean
	@ConfigurationProperties("fileUpload")
	public FileUploadConfiguration uploaderConfiguration() {
		return new FileUploadConfiguration();
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		application.sources(this.getClass());
		return super.configure(application);
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
