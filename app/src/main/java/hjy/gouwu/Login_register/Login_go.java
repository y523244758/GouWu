package hjy.gouwu.Login_register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import hjy.gouwu.MainActivity;
import hjy.gouwu.R;
import hjy.gouwu.Url_data.Url_data;
import hjy.gouwu.bean.Login_bean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Login_go extends AppCompatActivity {

    TextView tv_login;
    EditText login_name,login_pwd;
    Button login_bt;
    Request.Builder request;
    String name,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_go);

        tv_login= (TextView) findViewById(R.id.login_register);
        login_bt= (Button) findViewById(R.id.login_bt);
        login_name= (EditText) findViewById(R.id.login_username);
        login_pwd= (EditText) findViewById(R.id.login_userpwd);

        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name=login_name.getText().toString();
                pwd=login_pwd.getText().toString();
                oKhttpPost();
                Login_go.this.finish();
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login_go.this.finish();
                startActivity(new Intent(Login_go.this,registre_go.class));
            }
        });
    }


    public void oKhttpPost(){
        final OkHttpClient mOkHttpClient = new OkHttpClient();
        // 创建表单
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        bodyBuilder.add("username",name);
        bodyBuilder.add("password",pwd);
        bodyBuilder.add("client","android");


        //创建请求体对象
        request=new Request.Builder();
        request.url(Url_data.My_Login);
        request.post(bodyBuilder.build());

        Call call = mOkHttpClient.newCall(request.build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String login_data= response.body().string();
                Gson gson=new Gson();
                final Login_bean b=gson.fromJson(login_data,Login_bean.class);
                final Login_bean.DatasBean list=b.getDatas();


                Url_data.editor.putString("usname",list.getUsername().toString()).commit();
                Url_data.editor.putString("key",list.getKey().toString()).commit();
                Url_data.editor.putBoolean("YN",true).commit();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(Login_go.this,list.getUsername().toString()+"登陆成功",Toast.LENGTH_LONG).show();
                        System.out.println(login_data);
                        Log.i("xxx1",Url_data.read.getString("usname","")+Url_data.read.getString("key",""));

                    }
                });
            }
        });

    }
}
