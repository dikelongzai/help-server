package com.help.server.service;

import com.help.server.domain.AppServerMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author houlongbin
 *
 */
@Service
public class AppService {

	private Logger log = Logger.getLogger(AppService.class);
	
	@Autowired
	private AppServerMapper appServerMapper;

	/**
	 *
	 * @param id_name
	 * @return
	 */
	public long getIdGener(String id_name){
		long id=0;
		try {
			id=appServerMapper.get_id_generator(id_name);
			log.info("-------------->getIdGener id="+id+";id_name="+id_name);
			appServerMapper.id_generator(id_name);
			log.info("-------------->update id_generator id_name=" + id_name + ";id="+(id+1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

}
