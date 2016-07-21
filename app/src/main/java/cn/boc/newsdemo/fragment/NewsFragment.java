package cn.boc.newsdemo.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.boc.newsdemo.News;
import cn.boc.newsdemo.NewsApi;
import cn.boc.newsdemo.R;
import cn.boc.newsdemo.activity.LoginActivity;
import cn.boc.newsdemo.adapter.RecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static cn.boc.newsdemo.R.id.news;

/**
 * Created by wanglj on 16/7/20.
 */

public class NewsFragment extends Fragment{


    RecyclerViewAdapter recyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recyclerview,container,false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        News news = new News();

        news.setData(new ArrayList<News.DataEntity>());

        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(),news);

        recyclerView.setAdapter(recyclerViewAdapter);


        getNewsList();




        return view;

    }


    private void getNewsList() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(LoginActivity.SERVERURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsApi newsapi = retrofit.create(NewsApi.class);

        Call<News> call  = newsapi.getNews();


        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                News news = response.body();





//                db.execSQL("insert into news");




                recyclerViewAdapter.refreshData(news);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });


    }


    /**
     * 数据库操作
     * @param news
     */
    private void operateDB(News news){
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(getActivity());
        SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();

        for(News.DataEntity entity :news.getData()){
            ContentValues cv = new ContentValues();
            cv.put("id",entity.getId());
            cv.put("title",entity.getTitle());
            cv.put("content",entity.getContent());
            db.insert("news",null,cv);
        }


        Cursor cursor = db.rawQuery("select * from news where id=?",new String[]{"1"});
//        Cursor cursor = db.rawQuery("select * from news",null);


        while (cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));

        }
    }
}
