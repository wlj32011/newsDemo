package cn.boc.newsdemo.fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;

/**
 * Created by wanglj on 16/7/21.
 */

public class MySQLiteHelper extends SQLiteOpenHelper{


    public static final String DB_NAME = "news";
    public static final int DB_VRESION = 2;


    public MySQLiteHelper(Context context) {

        super(context, DB_NAME, null, DB_VRESION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table news (" +
                "id integer," +
                "title text,"+
                "content text"+
                ")";
        sqLiteDatabase.execSQL(sql);

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        
    }
}
