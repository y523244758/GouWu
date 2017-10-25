package hjy.gouwu.UserAddress;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import hjy.gouwu.Adapter.address_Adapter.Myaddress_Adapter;
import hjy.gouwu.R;
import hjy.gouwu.Url_data.Url_data;
import hjy.gouwu.bean.address_bean.address_All;
import hjy.gouwu.utils.GsonObjectCallback;
import hjy.gouwu.utils.OkHttp3Utils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Myaddress extends AppCompatActivity {

  private   ListView list;
   private TextView tv;
    private  Button bt;
    private  String key_me;
    private Request request;
      String addressurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaddress);
        tv= (TextView) findViewById(R.id.address_data);
        list= (ListView) findViewById(R.id.address_list);
        bt= (Button) findViewById(R.id.address_add);



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Myaddress.this,Add_address.class));
                Myaddress.this.finish();
            }
        });



        key_me= Url_data.read.getString("key","");

        addressurl=Url_data.Myaddress_Url+key_me;
        System.out.println("xxx="+addressurl);
        okhttp();

    }

    public void okhttp(){
        final OkHttpClient mOkHttpClient = new OkHttpClient();
        //Okhttp获取数据
                request=new Request.Builder()
                .url(addressurl)
                .build();

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String dd=response.body().string();
                Log.i("xxx",dd);
                Gson gson=new Gson();
                final address_All ar=gson.fromJson(dd,address_All.class);
                List<address_All.DatasBean.AddressListBean> listall=ar.getDatas().getAddress_list();
                final Myaddress_Adapter md=new Myaddress_Adapter(Myaddress.this,listall);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.setAdapter(md);
                        tv.setText(ar.getDatas().getAddress_list().size()+"个地址");
                    }
                });


            }
        });
    }


}
