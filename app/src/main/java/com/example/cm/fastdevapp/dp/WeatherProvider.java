package com.example.cm.fastdevapp.dp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * 数据库后台操作
 */
public class WeatherProvider extends ContentProvider {
 
	private static final String TAG = "ChannelProvider";
	private static UriMatcher mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	private WeatherDataBaseHelper mDBHelper;
	static {
		mMatcher.addURI(DataTools.AUTHORITY, "getAllData", DataTools.GET_ALL_CHANNEL);
	}
 
	@Override
	public boolean onCreate() {
 
		// TODO Auto-generated method stub
		Log.v("pin", "*************WeatherProvider onCreate db----------------");
		mDBHelper = new WeatherDataBaseHelper(this.getContext(), DataTools.DB_PATH, 1);
		return true;
	}
 
	public WeatherDataBaseHelper getDBHelper() {
 
		return mDBHelper;
	}
 
	// delete(String table, String whereClause, String[] whereArgs)
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
 
		// TODO Auto-generated method stub
		int rowId = 0;
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		rowId = db.delete(DataTools.TABLE_NAME, selection, selectionArgs);
		getContext().getContentResolver().notifyChange(uri, null);
		return rowId;
		// return 0;
	}
 
	@Override
	public String getType(Uri uri) {
 
		// TODO Auto-generated method stub
		return null;
	}
 
	// insert(String table, String nullColumnHack, ContentValues values)
	@Override
	public Uri insert(Uri uri, ContentValues values) {
 
		// TODO Auto-generated method stub
		SQLiteDatabase db = mDBHelper.getWritableDatabase();
		long rowId = db.insert(DataTools.TABLE_NAME, null, values);
		if (rowId > 0) {
			Log.v(TAG, "insert url return rowId " + rowId);
			getContext().getContentResolver().notifyChange(uri,
					null);// duopin
			return uri;
		}
 
		return null;
	}
 
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
						String[] selectionArgs, String sortOrder) {
 
		// TODO Auto-generated method stub
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		return db.query(DataTools.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
		// return null;
	}
 
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
 
		// TODO Auto-generated method stub
		int rowId = 0;
		SQLiteDatabase db = mDBHelper.getReadableDatabase();
		rowId = db.update(DataTools.TABLE_NAME, values, selection, selectionArgs);
		getContext().getContentResolver().notifyChange(uri, null);
		return rowId;
		// return 0;
	}
 
}
