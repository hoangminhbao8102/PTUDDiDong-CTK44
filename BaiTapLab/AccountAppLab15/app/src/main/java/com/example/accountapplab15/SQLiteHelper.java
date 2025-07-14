package com.example.accountapplab15;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    Context context;
    private static final String DB_NAME ="account.db";
    SQLiteDatabase myDB;
    public SQLiteHelper(@Nullable Context context){
        super(context,DB_NAME,null,1);
        this.context = context;
    }
    public void openDB() throws SQLException {
        if (myDB == null || !myDB.isOpen()) {
            myDB = getWritableDatabase();
            Log.d("DB_PATH", context.getDatabasePath("account.db").getAbsolutePath());  // Thêm dòng này
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS Account (email TEXT PRIMARY KEY, password TEXT)";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void createTable(){
        //myDB.execSQL("DROP TABLE IF EXISTS Account");
        String query = "create table if not exists Account (email text PRIMARY KEY, password text)";
        myDB.execSQL(query);
    }
    public void insert(Account acc){
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",acc.getEmail());
        contentValues.put("password",acc.getPass());
        myDB.insert("Account",null,contentValues);
    }
    public boolean login(String email, String pass) {
        String query = "select * from account where email ='"+email+"' and password='" +
                pass+"'";
        @SuppressLint("Recycle") Cursor cursor = myDB.rawQuery(query,null);
        return cursor.getCount() > 0;
    }
    public void update(Account acc){
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",acc.getEmail());
        contentValues.put("password",acc.getPass());
        myDB.update("Account",contentValues,("email = ?"),new
        String[]{String.valueOf(acc.getEmail())});
    }

}
