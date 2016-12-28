package com.help.server.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author houlongbin
 *
 */
@Service
public class AppService {

	private Logger log = Logger.getLogger(AppService.class);
	
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
