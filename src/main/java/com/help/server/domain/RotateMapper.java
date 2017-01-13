package com.help.server.domain;
import com.help.server.domain.tables.News;
import com.help.server.domain.tables.Rotate;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 轮播图
 */
@Mapper
public interface RotateMapper {
	/**
	 * 获取所有轮播图
	 * @return
     */
	@Select("select id,rotate_url,create_date from rotate_news where state<>'D' ")
	public List<Rotate> findAllNews();
	/**
	 * 删除轮播图
	 * @return
	 */
	@Update("UPDATE rotate_news SET state='D' WHERE id=#{id} ")
	public boolean deleteRotate(@Param("id") long id);
	/**
	 * 根据id获取轮播图
	 * @return
	 */
	@Select("select id,rotate_url,create_date from rotate_news where id=#{id} ")
	public News findRotateById(@Param("id") long id);
	/**
	 * 新建轮播图
	 * @param rotate_url
     * @return
     */
	@Insert("INSERT INTO rotate_news(state,create_date,new_url)SELECT 'N',(SELECT UNIX_TIMESTAMP()*1000),#{rotate_url}")
    public boolean addRotate(@Param("rotate_url") String rotate_url);


	/**
	 * 轮播图更新
	 * @return
	 */
	@Update("UPDATE rotate_news SET state='U',last_update=(SELECT UNIX_TIMESTAMP()*1000),rotate_url=#{rotate_url}  WHERE id=#{id} ")
	public boolean updateRotate(@Param("id") long id, @Param("rotate_url") String rotate_url);

}
