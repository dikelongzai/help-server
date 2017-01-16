package com.help.server.controller.admincontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.help.server.domain.AppServerMapper;
import com.help.server.domain.NewsMapper;
import com.help.server.domain.RotateMapper;
import com.help.server.domain.tables.News;
import com.help.server.domain.tables.Rotate;
import com.help.server.model.User;
import com.help.server.service.AdminService;
import com.help.server.service.AppService;
import com.help.server.service.FileUploadService;
import com.help.server.util.CommonConstant;
import com.help.server.util.ResultStatusCode;
import com.help.server.util.ServletUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {
    private static final Log log = LogFactory.getLog(AdminController.class);

    /*  =============================公共部分begin==================================**/
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private RotateMapper rotateMapper;
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private AppService appService;

    @Autowired
    private AdminService aadminService;
    @Autowired
    private AppServerMapper appServerMapper;
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
    /*  =============================公共部分end==================================**/
    /*  =============================新闻公告begion==================================**/
    /**
     * 新闻列表
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/news")
    public String news(Map<String, Object> map, HttpServletRequest request) {
        List<News> list=newsMapper.findAllNews();

        log.info("admin/news get All news="+ JSON.toJSONString(list));
        map.put("news",list);
        return "admin/news";
    }
    /**
     * 修改新闻
     * @return
     */
    @RequestMapping(value = "/newsedit", method = RequestMethod.GET)
    public String newsedit() {
        return "admin/news_edit";
    }
    /**
     * 添加新闻
     * @param news
     * @param result
     * @return
     */
    @RequestMapping(value="/news/edit_news", method = RequestMethod.POST)
    public String createNews(@ModelAttribute News news,BindingResult result) {
        newsMapper.addNews(news.getNew_title(), news.getNew_content());
        log.info("success add news title=" + news.getNew_title() + ";content=" + news.getNew_content());
        appServerMapper.id_generator(CommonConstant.NEW_ID);
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
        newsMapper.updateNew(news.getId(),news.getNew_title(),news.getNew_content());
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
        newsMapper.deleteNew(id);
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
        News news=newsMapper.findNewsById(id);
        log.info("/newsedit/{id}="+ JSON.toJSONString(news));
        map.put("news",news);
        return "/admin/newsUpdate";
    }

    /*  =============================新闻公告end==================================**/
    /*  =============================系统轮播图==================================**/
    /**
     * 系统轮播图列表
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/rotate")
    public String rotate(Map<String, Object> map, HttpServletRequest request) {
        List<Rotate> list=rotateMapper.findAllNews();
        log.info("admin/rotate get All rotate="+ JSON.toJSONString(list));
        map.put("news",list);
        return "admin/rotate";
    }
    /**
     * 添加轮播图
     * @return
     */
    @RequestMapping(value = "/rotate_a", method = RequestMethod.GET)
    public String upload() {
        return "admin/rotate_a";
    }

    /**
     * 添加轮播图
     * @return
     */
    @RequestMapping(value = "/addRotate", method = RequestMethod.POST)
    public String uploadRotate(@RequestParam("file") MultipartFile file,@RequestParam("href_url") String href_url) throws Exception {

        String fileName=fileUploadService.handleFileUpload(file).getFileName();
        String url= CommonConstant.BASE_IMAGE_URL+fileName;
        log.info("upload addRotate url="+url);
        rotateMapper.addRotate(url, href_url);
        appServerMapper.id_generator(CommonConstant.ROTATE_ID);
        log.info("upload addRotate url=" + url);
          return "redirect:/admin/rotate";
    }
    /**
     * 删除轮播图
     * @param id
     * @return
     */
    @GetMapping(value = "/rotateDelete/{id}")
    public String deleteRotate(@PathVariable("id") long id){
        rotateMapper.deleteRotate(id);
        log.info("success deleteRotate id=" + id);
        return "redirect:/admin/rotate";
    }
     /*  =============================系统轮播图end==================================**/
    /*  =============================系统留言begin==================================**/
    /**
     * 系统轮播图列表
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/leave")
    public String leave(Map<String, Object> map, HttpServletRequest request) {
        return "admin/leave";
    }

    /**
     * ajax获取留言信息及分页信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getLeaveMessage", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getLeaveMessage(HttpServletRequest request) throws Exception {
        JSONObject param=ServletUtil.getAppRequestParameters(request, null);
        log.info("param="+param.toJSONString());
        JSONObject resultJson=aadminService.getLeaveMsg(param);
        log.info(resultJson.toJSONString());
        return resultJson;
    }


     /*  =============================系统留言end==================================**/










}
