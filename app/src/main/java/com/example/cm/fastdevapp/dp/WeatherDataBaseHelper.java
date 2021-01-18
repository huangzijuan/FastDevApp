package com.example.cm.fastdevapp.dp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 数据库创建
 */
public class WeatherDataBaseHelper extends SQLiteOpenHelper {
 
	private static final String TAG = "pin";
 
	private static final String CREAT_TABLE_SQL = "create table " + DataTools.TABLE_NAME + "("
			+ DataTools._ID + " integer primary key,"
			+ DataTools.CITY + " String not null,"
			+ DataTools.TEMP_CUR + " String not null,"
			+ DataTools.TEMP_L + " String not null,"
			+ DataTools.TEMP_H + " String not null,"
			+ DataTools.STATUS + " String not null,"
			+ DataTools.IMAGE + " blob not null" + ")";// blob
 
	/**
	 * @param context
	 */
	public WeatherDataBaseHelper(Context context) {
 
		super(context, DataTools.DB_NAME, null, DataTools.DATABASE_VERSION);
	}
 
	/**
	 * @param context
	 * @param name
	 * @param version
	 */
	public WeatherDataBaseHelper(Context context, String name, int version) {
 
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
		Log.v(TAG, " WeatherDataBaseHelper-------constructor------------");
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
		Log.v(TAG, "WeatherDataBaseHelper on creat-------------------");
		db.execSQL(CREAT_TABLE_SQL);
	}
 
	/**
	 * when version changed,updata database
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
		Log.v(TAG, "on upgrade");
		String sql = " DROP TABLE IF EXISTS " + DataTools.TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}
 
}