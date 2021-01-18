package com.example.cm.fastdevapp.dp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class WeatherResolver {
 
	private static final String TAG = "ChannelResolver";
 
	private final Context mContext;
 
	WeatherDataBaseHelper db;
	SQLiteDatabase mSQLiteDatabase;
 
	public WeatherResolver(Context c) {
 
		mContext = c;
		db = new WeatherDataBaseHelper(c);
		mSQLiteDatabase = db.getWritableDatabase();// then creat db
 
	}
 
	public ArrayList<Map<String, String>> getChannelList() {
 
		return null;
 
	}
 
	// insert one channel to system data base
	public boolean insertOneData(Context con, Map<String, String> data, byte[] bytes) {
 
		if (data == null || data.size() == 0)
			return false;
 
		// ContentResolver resolver = con.getContentResolver();
		// Cursor cursor = resolver.query(DataTools.CONTENT_URI, new String[] {
		// DataTools.CITY, DataTools.NAME },
		// DataTools.CITY + " = ? ",
		// new String[] { channel.get(DataTools.CITY) }, DataTools.NAME);
		Log.d("pin", "insertOneChannel..City Name:" + data.get(DataTools.CITY).toString());
		// Cursor cursor = mSQLiteDatabase.rawQuery("select * from " +
		// DataTools.TABLE_NAME + " where " + DataTools.CITY
		// + "='" + channel.get(DataTools.CITY).toString() + "'",
		// null);xing
		// Cursor cursor = mSQLiteDatabase.rawQuery(
		// "select * from " + DataTools.TABLE_NAME + " where " + DataTools.CITY
		// + "=?",
		// new String[] { channel.get(DataTools.CITY).toString() });
		Cursor cursor = mSQLiteDatabase.rawQuery(
				"select * from " + DataTools.TABLE_NAME, null);
		ContentValues value = new ContentValues();
		value.put(DataTools.CITY, data.get(DataTools.CITY));
		value.put(DataTools.TEMP_CUR, data.get(DataTools.TEMP_CUR));
		value.put(DataTools.TEMP_L, data.get(DataTools.TEMP_L));
		value.put(DataTools.TEMP_H, data.get(DataTools.TEMP_H));
		value.put(DataTools.STATUS, data.get(DataTools.STATUS));
 
		// value.put(DataTools.IMAGE, data.get(DataTools.IMAGE));bytes
		value.put(DataTools.IMAGE, bytes);
		// database have data ,then not insert
		if (cursor != null && cursor.moveToFirst()) {
 
			updateOneDatabyID(con, value, 1);
			con.getContentResolver().notifyChange(DataTools.CONTENT_URI, null);
			Log.d("pin", "insert have pre data...not insert,then update data, db notifyChange");
			cursor.close();
			return false;
		}
 
		// value.put(DataTools.HASHCODE,
		// channel.get(DataTools.PATH).hashCode());
		// resolver.insert(DataTools.CONTENT_URI, value);
		mSQLiteDatabase.insert(DataTools.TABLE_NAME, null, value);
 
		Log.d("pin", "insert data...success");
		// mSQLiteDatabase.notifyAll();
		con.getContentResolver().notifyChange(DataTools.CONTENT_URI, null);
		if (cursor != null) {
			cursor.close();
		}
		return true;
	}

	// 采用事务方式插入多条数据
	public boolean insertAllChannel2DB(Context con, Map<String, String> channelMap) {

		//SQLiteDatabase database = SQLiteDatabase.openDatabase(DataTools.DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
		SQLiteDatabase database = db.getWritableDatabase();

		if (database.isReadOnly()) {
			Log.e(TAG, "database.isReadOnly...");
			return false;
		}
		long start = Calendar.getInstance().getTimeInMillis();
		Log.e("8", "====== start insert time:" + String.valueOf(start));
		ContentResolver resolver = con.getContentResolver();
		try {
			database.beginTransaction();
			for (String name : channelMap.keySet()) {
				Log.v(TAG, "name:" + name);
				String path = channelMap.get(name);// key->values
				Log.v(TAG, "path:" + path);

				ContentValues value = new ContentValues();
				value.put(DataTools._ID, path.hashCode());
				value.put(DataTools.NAME, name);
				value.put(DataTools.PATH, path);
				String[] args = new String[] { String.valueOf(path.hashCode()) };
				database.delete(DataTools.TABLE_NAME, DataTools._ID + "=?", args);
				// resolver.insert(DataTools.CONTENT_URI, value);
				// String sql = "insert into " + DataTools.TABLE_NAME +
				// " values(?,?,?)";
				database.insert(DataTools.TABLE_NAME, null, value);
				// database.execSQL(sql, new Object[] { path.hashCode(), name,
				// path });
			}
			database.setTransactionSuccessful();
			database.endTransaction();
		} catch (Exception e) {
			Log.e(TAG, "Exception-》database.setTransaction not Successful()...");
			return false;
		} finally {
			database.close();
			Log.e("8", "====== caculate insert time:" + String.valueOf(Calendar.getInstance().getTimeInMillis() - start));

			// resolver.notifyChange(uri, observer)
		}
		return true;
	}
 
	public boolean deleteAllChannel(Context con) {
 
		return true;
	}
 
	public boolean deleteOneChannel(Context con, String channelPath) {
 
		return true;
	}
 
	public boolean updateOneDatabyID(Context con, ContentValues values, int id) {
 
		mSQLiteDatabase.update(DataTools.TABLE_NAME, values, "" + DataTools._ID + "=?", new String[] { String.valueOf(id) });
		Log.d("pin", "updateOneData data...success");
		return true;
	}
}
