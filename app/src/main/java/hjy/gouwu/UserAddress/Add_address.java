package hjy.gouwu.UserAddress;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hjy.gouwu.R;
import hjy.gouwu.Url_data.Url_data;
import hjy.gouwu.bean.address_bean.address_add;
import hjy.gouwu.utils.GsonObjectCallback;
import hjy.gouwu.utils.OkHttp3Utils;
import okhttp3.Call;

public class Add_address extends AppCompatActivity {

    private EditText name,phone,city,area,address,area_info;
    private Button bt_add;
    private  String tname,tphone,tcity,tarea,taddress,tarea_info;
   // private  String all_url;
    private Map<String,String> mapurl=new HashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        bt_add= (Button) findViewById(R.id.add_address_bt);
        name= (EditText) findViewById(R.id.add_address_name);
        phone= (EditText) findViewById(R.id.add_address_phonenum);
        city= (EditText) findViewById(R.id.add_address_city);
        area= (EditText) findViewById(R.id.add_address_area);
        address= (EditText) findViewById(R.id.add_address_address);
        area_info= (EditText) findViewById(R.id.add_address_area_info);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tname=name.getText().toString();
                tphone=phone.getText().toString();
                tcity=city.getText().toString();
                tarea=area.getText().toString();
                taddress=address.getText().toString();
                tarea_info=area_info.getText().toString();


                String  mykey=Url_data.read.getString("key","");
                //all_url= Url_data.Add_Myaddress+mykey+tname+tphone+tcity+tarea+taddress+tarea_info;
                mapurl.put("true_name",tname);
                mapurl.put("mob_phone",tphone);
                mapurl.put("city_id",tcity);
                mapurl.put("area_id",tarea);
                mapurl.put("address",taddress);
                mapurl.put("area_info",tarea_info);
                mapurl.put("key",mykey);
                okhttp();
                startActivity(new Intent(Add_address.this,Myaddress.class));
                Add_address.this.finish();
            }
        });

    }


    public  void  okhttp(){
        OkHttp3Utils.doPost(Url_data.Add_Myaddress, mapurl, new GsonObjectCallback<address_add>() {
            @Override
            public void onUi(address_add address_add) {
            String cod= address_add.getDatas().toString();
                Toast.makeText(Add_address.this,"新建第"+cod+"个地址",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
