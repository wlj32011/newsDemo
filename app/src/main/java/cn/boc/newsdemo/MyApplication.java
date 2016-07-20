package cn.boc.newsdemo;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by wanglj on 16/7/20.
 */

public class MyApplication extends Application{


    SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = this.getSharedPreferences("News_APP",MODE_PRIVATE);

    }


    public SharedPreferences getSharedPreferences(){
        return sharedPreferences;
    }
}
