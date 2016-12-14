package com.help.server.util;

import java.util.HashMap;

public class CacheMap {
	public enum DataType {
		VEHICLEPOSTION, // 车辆位置
		VEHICLECOUNT, // 车辆总数
		VEHICLEONLINECOUNT, // 车辆在线数
		VEHICLEONLINERUNCOUNT, // 车辆在线且运行数
		VEHICLEVTYPECOUNT, // 各车型车辆数
		VEHICLERUNTYPECOUNT, // 各运输类型车辆数[干线、支线、成配]
		VEHICLEVBIZCOUNT, // 各行业车辆数
		VEHICLEVBIZCOUNTAll, // 各行业车辆数
		VEHICLELATLONS, // 车辆轨迹
		VEHICLELATLONSVIDS, // 车辆轨迹可选车辆
		COLDVEHICLEPOSTION, // 冷链车位置
		COLDVEHICLEVIDS, // 车辆轨迹可选车辆
		HPI, HPITYPE,HPIDAY,
		TRANSPORTLINE,   //
		MULTIVEHICLE,   //
		MULTIVEHICLETOPLINE,
		ROADTOP,   //
	}

	private static HashMap<DataType, CacheObject> cachemap = new HashMap<DataType, CacheObject>();
	private static CacheMap cmap;

	public static CacheMap get() {
		if (cmap == null) {
			cmap = new CacheMap();
		}
		return cmap;
	}

	private CacheMap() {
		super();
	}

	/**
	 * 读取数据
	 * 
	 * @param dataType
	 * @return
	 */
	public Object readVal(DataType dataType) {
		return cachemap.get(dataType).getObject();
	}

	/**
	 * 缓存数据
	 * 
	 * @param dataType
	 * @param object
	 */
	public void writeVal(DataType dataType, CacheObject object) {
		cachemap.put(dataType, object);
	}

	/**
	 * 数据是否已经过期
	 * 
	 * @param dataType
	 * @return
	 */
	public boolean isOverTime(DataType dataType) {
		if (!cachemap.containsKey(dataType))
			return true;
		return cachemap.get(dataType).isOverTime();
	}
}
