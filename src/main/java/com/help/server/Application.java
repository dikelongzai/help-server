package com.help.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("pages/{html}")
	public String urlsshow(@PathVariable String html) {
		return html;
	}

	@RequestMapping("/pages/{page}/{html}")
	public String index(@PathVariable String page, @PathVariable String html) {
		return page + "/" + html;
	}
}
