package cn.boc.newsdemo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wanglj on 16/7/20.
 */

public interface NewsApi {

    @GET("users")
    Call<User> login(@Query("username") String username, @Query("password") String password);
}
