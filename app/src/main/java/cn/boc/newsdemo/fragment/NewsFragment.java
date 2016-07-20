package cn.boc.newsdemo.fragment;

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
                recyclerViewAdapter.refreshData(news);
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });


    }
}
