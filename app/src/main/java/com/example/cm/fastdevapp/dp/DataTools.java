package com.example.cm.fastdevapp.dp;

import android.net.Uri;

/**
 * 数据库基本数据
 */
public final class DataTools {
 
	public static final String AUTHORITY = "yourpackageName.WeatherProvider";
 
	public static final String PACKAGE_NAME = "yourpackageName";
	public static final int GET_ALL_CHANNEL = 100;
 
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/getAllData");
 
	public static final String TABLE_NAME = "weatherTable";
	public static final String DB_PATH = "/data/data/" + PACKAGE_NAME + "/databases/weather.db";
	public static final String DB_NAME = "weather.db";
 
	public static final int DATABASE_VERSION = 1;
 
	public static final String _ID = "id";
 
	public static final String HASHCODE = "hashcode";
 
	public static final String CITY = "city";
	public static final String TEMP_CUR = "temp_cur";
	public static final String TEMP_L = "temp_l";
	public static final String TEMP_H = "temp_h";
	public static final String IMAGE = "image";
	public static final String STATUS = "status";
 
}
