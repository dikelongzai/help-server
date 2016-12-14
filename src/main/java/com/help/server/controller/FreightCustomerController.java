package com.help.server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.help.server.domain.FrieghtMapper;
import com.help.server.domain.tables.Da_show_waybill_province;
import com.help.server.service.FreightService;
import com.help.server.util.CommonUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 客户分布
 * Created by houlongbin on 2016/11/11.
 *
 */
@Controller
@RequestMapping("freight")
public class FreightCustomerController {
    private static final Log log = LogFactory.getLog(FreightCustomerController.class);
    @Autowired
    private FreightService freightService;
    @Autowired
    private FrieghtMapper frieghtMapper;
    @RequestMapping("/")
    public String index() {
        return "freight/customer";
    }
    @RequestMapping("customer")
    public String customer() {
        return "freight/customer";
    }
    @RequestMapping("10001")
    /**
     * 运单流向
     */
    @ResponseBody
    public JSONObject getWayBill() {
        /**
         * 组装百度echars数据
         */
        List<Da_show_waybill_province> list=frieghtMapper.findTopNBill();
        return CommonUtil.getEcharsAllDataJson(list,frieghtMapper.findFromHost(),frieghtMapper.findToHost());
    }
//    @RequestMapping("waybillhour")
//    /**
//     * 运单小时展示
//     */
//    @ResponseBody
//    public List<Da_show_waybill_province> waybillhour() {
//        System.out.println("-------------->"+JSON.toJSON(frieghtMapper.findWayBillHour()));
//        return frieghtMapper.findWayBillHour();
//    }
//    @RequestMapping(value = "GetGoodLine", method = RequestMethod.GET)
//    /**
//     * 货源
//     */
//    @ResponseBody
//    public JSONObject GetGoodLine(@RequestParam(value="name") String inputStr, HttpServletRequest request) {
//
//        String inputInt =request.getParameter("name");
//        return freightService.getGoodLine(inputInt);
//    }
//    @RequestMapping("GetTopGood")
//    /**
//     * 货源
//     */
//    @ResponseBody
//    public JSONObject GetTopGood() {
//        System.out.println("-------------->"+JSON.toJSON(frieghtMapper.GetTopGood()));
//        return CommonUtil.getTopGood(frieghtMapper.GetTopGood());
//    }
//
//    @RequestMapping("GetUs")
//    /**
//     * 货源
//     */
//    @ResponseBody
//    public JSONObject getUs(){
//        System.out.println("-------------->"+JSON.toJSON(frieghtMapper.GetTopGood()));
//        return CommonUtil.getForUs();
//    }


}
