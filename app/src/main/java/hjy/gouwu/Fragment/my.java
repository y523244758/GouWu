package hjy.gouwu.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import hjy.gouwu.R;
import hjy.gouwu.Url_data.Url_data;
import hjy.gouwu.UserAddress.Myaddress;

/**
 * Created by 胡靖宇 on 2017/9/27.
 */

public class my extends Fragment {

    private TextView tv_name,tv_address;
    private String uname;
    private String key;
    private Button bt;
    boolean bu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //加载布局
        View view=inflater.inflate(R.layout.my, container, false);

        bt=view.findViewById(R.id.my_logout);
        tv_name=view.findViewById(R.id.username_my);
        tv_address=view.findViewById(R.id.user_address);


        //获取值
        uname =Url_data.read.getString("usname","");
        key=Url_data.read.getString("key","");
        tv_name.setText(uname);




        //检查用户名
        Log.i("xxx00",uname+key+Url_data.read.getBoolean("YN",bu));
        System.out.println("00000"+uname);


        tv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),Myaddress.class));
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt.postInvalidate();

            }
        });


        return view;
    }
}
