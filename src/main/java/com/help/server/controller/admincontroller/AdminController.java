package com.help.server.controller.admincontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.help.server.domain.NewsMapper;
import com.help.server.domain.tables.News;
import com.help.server.model.User;
import com.help.server.util.ResultStatusCode;
import com.help.server.util.ServletUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.print.Book;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {
    private static final Log log = LogFactory.getLog(AdminController.class);
    @Autowired
    private NewsMapper adminMapper;
    @RequestMapping("/")
    public String indexmain() {
        return "admin/main";
    }

    @RequestMapping("/main")
    public String main() {
        return "admin/main";
    }
    @RequestMapping("/top")
    public String top(Map<String, Object> map, HttpServletRequest request) throws Exception {
        map.put("user", ServletUtil.checkLogin(request).getUsername());
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
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getUser(Map<String, Object> map, HttpServletRequest request) throws Exception {
        ResultStatusCode rs = null;
        User u = null;
        try {
            u = ServletUtil.checkLogin(request);
            rs = ResultStatusCode.OK;
        } catch (Exception e) {
            rs = ResultStatusCode.AUTHOR_ERROR;
        }

        if (u != null) {
            JSONObject jsonReturn = new JSONObject();
            jsonReturn.put("name", u.getUsername());
            rs.setReturnData(jsonReturn);
        }
        return rs.toJson();
    }

    /**
     * 新闻列表
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/news")
    public String news(Map<String, Object> map, HttpServletRequest request) {
        List<News> list=adminMapper.findAllNews();

        log.info("admin/news get All news="+ JSON.toJSONString(list));
        map.put("news",list);
        return "admin/news";
    }

    /**
     * 添加新闻
     * @param news
     * @param result
     * @return
     */
    @RequestMapping(value="/news/edit_news", method = RequestMethod.POST)
    public String createNews(@ModelAttribute News news,BindingResult result) {
        adminMapper.addNews(news.getNew_title(),news.getNew_content());
        log.info("success add news title="+news.getNew_title()+";content="+news.getNew_content());
        return "redirect:/admin/news";
    }

    /**
     * 修改新闻
     * @param news
     * @param result
     * @return
     */
    @RequestMapping(value="/news/updateNews", method = RequestMethod.POST)
    public String updateNews(@ModelAttribute News news,BindingResult result) {
        adminMapper.updateNew(news.getId(),news.getNew_title(),news.getNew_content());
        log.info("success update news title="+news.getNew_title()+";content="+news.getNew_content()+";id="+news.getId());
        return "redirect:/admin/news";
    }
    /**
     * 删除新闻
     * @param id
     * @return
     */
    @GetMapping(value = "/newsDelete/{id}")
    public String deleteNews(@PathVariable("id") long id){
        adminMapper.deleteNew(id);
        log.info("success deleteNew id="+id);
        return "redirect:/admin/news";
    }
    /**
     * 根据id获取新闻并跳转至修改页
     * @param id
     * @return
     */
    @GetMapping(value = "/newsedit/{id}")
    public String newseditId(@PathVariable("id") long id,Map<String, Object> map){
        News news=adminMapper.findNewsById(id);
        log.info("/newsedit/{id}="+ JSON.toJSONString(news));
        map.put("news",news);
        return "/admin/newsUpdate";
    }
    /**
     * 修改新闻
     * @return
     */
    @RequestMapping(value = "/newsedit", method = RequestMethod.GET)
    public String newsedit() {
        return "admin/news_edit";
    }





}
