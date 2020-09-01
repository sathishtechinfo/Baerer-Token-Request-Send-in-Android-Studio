package com.example.mvpdemo.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lalit on 9/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserLog.db";


    private static final String TABLE_USERLIST = "uselist";



    //Student table
    private static final String COLUMN_USER_ID = "userlist_id";
    private static final String COLUMN_FIRSTNAME = "firstname";
    private static final String COLUMN_LASTNAME = "lastname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_USERBIO = "bio";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_INSTALINK = "instalink";
    private static final String COLUMN_YOUTUBELINK = "youtubelink";
    private static final String COLUMN_WEBSITELINK = "websitelink";
    private static final String COLUMN_SEVICETOKEN= "devicetoken";




    SQLiteDatabase db;


    private String CREATE_USERLIST_TABLE = "CREATE TABLE " + TABLE_USERLIST + "("

        + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_FIRSTNAME + " TEXT, "
          + COLUMN_LASTNAME + " TEXT," + COLUMN_EMAIL + " TEXT," +
            COLUMN_PHONE + " TEXT," + COLUMN_IMAGE + " TEXT," + COLUMN_USERBIO + " TEXT," + COLUMN_INSTALINK + " TEXT," + COLUMN_YOUTUBELINK + " TEXT," +
            COLUMN_WEBSITELINK + " TEXT," + COLUMN_SEVICETOKEN + " TEXT" +")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USERLIST;

    /**
     * Constructor
     * 
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERLIST_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
       // db.execSQL(DR);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param
     */
  /*  public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_PHONE, user.getPhone());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());



        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }*/

    public void addUserList(Userlist userlist)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_FIRSTNAME, userlist.getFirstname());
        values.put(COLUMN_LASTNAME,userlist.getLastname());
        values.put(COLUMN_EMAIL, userlist.getEmail());
        values.put(COLUMN_PHONE, userlist.getMobile());
        values.put(COLUMN_IMAGE, userlist.getImage());
        values.put(COLUMN_USERBIO, userlist.getUserbio());
        values.put(COLUMN_INSTALINK, userlist.getInstaurl());
        values.put(COLUMN_WEBSITELINK, userlist.getWebsitelink());
        values.put(COLUMN_YOUTUBELINK, userlist.getYoutubelink());
        values.put(COLUMN_SEVICETOKEN, userlist.getDevicetoken());



        // Inserting Row
        db.insert(TABLE_USERLIST, null, values);
        db.close();
    }



    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(Userlist user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, user.getFirstname());
        values.put(COLUMN_LASTNAME, user.getLastname());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PHONE, user.getMobile());

        // updating row
        db.update(TABLE_USERLIST, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getID())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(Userlist user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USERLIST, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getID())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param
     * @return true/false
     */

    public boolean checkStudent(String name) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_FIRSTNAME
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_FIRSTNAME + " = ?";

        // selection argument
        String[] selectionArgs = {name};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USERLIST, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


    /**
     * This method to check user exist or not
     *
     * @param username
     * @param password
     * @return true/false
     */
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_USERLIST + " WHERE user_name=? AND user_password=?", new String[]{username,password});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;

    }
    public List<Userlist> getAllStudent(String id) {
        // array of columns to fetch
        String[] columns = {

                COLUMN_USER_ID,
                COLUMN_FIRSTNAME,
                COLUMN_LASTNAME,
                COLUMN_EMAIL,
                COLUMN_PHONE,
                COLUMN_USERBIO,
                COLUMN_IMAGE,
                COLUMN_INSTALINK,
                COLUMN_YOUTUBELINK,
                COLUMN_WEBSITELINK,
                COLUMN_SEVICETOKEN


        };

        List<Userlist> userList1 = new ArrayList<Userlist>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

        if(id.isEmpty())
        {
             cursor = db.rawQuery("SELECT * FROM " + TABLE_USERLIST, null);

        }else {
             cursor = db.rawQuery("SELECT * FROM " + TABLE_USERLIST + " WHERE userlist_id=?", new String[]{id});
        }
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Userlist userlist = new Userlist();
                userlist.setID(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)));
                userlist.setFirstname(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)));
                userlist.setLastname(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)));
                userlist.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                userlist.setMobile(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));
                userlist.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));
                userlist.setInstaurl(cursor.getString(cursor.getColumnIndex(COLUMN_INSTALINK)));
                userlist.setUserbio(cursor.getString(cursor.getColumnIndex(COLUMN_USERBIO)));
                userlist.setYoutubelink(cursor.getString(cursor.getColumnIndex(COLUMN_YOUTUBELINK)));
                userlist.setWebsitelink(cursor.getString(cursor.getColumnIndex(COLUMN_WEBSITELINK)));
                userlist.setDevicetoken(cursor.getString(cursor.getColumnIndex(COLUMN_SEVICETOKEN)));

                // Adding user record to list
                userList1.add(userlist);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList1;
    }



}
