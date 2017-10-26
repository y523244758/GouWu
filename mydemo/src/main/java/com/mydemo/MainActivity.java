package com.mydemo;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mydemo.utils.GsonObjectCallback;
import com.mydemo.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;



public class MainActivity extends AppCompatActivity {

    RecyclerView rc;
    SwipeRefreshLayout swf;
    LinearLayoutManager layoutManager;
    MyNewAdapter adapter;
    String url="http://news-at.zhihu.com/api/4/news/latest";
    String urlMore="http://news-at.zhihu.com/api/4/news/before/20131119";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rc= (RecyclerView) findViewById(R.id.rc);
        swf= (SwipeRefreshLayout) findViewById(R.id.swf);

        layoutManager = new LinearLayoutManager(MainActivity.this);
        rc.setLayoutManager(layoutManager);

        getServerData();

        swf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()  {
            @Override
            public void onRefresh() {
                OkHttp3Utils.doGet(urlMore, new GsonObjectCallback<NewsBean>() {
                    @Override
                    public void onUi(NewsBean newsBean) {
                        List<NewsBean.StoriesBean> stories = newsBean.getStories();
                        adapter.refreshMore(stories);
                    }

                    @Override
                    public void onFailed(Call call, IOException e) {
                    }

                });

                if(swf.isRefreshing()){
                    //隐藏掉下拉刷新进度条
                    swf.setRefreshing(false);
                }
            }
        });

//给RecycleView天机滚动监听
        rc.addOnScrollListener(new RecyclerView.OnScrollListener(){
            private int lastPOsition;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(lastPOsition+1==adapter.getItemCount()&&newState==RecyclerView.SCROLL_STATE_IDLE){
                    OkHttp3Utils.doGet(urlMore, new GsonObjectCallback<NewsBean>() {
                        @Override
                        public void onUi(NewsBean newsBean) {
                            List<NewsBean.StoriesBean> stories = newsBean.getStories();
                            adapter.loadMore(stories);
                        }
                        @Override
                        public void onFailed(Call call, IOException e) {
                        }
                    });
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastPOsition = layoutManager.findLastVisibleItemPosition();
            }
        });

    }

    private void getServerData() {
        OkHttp3Utils.doGet(url, new GsonObjectCallback<NewsBean>() {
            @Override
            public void onUi(NewsBean newsBean) {

                if(adapter==null){
                    List<NewsBean.TopStoriesBean> top_stories = newsBean.getTop_stories();
                    adapter = new MyNewAdapter(MainActivity.this,newsBean,top_stories);
                    rc.setAdapter(adapter);
                }else{
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }

        });
    }
}
