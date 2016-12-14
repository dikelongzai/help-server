package com.help.server.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.help.server.domain.FrieghtMapper;
import com.help.server.util.CommonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author houlongbin
 *
 */
@Service
public class FreightService {

	private Logger log = Logger.getLogger(FreightService.class);
	
//	@Autowired
//	private FrieghtMapper frieghtMapper;
//
//
//	public JSONObject getGoodLine(String pro){
//		JSONObject returnJson=null;
//		List<ProvinceReq> res = null;
//		try {
//			res = frieghtMapper.goodsShow(pro);
//			 returnJson= CommonUtil.getTopGoodLine(res);
//			log.info("-------------->GetGoodLine"+returnJson.toJSONString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return returnJson;
//	}

}
