package cn.boc.newsdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.boc.newsdemo.News;
import cn.boc.newsdemo.R;

import static android.R.id.list;


/**
 * Created by wanglj on 16/7/19.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{


    private final Context context;
    private  News newsList;

    public RecyclerViewAdapter(Context context, News newsList) {
        this.context = context;
        this.newsList = newsList;
    }


    public void refreshData(News newsList){
        this.newsList = newsList;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_recycler_view,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        News.DataEntity newsEntity = newsList.getData().get(position);
//        holder.textView.setText(text);
        holder.titleText.setText(newsEntity.getTitle());
        holder.contentText.setText(newsEntity.getContent());

        Glide.with(context).load(newsEntity.getImage_url())
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return newsList.getData().size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titleText,contentText;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.imageview);
            contentText = (TextView) itemView.findViewById(R.id.content);
        }
    }


}
