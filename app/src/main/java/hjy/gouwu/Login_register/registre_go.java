package hjy.gouwu.Login_register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import hjy.gouwu.MainActivity;
import hjy.gouwu.R;
import hjy.gouwu.Url_data.Url_data;
import hjy.gouwu.bean.Login_bean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class registre_go extends AppCompatActivity {

    EditText u_name,u_pwd,u_pwdtwo,u_email;
    Button bt_go;
    Request.Builder request;
    String name;
    String pwd;
    String pwdtwo;
    String email;
    //String url="http://169.254.110.146/mobile/index.php?act=login&op=register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre_go);

        u_name= (EditText) findViewById(R.id.user_name);
        u_pwd= (EditText) findViewById(R.id.user_pwd);
        u_pwdtwo= (EditText) findViewById(R.id.user_pwdtwo);
        u_email= (EditText) findViewById(R.id.user_email);
        bt_go= (Button) findViewById(R.id.bt_go);





        bt_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name=u_name.getText().toString();
                pwd=u_pwd.getText().toString();
                pwdtwo=u_pwdtwo.getText().toString();
                email=u_email.getText().toString();

                okhttp();

            }
        });
    }

    public void okhttp(){
        final OkHttpClient mOkHttpClient = new OkHttpClient();
        // 创建表单
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        bodyBuilder.add("username",name);
        bodyBuilder.add("password",pwd);
        bodyBuilder.add("password_confirm",pwdtwo);
        bodyBuilder.add("email",email);
        bodyBuilder.add("client","android");

        //创建请求体对象
        request=new Request.Builder();
        request.url(Url_data.My_registre);
        request.post(bodyBuilder.build());

        Call call = mOkHttpClient.newCall(request.build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String logindata=response.body().string();
                Gson gson=new Gson();
                final Login_bean b=gson.fromJson(logindata,Login_bean.class);
                final Login_bean.DatasBean list=b.getDatas();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(b.getCode()==400){
                            Toast.makeText(registre_go.this,"注册失败",Toast.LENGTH_SHORT).show();
                        }else {



                            Url_data.editor.putString("usname",list.getUsername().toString()).commit();
                            Url_data.editor.putString("key",list.getKey().toString()).commit();

                            //结束页面
                            registre_go.this.finish();
                        }

                        System.out.println(logindata);



                    }
                });
            }
        });
    }
}
