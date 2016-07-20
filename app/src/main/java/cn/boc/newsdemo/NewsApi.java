package cn.boc.newsdemo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static android.R.attr.password;

/**
 * Created by wanglj on 16/7/20.
 */

public interface NewsApi {

    @GET("users")
    Call<User> login(@Query("username") String username, @Query("password") String password);

    @GET("news")
    Call<News> getNews();


}
