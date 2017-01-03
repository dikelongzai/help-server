package com.help.server.domain;
import com.help.server.domain.tables.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 客户分布，订单流向
 */
@Mapper
public interface NewsMapper {
	/**
	 * 获取所有新闻不包括已删除
	 * @return
     */
	@Select("select new_id,new_title,new_content,create_date from news where state<>'D' ")
	public List<News> findAllNews();











	
}
