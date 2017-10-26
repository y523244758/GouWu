package com.mydemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 胡靖宇 on 2017/10/25.
 */

public class MyNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<NewsBean.TopStoriesBean> top_stories;
    Context context;
    NewsBean newsBean;
    List<NewsBean.StoriesBean> stories;

    public MyNewAdapter(Context context, NewsBean newsBean, List<NewsBean.TopStoriesBean> top_stories) {
        this.context = context;
        this.newsBean = newsBean;
        this.top_stories=top_stories;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==0){
            View view = LayoutInflater.from(context).inflate(R.layout.footer_item, parent, false);
            return new VHFooter(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            return new  Content(view);
        }



    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if(type==0){
            return;
        }
        if(type==1) {
            Content content=new Content(holder.itemView);

            stories = newsBean.getStories();
            content.tv_newTitle.setText(stories.get(position).getTitle());
            content.tv_newTime.setText(stories.get(position).getGa_prefix());
        }

    }

    @Override
    public int getItemCount() {
        return newsBean.getStories().size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==getItemCount()-1){
            return 0;
        }else
        {
            return 1;
        }
    }

    private class Content extends RecyclerView.ViewHolder{

        private final ImageView iv_new;
        private final TextView tv_newTitle;
        private final TextView tv_newTime;

        public Content(View itemView) {
            super(itemView);
            iv_new = (ImageView) itemView.findViewById(R.id.iv_new);
            tv_newTitle = (TextView) itemView.findViewById(R.id.tv_newTitle);
            tv_newTime = (TextView) itemView.findViewById(R.id.tv_NewTime);
        }
    }

    private class VHFooter extends RecyclerView.ViewHolder{

        public VHFooter(View itemView) {
            super(itemView);
        }
    }

    public void loadMore(List<NewsBean.StoriesBean> s ){
        for (NewsBean.StoriesBean str : s){
            stories.add(str);
        }
        //更新界面
        notifyDataSetChanged();

    }

    public void refreshMore(List<NewsBean.StoriesBean> s){
        for (NewsBean.StoriesBean str : s){
            stories.add(0,str);
        }
        //更新界面
        notifyDataSetChanged();
    }
}
