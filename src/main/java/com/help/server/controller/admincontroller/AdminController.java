package com.help.server.controller.admincontroller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.help.server.domain.*;
import com.help.server.domain.tables.*;
import com.help.server.model.User;
import com.help.server.service.AdminService;
import com.help.server.service.AppService;
import com.help.server.service.FileUploadService;
import com.help.server.util.CommonConstant;
import com.help.server.util.ResultStatusCode;
import com.help.server.util.ServletUtil;
import com.help.server.util.StringUtil;
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
    @Autowired
    private LeaveMsgMapper leaveMsgMapper;
    @Autowired
    private OrderSettingMapper orderSettingMapper;
    @Autowired
    private AwardMapper awardMapper;
    @Autowired
    private UserMapper userMapper;
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
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/news")
    public String news(Map<String, Object> map, HttpServletRequest request) {
        List<News> list = newsMapper.findAllNews();

        log.info("admin/news get All news=" + JSON.toJSONString(list));
        map.put("news", list);
        return "admin/news";
    }

    /**
     * 修改新闻
     *
     * @return
     */
    @RequestMapping(value = "/newsedit", method = RequestMethod.GET)
    public String newsedit() {
        return "admin/news_edit";
    }

    /**
     * 添加新闻
     *
     * @param news
     * @param result
     * @return
     */
    @RequestMapping(value = "/news/edit_news", method = RequestMethod.POST)
    public String createNews(@ModelAttribute News news, BindingResult result) {
        newsMapper.addNews(news.getNew_title(), news.getNew_content());
        log.info("success add news title=" + news.getNew_title() + ";content=" + news.getNew_content());
        appServerMapper.id_generator(CommonConstant.NEW_ID);
        return "redirect:/admin/news";
    }

    /**
     * 修改新闻
     *
     * @param news
     * @param result
     * @return
     */
    @RequestMapping(value = "/news/updateNews", method = RequestMethod.POST)
    public String updateNews(@ModelAttribute News news, BindingResult result) {
        newsMapper.updateNew(news.getId(), news.getNew_title(), news.getNew_content());
        log.info("success update news title=" + news.getNew_title() + ";content=" + news.getNew_content() + ";id=" + news.getId());
        return "redirect:/admin/news";
    }

    /**
     * 删除新闻
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/newsDelete/{id}")
    public String deleteNews(@PathVariable("id") long id) {
        newsMapper.deleteNew(id);
        log.info("success deleteNew id=" + id);
        return "redirect:/admin/news";
    }

    /**
     * 根据id获取新闻并跳转至修改页
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/newsedit/{id}")
    public String newseditId(@PathVariable("id") long id, Map<String, Object> map) {
        News news = newsMapper.findNewsById(id);
        log.info("/newsedit/{id}=" + JSON.toJSONString(news));
        map.put("news", news);
        return "/admin/newsUpdate";
    }

    /*  =============================新闻公告end==================================**/
    /*  =============================系统轮播图==================================**/

    /**
     * 系统轮播图列表
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/rotate")
    public String rotate(Map<String, Object> map, HttpServletRequest request) {
        List<Rotate> list = rotateMapper.findAllNews();
        log.info("admin/rotate get All rotate=" + JSON.toJSONString(list));
        map.put("news", list);
        return "admin/rotate";
    }

    /**
     * 添加轮播图
     *
     * @return
     */
    @RequestMapping(value = "/rotate_a", method = RequestMethod.GET)
    public String upload() {
        return "admin/rotate_a";
    }

    /**
     * 添加轮播图
     *
     * @return
     */
    @RequestMapping(value = "/addRotate", method = RequestMethod.POST)
    public String uploadRotate(@RequestParam("file") MultipartFile file, @RequestParam("href_url") String href_url) throws Exception {

        String fileName = fileUploadService.handleFileUpload(file).getFileName();
        String url = CommonConstant.BASE_IMAGE_URL + fileName;
        log.info("upload addRotate url=" + url);
        rotateMapper.addRotate(url, href_url);
        appServerMapper.id_generator(CommonConstant.ROTATE_ID);
        log.info("upload addRotate url=" + url);
        return "redirect:/admin/rotate";
    }

    /**
     * 删除轮播图
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/rotateDelete/{id}")
    public String deleteRotate(@PathVariable("id") long id) {
        rotateMapper.deleteRotate(id);
        log.info("success deleteRotate id=" + id);
        return "redirect:/admin/rotate";
    }
     /*  =============================系统轮播图end==================================**/
    /*  =============================系统留言begin==================================**/

    /**
     * 系统轮播图列表
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/leave")
    public String leave(Map<String, Object> map, HttpServletRequest request) {
        return "admin/leave";
    }

    /**
     * 回复留言
     *
     * @return
     */
    @RequestMapping(value = "/leave_edit", method = RequestMethod.GET)
    public String leaveedit() {
        return "admin/leave_edit";
    }

    /**
     * ajax获取留言信息及分页信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getLeaveMessage", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getLeaveMessage(HttpServletRequest request) throws Exception {
        JSONObject param = ServletUtil.getAppRequestParameters(request, null);
        log.info("param=" + param.toJSONString());
        // 'reply_type 0 普通用户留言 1 管理员问题解答'
        JSONObject resultJson = aadminService.getLeaveMsg(param,0);
        log.info(resultJson.toJSONString());
        return resultJson;
    }

    /**
     * 异步删除留言信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delLeaveMessage", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject DelLeaveMessage(HttpServletRequest request) throws Exception {
        JSONObject param = ServletUtil.getAppRequestParameters(request, null);
        log.info("param=" + param.toJSONString());
        leaveMsgMapper.deleteNew(Long.valueOf(param.getString("leaveId")));
        return ResultStatusCode.OK.toJson();
    }

    /**
     * 根据id获取新闻并跳转至修改页
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/editLeave/{id}")
    public String editLeave(@PathVariable("id") long id, Map<String, Object> map) {
        Leaving_Msg news = leaveMsgMapper.findNewsById(id);
        log.info("/editLeave/{id}=" + JSON.toJSONString(news));
        map.put("news", news);
        return "/admin/leave_edit";
    }

    /**
     * 修改新闻
     *
     * @param news
     * @param result
     * @return
     */
    @RequestMapping(value = "/updateLeave", method = RequestMethod.POST)
    public String updateLeave(@ModelAttribute Leaving_Msg news, BindingResult result) {
        leaveMsgMapper.updateNew(news.getId(), news.getReply_content());
        log.info("success update news getReply_content=" + news.getReply_content() + ";id=" + news.getId());
        return "redirect:/admin/leave";
    }

    /*  =============================系统留言end==================================**/
     /*  =============================订单规则设置start==================================**/

    /**
     * 订单规则
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/order_setting")
    public String orderSetting(Map<String, Object> map, HttpServletRequest request) {
        OrderSetting news = orderSettingMapper.findNewsById();
        log.info("/order_setting/=" + JSON.toJSONString(news));
        map.put("news", news);
        return "admin/order_setting";
    }

    /**
     * 修改订单规则
     *
     * @return
     */
    @RequestMapping(value = "/updateOrderSetting", method = RequestMethod.POST)
    public String updateorderSetting(HttpServletRequest request) throws Exception {
        JSONObject param = ServletUtil.getAppRequestParameters(request, null);

        aadminService.updateOrderRolue(param);
        log.info(param);
        return "redirect:/admin/order_setting";
    }

     /*  =============================订单规则设置end==================================**/
    /*  ============================= 动态奖励规则设置start==================================**/

    /**
     * 动态奖励规则列表
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/award")
    public String award(Map<String, Object> map, HttpServletRequest request) {
        List<Award> list = awardMapper.findAllNews();
        log.info("admin/award get All news=" + JSON.toJSONString(list));
        map.put("news", list);
        return "admin/award";
    }

    /**
     * 修改动态奖励规则
     *
     * @return
     */
    @RequestMapping(value = "/awardIndex", method = RequestMethod.GET)
    public String awardIndex() {
        return "admin/awardIndex";
    }

    /**
     * 添加动态奖励规则
     *
     * @param news
     * @param result
     * @return
     */
    @RequestMapping(value = "/addAward", method = RequestMethod.POST)
    public String addAward(@ModelAttribute Award news, BindingResult result) {
        log.info("success addAward parm="+JSON.toJSON(news) );
        awardMapper.addNews(news.getDirect_num(),news.getTeam_num(),news.getOne_generation(),news.getTwo_generation(),news.getThree_generation(),news.getFour_generation(),news.getUser_title());
        appServerMapper.id_generator(CommonConstant.DYNAMIC_ID);
        appServerMapper.id_generator(CommonConstant.USER_TITLE_ID);
        return "redirect:/admin/award";
    }

    /**
     * 修改动态奖励规则
     *
     * @param news
     * @param result
     * @return
     */
    @RequestMapping(value = "/updateAward", method = RequestMethod.POST)
    public String updateAward(@ModelAttribute Award news, BindingResult result)throws Exception {
        JSONObject param= JSON.parseObject(JSON.toJSONString(news));
        log.info("success updateAward parm=" + JSON.toJSON(news) );
        aadminService.updateAward(param);
        //awardMapper.updateNew(news.getId(),news.getDirect_num(),news.getTeam_num(),news.getOne_generation(),news.getTwo_generation(),news.getThree_generation(),news.getFour_generation(),news.getUser_title());

        return "redirect:/admin/award";
    }

    /**
     * 删除动态奖励规则
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/deleteAward/{id}")
    public String deleteAward(@PathVariable("id") long id) {
        awardMapper.deleteNew(id);
        log.info("success deleteNew id=" + id);
        return "redirect:/admin/award";
    }

    /**
     * 根据id获取新闻并跳转至修改页
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/editAward/{id}")
    public String editAward(@PathVariable("id") long id, Map<String, Object> map) {
        Award news = awardMapper.findNewsById(id);
        log.info("/editAward/{id}=" + JSON.toJSONString(news));
        map.put("news", news);
        return "/admin/awardUpdate";
    }
   /*  ============================= 动态奖励规则设置end==================================**/
    /* ===========================激活码管理start==================================**/
    /**
     * 激活码管理
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/code")
    public String code(Map<String, Object> map, HttpServletRequest request) {
        return "admin/code";
    }
    /**
     * ajax获取用户激活码
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCodeList", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getCodeList(HttpServletRequest request) throws Exception {
        JSONObject param = ServletUtil.getAppRequestParameters(request, null);
        log.info("param=" + param.toJSONString());
        JSONObject resultJson = aadminService.getPageCode(param);
        log.info(resultJson.toJSONString());
        return resultJson;
    }
    /**
     * 下发激活码
     *
     * @return
     */
    @GetMapping(value = "/addCode/{user_id}")
    public String addCode(@PathVariable("user_id") long user_id, Map<String, Object> map) {
        User_MemberInfo news = appServerMapper.getUserInfo(user_id);
        log.info("/addCode/{user_id}=" + JSON.toJSONString(news));
        map.put("news", news);
        return "/admin/addCodeIndex";
    }

    /**
     * ajax添加激活码
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doAddCode", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject doAddCode(HttpServletRequest request) throws Exception {
        JSONObject param = ServletUtil.getAppRequestParameters(request, null);
        log.info("param=" + param.toJSONString());
        int num = appServerMapper.updateUserCodeNum_add(Integer.parseInt(param.getString("addNum")),Long.valueOf(param.getString("user_id")));
        return ResultStatusCode.OK.toJson();
    }
/* ===========================激活码管理end==================================**/
/* ===========================用户管理start==================================**/
    /**
     * 用户管理starts
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/user")
    public String user(Map<String, Object> map, HttpServletRequest request) {
        //获取所有title及titleid
        List<Award> list = awardMapper.findAllNews();
        log.info("admin/award get All news=" + JSON.toJSONString(list));
        map.put("news", list);
        return "admin/user";
    }
    /**
     * ajax获取用户列表
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getUserList(HttpServletRequest request) throws Exception {
        JSONObject param = ServletUtil.getAppRequestParameters(request, null);
        JSONObject resultJson = aadminService.getPageUser(param);
        log.info("param=" + param.toJSONString()+";result="+resultJson.toJSONString());
        return resultJson;
    }
    /**
     * 单用户审批
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/appro/{user_id}", method = RequestMethod.GET)
    public String appro(@PathVariable("user_id") long user_id, Map<String, Object> map) throws Exception {
        userMapper.updateUserStatus(2,user_id);
        return "redirect:/admin/user";
    }

    /**
     * 设置管理账户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doAdminUser/{user_id}", method = RequestMethod.GET)
    public String doAdminUser(@PathVariable("user_id") long user_id, Map<String, Object> map) throws Exception {
        userMapper.updateUserRoleAdmin(user_id);
        return "redirect:/admin/user";
    }
    /**
     * 取消管理账户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/undoAdminUser/{user_id}", method = RequestMethod.GET)
    public String undoAdminUser(@PathVariable("user_id") long user_id, Map<String, Object> map) throws Exception {
        userMapper.updateUndoUserRoleAdmin(user_id);
        return "redirect:/admin/user";
    }
    /**
     * 冻结
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/frozen/{user_id}", method = RequestMethod.GET)
    public String frozen(@PathVariable("user_id") long user_id, Map<String, Object> map) throws Exception {
        userMapper.updateUserStatus(3,user_id);
        return "redirect:/admin/user";
    }
    /**
     * 解冻
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unfrozen/{user_id}", method = RequestMethod.GET)
    public String unfrozen(@PathVariable("user_id") long user_id, Map<String, Object> map) throws Exception {
        userMapper.updateUserStatus(2,user_id);
        return "redirect:/admin/user";
    }
    /**
     * 解冻
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/approall", method = RequestMethod.GET)
    public String approall() throws Exception {
        userMapper.approveAll();
        return "redirect:/admin/user";
    }
    /**
     * 用户详情
     *
     * @return
     */
    @GetMapping(value = "/userDetail/{user_id}")
    public String userDetail(@PathVariable("user_id") long user_id, Map<String, Object> map) {
        User_MemberInfo news = appServerMapper.getUserInfo(user_id);
        log.info("/userDetail/{user_id}=" + JSON.toJSONString(news));
        map.put("news", news);
        return "/admin/userdetail";
    }
    /**
     * ajax修改用户信息
     *@param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doEditUser", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject doEditUser(HttpServletRequest request) throws Exception {
        JSONObject param = ServletUtil.getAppRequestParameters(request, null);
        //param={"udynamic_wallet":"6000","user_bank_account":"65182364588569426","user_bank_name":"测试仔仔","user_payment":"东边6","ustatic_wallet":"5000"}
        log.info("param=" + param.toJSONString());
        aadminService.updateUser(param);
        //int num = appServerMapper.updateUserCodeNum_add(Integer.parseInt(param.getString("addNum")),Long.valueOf(param.getString("user_id")));
        return ResultStatusCode.OK.toJson();
    }
    /* ===========================用户管理end==================================**/
    /* ===========================添加订单start==================================**/
    /**
     * 添加订单先展示管理账户列表
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/adminlist")
    public String adminlist(Map<String, Object> map, HttpServletRequest request) {
        List<User_MemberInfo> list = userMapper.getAdminInfo();
        log.info("admin/adminlist get All adminlist=" + JSON.toJSONString(list));
        map.put("news", list);
        return "/admin/adminlistIndex";
    }
    /**
     * 造单携带用户信息过去
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addOfferHelp/{user_id}", method = RequestMethod.GET)
    public String addOfferHelp(@PathVariable("user_id") long user_id, Map<String, Object> map) throws Exception {
        User_MemberInfo news = appServerMapper.getUserInfo(user_id);
        log.info("/addOfferHelp/{user_id}=" + JSON.toJSONString(news));
        map.put("news", news);
        return "/admin/addOfferIndex";
    }
    /**
     * ajax添加订单
     *@param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doAddOfferHelp", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject doAddOfferHelp(HttpServletRequest request) throws Exception {
        JSONObject param = ServletUtil.getAppRequestParameters(request, null);
        // param={"create_date":"2017-02-06 11:05:01","help_type":"0","money_num":"10000","user_id":"3","user_phone":"13759889278","wallet_type":"1"}
        log.info("param=" + param.toJSONString());
        //int num = appServerMapper.updateUserCodeNum_add(Integer.parseInt(param.getString("addNum")),Long.valueOf(param.getString("user_id")));
        return aadminService.doAddOfferHelp(param);
    }

    /* ===========================添加订单end==================================**/
    /* ===========================订单匹配start==================================**/
    /**
     * 添加订单先展示管理账户列表
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/match")
    public String match(Map<String, Object> map, HttpServletRequest request) {
        return "/admin/match";
    }
    /**
     * ajax获取用户列表
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getMatchList", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getMatchList(HttpServletRequest request) throws Exception {
        JSONObject param = ServletUtil.getAppRequestParameters(request, null);
        JSONObject resultJson = aadminService.getMatchList(param);
        log.info("param=" + param.toJSONString()+";result="+resultJson.toJSONString());
        return resultJson;
    }
    /**
     * ajax订单匹配
     *@param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doMatch", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject doMatch(HttpServletRequest request) throws Exception {
        JSONObject param = ServletUtil.getAppRequestParameters(request, null);
        // param={"create_date":"2017-02-06 11:05:01","help_type":"0","money_num":"10000","user_id":"3","user_phone":"13759889278","wallet_type":"1"}
        //b1 正常买入 b2 复投买入 c1 静态提现 c2 动态提现
        //{"b1":[{"day":"2017-02-04","value":"800"},{"day":"2017-02-03","value":"4200"}],"b2":[{"day":"2017-02-04","value":"1800"},{"day":"2017-02-03","value":"500"}],"c1":[{"day":"2017-02-04","value":"500"},{"day":"2017-02-03","value":"1100"}],"c2":[{"day":"2017-02-04","value":"2600"},{"day":"2017-02-03","value":"1300"}]}
        JSONObject paramJson=JSON.parseObject(param.getString("param"));
        log.info("paramJson=" + paramJson.toJSONString());
        //int num = appServerMapper.updateUserCodeNum_add(Integer.parseInt(param.getString("addNum")),Long.valueOf(param.getString("user_id")));
        return aadminService.doMatchOffer(paramJson);
    }


     /* ===========================订单匹配end==================================**/
    /* ===========================常见问题start==================================**/
    /**
     * 常见问题
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/question")
    public String question(Map<String, Object> map, HttpServletRequest request) {
        return "admin/question";
    }

    /**
     * 添加问题
     *
     * @return
     */
    @RequestMapping(value = "/questionAdd", method = RequestMethod.GET)
    public String questionAdd() {
        return "admin/questionAdd";
    }

    /**
     * ajax获取留言信息及分页信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getQuestionMessage", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getQuestionMessage(HttpServletRequest request) throws Exception {
        JSONObject param = ServletUtil.getAppRequestParameters(request, null);
        log.info("param=" + param.toJSONString());
        // 'reply_type 0 普通用户留言 1 管理员问题解答'
        JSONObject resultJson = aadminService.getLeaveMsg(param, 1);
        log.info(resultJson.toJSONString());
        return resultJson;
    }
    /**
     * 常见问题添加
     *
     * @param result
     * @return
     */
    @RequestMapping(value = "/doAddQquest", method = RequestMethod.POST)
    public String doAddQquest(@ModelAttribute Leaving_Msg leaving_msg, BindingResult result) {
        log.info("success addAward parm=" + JSON.toJSON(leaving_msg));
        long ncurrent = System.currentTimeMillis();
        leaving_msg.setCreate_date(ncurrent);
        leaving_msg.setLast_update(ncurrent);
        leaving_msg.setMsg_date(ncurrent);
        leaving_msg.setIs_reply(2);
        leaving_msg.setUser_id(0);
        leaving_msg.setState('N');
        leaving_msg.setReply_type(1);
        long leaving_id = 0;
        String leaving_name = "leaving_id";
        appServerMapper.id_generator(leaving_name);
        leaving_id = appServerMapper.get_id_generator(leaving_name);
        leaving_msg.setLeaving_id(leaving_id);
        appServerMapper.InsertLeavingMsg(leaving_msg);
        return "redirect:/admin/question";
    }
    /* ===========================常见问题end==================================**/
    /* ===========================用户发单start==================================**/
    @RequestMapping("/offer")
    public String offer(Map<String, Object> map, HttpServletRequest request) {
        return "admin/offer";
    }

    /**
     * 删除发单
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteOffer/{help_order}", method = RequestMethod.GET)
    public String undoAdminUser(@PathVariable("help_order") String help_order, Map<String, Object> map) throws Exception {
        aadminService.deleteOfferHelp(help_order);
        return "redirect:/admin/offer";
    }
    /**
     * ajax获取getOfferList
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getOfferList", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getOfferList(HttpServletRequest request) throws Exception {
        JSONObject param = ServletUtil.getAppRequestParameters(request, null);
        //param={"et":"","help_status":"-1","help_type":"-1","is_admin":"-1","is_income":"-1","is_split":"-1","page":"1","st":"","user_phone":"","wallet_type":"-1"};result=
        JSONObject resultJson = aadminService.getPageOffer(param);
        log.info("param=" + param.toJSONString()+";result="+resultJson.toJSONString());
        return resultJson;
    }
    /* ===========================用户发单end==================================**/
        /* ===========================订单start==================================**/
    @RequestMapping("/order")
    public String order(Map<String, Object> map, HttpServletRequest request) {
        return "admin/order";
    }
    /**
     * ajax获取getOfferList
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getOrderList", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getOrderList(HttpServletRequest request) throws Exception {
        JSONObject param = ServletUtil.getAppRequestParameters(request, null);
        //param={"et":"","help_status":"-1","help_type":"-1","is_admin":"-1","is_income":"-1","is_split":"-1","page":"1","st":"","user_phone":"","wallet_type":"-1"};result=
        JSONObject resultJson = aadminService.getOrderOffer(param);
        log.info("param=" + param.toJSONString()+";result="+resultJson.toJSONString());
        return resultJson;
    }
    /* ===========================用户发单end==================================**/

}
