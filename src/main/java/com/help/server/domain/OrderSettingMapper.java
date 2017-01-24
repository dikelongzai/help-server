package com.help.server.domain;
import com.help.server.domain.tables.OrderSetting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 订单规则设置
 */
@Mapper
public interface OrderSettingMapper {
	/**
	 * 获取订单规则
	 * @return
	 */
	@Select("select * from order_Setting where id=1 ")
	public OrderSetting findNewsById();
	/**
	 * 修改订单规则
	 * @return
	 */
	@Update("UPDATE order_Setting SET state='U',last_update=(SELECT UNIX_TIMESTAMP()*1000),is_order_timer={#is_order_timer},start_date={#start_date}, end_date={#end_date},order_max_money={#order_max_money},match_date_min={#match_date_min},match_date_max={#match_date_max},freezing_time={#freezing_time},min_order_amount={#min_order_amount},max_order_num={#max_order_num},static_min_money={#static_min_money},static_times_money={#static_times_money},dynamic_min_money={#dynamic_min_money},dynamic_times_money={#dynamic_times_money},dynamic_max_money={#dynamic_max_money},dynamic_deduct_proportion={#dynamic_deduct_proportion},interest_not_paid={#interest_not_paid},interest_paid={#interest_paid} WHERE id=1 ")
	public boolean updateNew(@Param("is_order_timer") int is_order_timer,
							 @Param("start_date") long  start_date,
							 @Param("end_date") long  end_date,
							 @Param("order_max_money") float  order_max_money,
							 @Param("match_date_min") int  match_date_min,
							 @Param("match_date_max") int  match_date_max,
							 @Param("freezing_time") int  freezing_time,
							 @Param("min_order_amount") int  min_order_amount,
							 @Param("max_order_num") int  max_order_num,
							 @Param("static_min_money") int  static_min_money,
							 @Param("static_times_money") int  static_times_money,
							 @Param("dynamic_min_money") int  dynamic_min_money,
							 @Param("dynamic_times_money") int  dynamic_times_money,
							 @Param("dynamic_max_money") int  dynamic_max_money,
							 @Param("interest_not_paid") float  interest_not_paid,
							 @Param("interest_paid") float  interest_paid
	);

}
