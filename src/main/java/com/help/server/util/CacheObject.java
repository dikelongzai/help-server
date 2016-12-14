package com.help.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CacheObject {
	public SimpleDateFormat format;// 格式化为年月
	private String lastupdatetime;
	private Object object;

	public boolean isOverTime() {
		return !format.format(new Date()).equals(lastupdatetime);
	}

	public CacheObject(Object object, String updateTimeType) {
		super();
		this.object = object;
		setFormat(updateTimeType);
		refreshUpdateTime();

	}

	public void refreshUpdateTime() {
		this.lastupdatetime = format.format(new Date());
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
		refreshUpdateTime();
	}

	public void setFormat(String fstr) {
		try {
			format = new SimpleDateFormat(fstr);
		} catch (Exception e) {
			format = new SimpleDateFormat("yyyy-mm-dd");
		}
	}

}
