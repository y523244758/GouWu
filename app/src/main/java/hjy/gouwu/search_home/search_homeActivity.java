package hjy.gouwu.search_home;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import hjy.gouwu.Adapter.Mysearch_Adapter;
import hjy.gouwu.MainActivity;
import hjy.gouwu.R;
import hjy.gouwu.RecyclerView_ItemDecoration.Rc_ItemDecoration;
import hjy.gouwu.bean.search_bean;
import hjy.gouwu.search_home.goods_detail.to_goods_detail;
import hjy.gouwu.utils.GsonArrayCallback;
import hjy.gouwu.utils.GsonObjectCallback;
import hjy.gouwu.utils.OkHttp3Utils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class search_homeActivity extends AppCompatActivity {
    int gid;
    EditText editname;
    RecyclerView recyclerView;
    String url="http://169.254.110.146/mobile/index.php?act=goods&op=goods_list&page=100&keyword=";
    Button button_search,bt_chenge;
    ImageView break_home;
    private Request request;
    Mysearch_Adapter mda;
    int chenge=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_home);

        button_search= (Button) findViewById(R.id.search_go);
        editname= (EditText) findViewById(R.id.search_bar);
        break_home= (ImageView) findViewById(R.id.search_bar_break);
        recyclerView= (RecyclerView) findViewById(R.id.rc_goods);
        //添加分割线
        recyclerView.addItemDecoration(new Rc_ItemDecoration(search_homeActivity.this));
        //布局管理器
        LinearLayoutManager llm=new LinearLayoutManager(search_homeActivity.this);
        recyclerView.setLayoutManager(llm);
        bt_chenge= (Button) findViewById(R.id.search_chenge);
        break_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //返回
                startActivity(new Intent(search_homeActivity.this,MainActivity.class));
            }
        });
        bt_chenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(chenge==0){
                    //添加分割线
                    recyclerView.addItemDecoration(new Rc_ItemDecoration(search_homeActivity.this));
                    //布局管理器
                    LinearLayoutManager llm=new LinearLayoutManager(search_homeActivity.this);
                    recyclerView.setLayoutManager(llm);
                    chenge=1;
                }else if(chenge==1){
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(search_homeActivity.this, 2);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    chenge=0;

                }
            }
        });




        //获得商品名称
        final String name= editname.getText().toString();


        final OkHttpClient mOkHttpClient = new OkHttpClient();
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Okhttp获取数据
                 request=new Request.Builder()
                        .url(url+name)
                        .build();

                Call call = mOkHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String goods =  response.body().string();
                        Gson gson=new Gson();
                        search_bean b=  gson.fromJson(goods,search_bean.class);
                        final List<search_bean.DatasBean.GoodsListBean> list_goods=b.getDatas().getGoods_list();
                        mda=new Mysearch_Adapter(search_homeActivity.this,list_goods);


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                               recyclerView.setAdapter(mda);
                                //点击事件
                                mda.setOnItemClickListener(new Mysearch_Adapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                       Intent inte= new Intent(search_homeActivity.this,to_goods_detail.class);
                                      gid =Integer.parseInt(list_goods.get(position).getGoods_id());
                                        inte.putExtra("id",gid);
                                        startActivity(inte);
                                       // Toast.makeText(search_homeActivity.this,list_goods.get(position).getGoods_id(),Toast.LENGTH_SHORT).show();
                                    }
                                });

                               // System.out.println(list_goods.size());
                            }
                        });

                    }
                });
            }
            });


    }
}