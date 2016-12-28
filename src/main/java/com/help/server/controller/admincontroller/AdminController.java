package com.help.server.controller.admincontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/main")
    public String main() {
        return "admin/main";
    }
    @RequestMapping("/top")
    public String top() {
        return "admin/top";
    }
    @RequestMapping("/left")
    public String left() {
        return "admin/left";
    }
    @RequestMapping("/index")
    public String index() {
        return "admin/index";
    }
    @RequestMapping("/footer")
    public String footer() {
        return "admin/footer";
    }

}
