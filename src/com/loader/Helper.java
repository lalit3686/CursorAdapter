package com.loader;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper{

	private static SQLiteDatabase db;
	private static final String db_name = "myDB";
	private static final int version = 1;
	private static final String tbl_name = "login";
	public static final String tbl_col_username = "username";
	public static final String tbl_col_password = "password";
	public static final String tbl_col_id = "_id";
	private static final String create_DB = "Create table if NOT EXISTS "+tbl_name+" ("+tbl_col_id+" integer primary key autoincrement, "+tbl_col_username+" text not null, "+tbl_col_password+" text not null)";
	
	public Helper(Context context) {
		super(context, db_name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(create_DB);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	public void open(){
		db = this.getWritableDatabase();		
	}
	
	public void closeDB(){
		if(db.isOpen())
		db.close();
	}
	
	public boolean isOpen() {
		return db.isOpen();
	}

	public Cursor getAll(){
		return getReadableDatabase().query(tbl_name, new String[]{"_id","username","password"}, null, null, null,null, null);
//		OR return getReadableDatabase().query("login", null, null, null, null,null, null);
	}
	
	public void insert(String username, String password) {
		db.execSQL("insert into "+tbl_name+" (username, password) values('"+username+"','"+password+"')");
	}
	
	public void delete(String id) {
		db.execSQL("delete from "+tbl_name+" where _id='"+id+"'");
	}
}
