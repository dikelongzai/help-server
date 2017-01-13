package com.help.server.controller.admincontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.help.server.configurations.FileUploadConfiguration;
import com.help.server.domain.NewsMapper;
import com.help.server.domain.RotateMapper;
import com.help.server.domain.tables.News;
import com.help.server.domain.tables.Rotate;
import com.help.server.model.User;
import com.help.server.util.FileUploadHelper;
import com.help.server.util.ResultStatusCode;
import com.help.server.util.ServletUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.awt.print.Book;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
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
    private FileUploadConfiguration fileUploaderConfiguration;
    private static class FileModel {
        @JsonProperty("file_name")
        public String fileName;
        public FileModel(String fileName) {
            this.fileName = fileName;
        }
    }
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
        newsMapper.addNews(news.getNew_title(),news.getNew_content());
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
     * 修改新闻
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload() {
        return "admin/upload";
    }
    @ResponseBody
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public FileModel handleFileUpload(@RequestParam("file") MultipartFile file) throws Exception {

        String name = file.getOriginalFilename();
        log.info("try load image " + name);

        String newPhotoName = FileUploadHelper.getUniqueName(name);
        log.info("generate new unique file name " + newPhotoName);

        if (file.isEmpty()) {
            log.error("file " + name + " is empty");
            throw new IllegalArgumentException("Uploaded file is empty");
        }

        if (!FileUploadHelper.isImageContentType(file.getContentType())) {
            log.error("No supported content type " + file.getContentType());
            MediaType mediaType = MediaType.parseMediaType(file.getContentType());
            throw new HttpMediaTypeNotSupportedException(mediaType, FileUploadHelper.getImageMediaTypes());
        }

        String path = fileUploaderConfiguration.getPathToUploadFolder() + newPhotoName;
        log.info("path to upload file - " + path);
        try {
            byte[] bytes = file.getBytes();

            BufferedOutputStream stream =
                    new BufferedOutputStream(
                            new FileOutputStream(new File(path)
                            )
                    );

            stream.write(bytes);
            stream.close();

            log.info("file successfully save by path - " + path);

            return new FileModel(newPhotoName);
        } catch (Exception e) {
            log.debug("error save file by path " + path, e);
            throw new Exception("No file was uploaded");
        }
    }

    /**
     * 根据id获取新闻并跳转至修改页
     * @param
     * @return
     */
    @RequestMapping(value = "/media", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFile( )
            throws IOException {
        String filePath =fileUploaderConfiguration.getPathToUploadFolder()+"7709704f-881e-451e-ac9e-a9642f497172.jpg";
        FileSystemResource file = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }






}
