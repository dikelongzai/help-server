package com.help.server.domain;

import com.help.server.domain.tables.Da_Show_Corp_City;
import com.help.server.domain.tables.Da_show_waybill_province;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * 客户分布，订单流向
 */
@Mapper
public interface FrieghtMapper {
	/**
	 * 客户分布
	 * @return
     */
	@Select("select city,count,customer from da_show_corp_city where count>2 ")
	public List<Da_Show_Corp_City> findAllCorp();


	/**
	 * 运单流向
	 * @return
	 */
	@Select("select from_pro,to_pro,num from da_show_waybill_province ")
	public List<Da_show_waybill_province> findTopNBill();

	/**
	 * 运单流向热度 根据查询结果 出发和终止地基本趋势符合
	 * @return
     */
	@Select("SELECT SUM(num)  AS num,from_pro FROM da_show_waybill_province GROUP BY from_pro ORDER BY num DESC")
	public List<Da_show_waybill_province> findFromHost();

	/**
	 * 运单流向热度 根据查询结果 出发和终止地基本趋势符合
	 * @return
	 */
	@Select("SELECT SUM(num)  AS num,to_pro FROM da_show_waybill_province GROUP BY to_pro ORDER BY num DESC")
	public List<Da_show_waybill_province> findToHost();

	/**
	 * 运单每小时统计
	 * @return
	 */
	@Select("SELECT from_pro,to_pro,num FROM da_show_waybill_province_hour ORDER BY num DESC LIMIT 10")
	public List<Da_show_waybill_province> findWayBillHour();




	
}
