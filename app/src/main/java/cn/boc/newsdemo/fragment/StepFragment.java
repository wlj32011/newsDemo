package cn.boc.newsdemo.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.boc.newsdemo.R;
import cn.boc.newsdemo.service.StepService;
import cn.boc.newsdemo.view.CircleProgress;

import static cn.boc.newsdemo.R.id.step;

/**
 * Created by wanglj on 16/7/20.
 */

public class StepFragment extends Fragment{

    private CircleProgress circleProgress;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.step_fragment,container,false);

        circleProgress = (CircleProgress) view.findViewById(R.id.circleprogress);

        circleProgress.setValue(55);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().startService(new Intent(getActivity(), StepService.class));

//        getActivity().bindService()
    }


    class StepBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals("step_conter")){
                float step = intent.getFloatExtra("step",0);
                circleProgress.setValue((int) step);
            }

        }
    }


}
