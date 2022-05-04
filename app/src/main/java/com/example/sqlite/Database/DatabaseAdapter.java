package com.example.sqlite.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlite.Models.Contact;

import java.util.ArrayList;

public class DatabaseAdapter {
    SQLiteDatabase database;
    DBHelper dbHelper;

    public DatabaseAdapter(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void openDB() {
        database = dbHelper.getWritableDatabase();
    }

    public void closeDB() {
        database.close();
    }

    public long insertContact (String name, String address, String email){
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_NAME, name);
        cv.put(DBHelper.COLUMN_ADDRESS, address);
        cv.put(DBHelper.COLUMN_EMAIL, email);
        return database.insert(DBHelper.CONTACT_TABLE,
                "", cv);
    }

    //Add getContacts() in DatabaseAdapter Class
//This method will return data from database in a Arraylist.
    @SuppressLint("Range")
    public ArrayList<Contact> getContacts(){
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        String[] columns = {DBHelper.COLUMN_ID, DBHelper.COLUMN_NAME,
                DBHelper.COLUMN_ADDRESS, DBHelper.COLUMN_EMAIL,};
        Cursor cursor = database.query(DBHelper.CONTACT_TABLE, columns, null, null, null, null, null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            for(int i = 0; i < cursor.getCount(); i++){
                String id = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
                String email = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_EMAIL));
                String address = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_ADDRESS));
                Contact c = new Contact(id, name, email, address);
                contacts.add(c);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return contacts;
    }

    public Contact getContactByID(int id) {
        Cursor cursor = database.query(DBHelper.CONTACT_TABLE, new String[]
                        { DBHelper.COLUMN_ID,
                                DBHelper.COLUMN_NAME, DBHelper.COLUMN_EMAIL,
                                DBHelper.COLUMN_ADDRESS }, DBHelper.COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Contact contact = new Contact(cursor.getString(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return contact
        return contact;
    }

    public int updateContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, contact.getName());
        values.put(DBHelper.COLUMN_EMAIL, contact.getEmail());
        values.put(DBHelper.COLUMN_ADDRESS, contact.getAddress());
        // updating row
        return database.update(DBHelper.CONTACT_TABLE, values,
                DBHelper.COLUMN_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }

    public int deleteContact(int id) {
        // delete row in students table based on id
        return database.delete(DBHelper.CONTACT_TABLE,
                DBHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }


}
