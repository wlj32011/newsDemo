package cn.boc.newsdemo;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by wanglj on 16/7/20.
 */

public class TimeCount extends CountDownTimer{

    Button getCodeButton;

    public TimeCount(Button getCodeButton,long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.getCodeButton = getCodeButton;
    }

    @Override
    public void onTick(long l) {
        getCodeButton.setEnabled(false);
        getCodeButton.setClickable(false);

        getCodeButton.setText(l/1000+"秒后重新获取");

    }

    @Override
    public void onFinish() {

        getCodeButton.setEnabled(true);
        getCodeButton.setClickable(true);

        getCodeButton.setText("获取短信验证码");
    }
}
