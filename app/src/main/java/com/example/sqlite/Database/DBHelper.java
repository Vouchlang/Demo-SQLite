package com.example.sqlite.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "Contact_Details.db";
    public static String CONTACT_TABLE = "contact";
    public static String COLUMN_ID = "id";
    public static String COLUMN_NAME = "name";
    public static String COLUMN_ADDRESS = "address";
    public static String COLUMN_EMAIL = "email";
    public static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS "
            + CONTACT_TABLE
            + " (id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " name TEXT, "
            + " address TEXT,"
            + " email TEXT)";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
