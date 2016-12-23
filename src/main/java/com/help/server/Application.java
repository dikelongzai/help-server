package com.help.server;

import com.help.server.filter.AppHTTPBasicAuthorizeAttribute;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
public class Application {
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
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/")
	public String index() {
		return "main";
	}
}
