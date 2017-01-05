package com.help.server.domain;
import com.help.server.domain.tables.News;
import org.apache.ibatis.annotations.*;

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
	@Select("select id,new_title,new_content,create_date from news where state<>'D' ")
	public List<News> findAllNews();
	/**
	 * 获取所有新闻不包括已删除
	 * @return
	 */
	@Update("UPDATE news SET state='D' WHERE id=#{id} ")
	public boolean deleteNew(@Param("id") long id);
	/**
	 * 根据id获取新闻
	 * @return
	 */
	@Select("select id,new_title,new_content,create_date from news where id=#{id} ")
	public News findNewsById(@Param("id") long id);
	/**
	 * 新建新闻
	 * @param new_title
	 * @param new_content
     * @return
     */
	@Insert("INSERT INTO news(state,create_date,new_title,new_content)SELECT 'N',(SELECT UNIX_TIMESTAMP()*1000),#{new_title},#{new_content}")
    public boolean addNews(@Param("new_title") String new_title,@Param("new_content") String new_content);


	/**
	 * 新闻更新
	 * @return
	 */
	@Update("UPDATE news SET state='U',last_update=(SELECT UNIX_TIMESTAMP()*1000),new_title=#{new_title},new_content=#{new_content} WHERE id=#{id} ")
	public boolean updateNew(@Param("id") long id,@Param("new_title") String new_title,@Param("new_content") String new_content);

}
