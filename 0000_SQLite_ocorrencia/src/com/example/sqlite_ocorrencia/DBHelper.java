package com.example.sqlite_ocorrencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "ocorrencia.db" ;
	private static final int DATABASE_VERSION = 1;
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE ocorrencia (" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"nome TEXT NOT NULL," +
				"descricao TEXT," +
				"horarioregistro TEXT);";
		db.execSQL(sql);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS ocorrencia");
		onCreate(db);
	}
}