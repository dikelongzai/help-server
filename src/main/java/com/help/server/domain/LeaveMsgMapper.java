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
	/**
	 * 删除留言
	 * @return
	 */
	@Update("UPDATE leaving_msg SET state='D' WHERE id=#{id} ")
	public boolean deleteNew(@Param("id") long id);
	/**
	 * 根据id获取留言
	 * @return
	 */
	@Select("select * from leaving_msg where id=#{id} ")
	public Leaving_Msg findNewsById(@Param("id") long id);
	/**
	 * 留言回复
	 * @return
	 */
	@Update("UPDATE leaving_msg SET state='U',is_reply=1,last_update=(SELECT UNIX_TIMESTAMP()*1000),reply_date=(SELECT UNIX_TIMESTAMP()*1000),reply_content=#{reply_content} WHERE id=#{id} ")
	public boolean updateNew(@Param("id") long id,@Param("reply_content") String reply_content);

}
