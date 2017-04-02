package com.example.rhutc.ryandemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.rhutc.ryandemo.util.UtilLog;

public class SQLiteActivity extends BaseActivity {

    private android.database.sqlite.SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: Make a layout
//        setContentView(R.layout.activity_sqlite);
        SQLiteDatabase db =this.openOrCreateDatabase("test_db.db", Context.MODE_PRIVATE, null);
        createTable();
    }

    private void createTable() {
        String sql="create table t_user (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL,password TEXT NOT NULL)";
        db.execSQL(sql);
    }

    private void addValue(){
        ContentValues values=new ContentValues();
        values.put("name", "Yan");
        values.put("password", "123456");
        db.insert("t_user", "id", values);
    }


    private void deleteValue(){
        // use id
         db.delete("t_user", "id=1", null);
        // use name and password
         db.delete("t_user", "name=? and password =?", new String[]{"Yan","112233"});
    }

    private void queryValue(){
        // use rawQuery
        Cursor c1 = db.rawQuery("select * from t_user", null);
        Cursor c2 = db.rawQuery("select * from t_user where id=1", null);
        Cursor c3 = db.rawQuery("select * from t_user where id=?", new String[]{"1"});
        // use query()
        Cursor c = db.query("t_user", new String[]{"id","name"}, "name=?", new String[]{"Yan"}, null, null, null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            String msg="";
            for(int i=0,j=c.getColumnCount();i<j;i++){
                msg+="--"+c.getString(i);
            }
            UtilLog.logD("SQLite", "data:"+msg);
            c.moveToNext();
        }
    }

    private void update(){
        ContentValues values=new ContentValues();
        values.put("password", "111111");
        // method 1
        db.update("t_user", values, "id=1", null);
        // method 2
        db.update("t_user", values, "name=? or password=?",new String[]{"Yan","123456"});
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
