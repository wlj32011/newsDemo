package cn.boc.newsdemo.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import static android.hardware.SensorManager.SENSOR_DELAY_GAME;

/**
 * Created by wanglj on 16/7/21.
 */

public class StepService extends Service{


    /**
     * 加速度传感器的采样频率：SENSOR_DELAY_GAME代表每20ms采样一次数据<br>
     * <li>SENSOR_DELAY_GAME：计步器准确度高，但是比较耗电<br>
     * <li>SENSOR_DELAY_UI：计步器准确度一般，一般耗电<br>
     * <li>SENSOR_DELAY_NORMAL：计步器较不准确，不耗电<br>
     * 可以自行根据用户使用习惯，动态调整传感器的采样频率，以达到耗电和准确性的平衡
     * 频率分析：
     *SENSOR_DELAY_FASTEST 在游戏想特别快的反应速度的时候使用 缺省值0
     *SENSOR_DELAY_GAME 游戏用 缺省值1
     *SENSOR_DELAY_UI 用户接口用 缺省值2   计步器返回步数很慢
     *SENSOR_DELAY_NORMAL 取得倾斜度的时候使用（缺省）3 计步器返回数据相当慢
     */
    private static final  int rate = SensorManager.SENSOR_DELAY_GAME;


    private SensorManager sensorManager;

    private Sensor sensor;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        List<Sensor> sensorList =  sensorManager.getSensorList(Sensor.TYPE_STEP_COUNTER);



        if(sensorList != null && sensorList.size() > 0){
            sensor = sensorList.get(0);
        }

        sensorManager.registerListener(new MySensorListener(this),sensor,rate);


        return START_STICKY;
//        return super.onStartCommand(intent, flags, startId);
    }





    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    class MySensorListener implements SensorEventListener{



        private Context context;

        public MySensorListener(Context context){
            this.context = context;
        }



        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            for(float f:sensorEvent.values){
                Log.e("values","values="+f);
            }

            Intent intent = new Intent("step_counter");

            intent.putExtra("step",sensorEvent.values[0]);

            context.sendBroadcast(intent);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }


    }


}
