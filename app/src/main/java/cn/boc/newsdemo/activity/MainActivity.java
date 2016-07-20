package cn.boc.newsdemo.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.boc.newsdemo.R;
import cn.boc.newsdemo.fragment.NewsFragment;
import cn.boc.newsdemo.fragment.StepFragment;

public class MainActivity extends AppCompatActivity {

    Button newsButton,stepButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        newsButton = (Button) findViewById(R.id.news);
        stepButton = (Button) findViewById(R.id.step);


        setNewsFragment();

        newsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNewsFragment();
            }
        });

        stepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setStepFragment();
            }
        });

    }


    private void setNewsFragment(){
        newsButton.setSelected(true);
        stepButton.setSelected(false);

        String tag = NewsFragment.class.getName();

        Fragment newsFragment = getSupportFragmentManager().findFragmentByTag(tag);

        if(newsFragment == null){
            newsFragment = new NewsFragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.contentPanel,newsFragment,tag).commit();
    }


    private void setStepFragment(){
        newsButton.setSelected(false);
        stepButton.setSelected(true);

        String tag = StepFragment.class.getName();

        Fragment stepFragment = getSupportFragmentManager().findFragmentByTag(tag);

        if(stepFragment == null){
            stepFragment = new StepFragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.contentPanel,stepFragment,tag).commit();
    }
}
