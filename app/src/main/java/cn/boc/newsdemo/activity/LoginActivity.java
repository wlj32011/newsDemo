package cn.boc.newsdemo.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import cn.boc.newsdemo.MyApplication;
import cn.boc.newsdemo.NewsApi;
import cn.boc.newsdemo.R;
import cn.boc.newsdemo.TimeCount;
import cn.boc.newsdemo.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String MESSAGE_ACTION = "MESSAGE";


    MyCodeReceiver myReceiver;

    EditText codeEditText,phoneEditText;

    Button getCodeButton,loginButton;


    TimeCount timeCount;


    public static final String SERVERURL = "http://192.168.43.116:3000/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        codeEditText = (EditText) findViewById(R.id.codeEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);
        getCodeButton = (Button) findViewById(R.id.getCodeButton);
        loginButton = (Button) findViewById(R.id.loginButton);


        MyApplication myApplication = (MyApplication) getApplication();

        SharedPreferences sharedPreferences = myApplication.getSharedPreferences();


        String username = sharedPreferences.getString("username","");

        String password = sharedPreferences.getString("password","");


        phoneEditText.setText(username);

        codeEditText.setText(password);


        if(phoneEditText.getText().length() != 11){
            loginButton.setEnabled(false);
            loginButton.setClickable(false);
        }



        phoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 11){
                    loginButton.setEnabled(true);
                    loginButton.setClickable(true);
                }else{
                    loginButton.setEnabled(false);
                    loginButton.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        getCodeButton.setOnClickListener(this);


        loginButton.setOnClickListener(this);


    }


    @Override
    protected void onResume() {
        super.onResume();
        if(myReceiver == null){
            myReceiver = new MyCodeReceiver(codeEditText);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MESSAGE_ACTION);
        this.registerReceiver(myReceiver,intentFilter);
    }


    @Override
    protected void onStop() {
        super.onStop();

        if(timeCount != null)
            timeCount.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(myReceiver != null){
            this.unregisterReceiver(myReceiver);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginButton:

                String username = phoneEditText.getText().toString();
                String password = codeEditText.getText().toString();


                MyApplication myApplication = (MyApplication) getApplication();

                SharedPreferences sharedPreferences = myApplication.getSharedPreferences();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username",username);
                editor.putString("password",password);
                editor.commit();



//                MyHttpTask myHttpTask = new MyHttpTask();
//                myHttpTask.execute(SERVERURL,username,password);

                login(username,password);

                break;
            case R.id.getCodeButton:
                String phone = phoneEditText.getText().toString();
                if(phone.length() == 11){
                    timeCount = new TimeCount(getCodeButton,60*1000,1000);
                    timeCount.start();
                }else{
                    Toast.makeText(this,"请输入合法的手机号",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    private void login(String username,String password){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVERURL).build();

        NewsApi newsApi = retrofit.create(NewsApi.class);

        Call<User> userCall = newsApi.login(username,password);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("LoginActivity", "response.body():" + response.body());

                startActivity(new Intent(LoginActivity.this,MainActivity.class));


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

//        try {
//            Response<User> user = userCall.execute();
//            return user.body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    class MyHttpTask extends AsyncTask<String,Integer,User>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected User doInBackground(String... strings) {

            String url = strings[0];
            String username = strings[1];
            String password = strings[2];
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(url).build();

            NewsApi newsApi = retrofit.create(NewsApi.class);

            Call<User> userCall = newsApi.login(username,password);

            try {
                Response<User> user = userCall.execute();
                return user.body();
            } catch (IOException e) {
                e.printStackTrace();
            }


//            String url = strings[0];
//
//
//            HttpClient httpClient = new DefaultHttpClient();
//
//            HttpGet httpGet = new HttpGet(url);
//
//            HttpResponse httpResponse = null;
//
//            String result = "请求错误";
//
//            try {
//                httpResponse = httpClient.execute(httpGet);
//                if(httpResponse.getStatusLine().getStatusCode() == 200){
//                    result = EntityUtils.toString(httpResponse.getEntity()) ;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


            return null;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);




//            Gson gson = new Gson();
//            User user = gson.fromJson(s,User.class);


            Log.d("MyHttpTask", "user:" + user);


//            try {
//                User user = new User();
//                JSONObject jsonObject = new JSONObject(s);
//                user.setStatus_code(jsonObject.getInt("status_code"));
//                user.setStatus_msg(jsonObject.getString("status_msg"));
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }


        }
    }

}
