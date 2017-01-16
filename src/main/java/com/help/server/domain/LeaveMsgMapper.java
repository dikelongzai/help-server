package com.help.server.domain;
import com.help.server.domain.tables.Leaving_Msg;
import com.help.server.domain.tables.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 留言
 */
@Mapper
public interface LeaveMsgMapper {
	/**
	 * 获取所有新闻不包括已删除
	 * @return
     */
	@Select("select * from leaving_msg order by create_date desc ")
	public List<Leaving_Msg> findAllNews();

}
