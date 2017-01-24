package com.help.server.domain;
import com.help.server.domain.tables.Award;
import com.help.server.domain.tables.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *动态奖励规则设置
 */
@Mapper
public interface AwardMapper {
	/**
	 * 获取动态奖励不包括已删除
	 * @return
     */
	@Select("select * from dynamic_award_rules where state<>'D' ")
	public List<Award> findAllNews();
	/**
	 * 获取动态奖励规则设置不包括已删除
	 * @return
	 */
	@Update("UPDATE dynamic_award_rules SET state='D' WHERE id=#{id} ")
	public boolean deleteNew(@Param("id") long id);
	/**
	 * 根据id获取动态奖励规则设置
	 * @return
	 */
	@Select("select * from dynamic_award_rules where id=#{id} ")
	public Award findNewsById(@Param("id") long id);
	/**
	 * 新建动态奖励规则设置
     * @return
     */
	@Insert("INSERT INTO dynamic_award_rules(state,create_date,dynamic_id" +
			",direct_num,team_num," +
			"one_generation,two_generation,three_generation," +
			"four_generation," +
			"user_title_id,user_title)SELECT 'N'," +
			"(SELECT UNIX_TIMESTAMP()*1000)," +
			"(select id_count  from id_generator where id_name ='dynamic_id')," +
			"#{direct_num},#{team_num}," +
			"#{one_generation},#{two_generation}," +
			"#{three_generation},#{four_generation},(select id_count  from id_generator where id_name ='user_title_id'),#{user_title}")
    public boolean addNews(@Param("direct_num") int direct_num , @Param("team_num") int team_num,
						   @Param("one_generation") float one_generation,
						   @Param("two_generation") float two_generation,
						   @Param("three_generation") float three_generation,
						   @Param("four_generation") float four_generation,
						   @Param("user_title") String user_title);


	/**
	 * 动态奖励规则设置更新
	 * @return
	 */
	@Update("UPDATE dynamic_award_rules SET state='U',last_update=(SELECT UNIX_TIMESTAMP()*1000)," +
			"team_num=#{team_num}," +
			"direct_num=#{direct_num},one_generation={#one_generation},two_generation={#two_generation}," +
			"three_generation={#three_generation},four_generation={#four_generation},user_title={#user_title} WHERE id=#{id}")
	public boolean updateNew(@Param("id") long id, @Param("direct_num") int direct_num , @Param("team_num") int team_num,
							 @Param("one_generation") float one_generation,
							 @Param("two_generation") float two_generation,
							 @Param("three_generation") float three_generation,
							 @Param("four_generation") float four_generation,
							 @Param("user_title") String user_title);

}
