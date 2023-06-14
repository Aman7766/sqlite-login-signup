package com.amandeep.sqliteloginsignup;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION=1;
    public static final String DB_NAME="SQLiteuser";
    public  final static String TABLE_NAME="user_register";

    public static final String COL_1="name";
    public static final String COL_2="mobile";
    public static final String COL_3="email";
    public static final String COL_4="password";

    public DBHelper( Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            String query="CREATE TABLE "+TABLE_NAME+"("
                    +COL_1+" TEXT, "
                    +COL_2+" TEXT, "
                    +COL_3+" TEXT, "
                    +COL_4+" TEXT)";
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("SQLiteuser","table creation failed",e);
        }



    }

    public boolean insertData(String name,String mobile,String email,String password)
    {
        try{
            String insert="INSERT INTO "+TABLE_NAME+ " Values('"+name+"','"+mobile+"','"+email+"','"+password+"')";
            SQLiteDatabase db=this.getWritableDatabase();
            db.execSQL(insert);
            return true;
        }
        catch (Exception e)
        {
            Log.e("SQLiteuser","table insertion failed",e);
            return false;
        }

    }

    public Cursor validatelogin(String email,String password)
    {
        try{
            String login = "SELECT " + COL_3 + ", " + COL_4 + " FROM " + TABLE_NAME + " WHERE " + COL_3 + "='" + email + "' AND " + COL_4 + "='" + password + "'";
            SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery(login,null);
        }
        catch(Exception e)
        {
            Log.e("SQLiteuser","Login failed",e);
            return  null;
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        try {
            String drop="DROP TABLE IF EXISTS "+ TABLE_NAME;
            db.execSQL(drop);
            onCreate(db);

        }
        catch (Exception e)
        {
            Log.e("SQLiteuser","table Upgrade failed",e);
        }

    }
}
