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
import java.util.List;

import cn.boc.newsdemo.R;
import cn.boc.newsdemo.adapter.RecyclerViewAdapter;

/**
 * Created by wanglj on 16/7/20.
 */

public class NewsFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recyclerview,container,false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(new RecyclerViewAdapter(getActivity(),initData()));

        return view;

    }

    private List<String> initData() {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("testtesttesttesttesttesttesttest");
        }
        return list;

    }
}
