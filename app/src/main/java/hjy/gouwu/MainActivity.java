package hjy.gouwu;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import hjy.gouwu.Fragment.Home;
import hjy.gouwu.Fragment.Shopping;
import hjy.gouwu.Fragment.WeiTao;
import hjy.gouwu.Fragment.message;
import hjy.gouwu.Fragment.my;
import hjy.gouwu.Login_register.Login_go;
import hjy.gouwu.Url_data.Url_data;
import okhttp3.Request;

public class MainActivity extends FragmentActivity {

    ViewPager vp;
    private Request request;
    boolean yn2;
    int id;
    String url="http://169.254.110.146/mobile/index.php?act=login&client=android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Url_data.saveData(getApplicationContext());
        vp = (ViewPager) findViewById(R.id.vp);


            vp.setOffscreenPageLimit(5);
            vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    Fragment fragment = null;


                    switch (position) {
                        case 0:
                            fragment = new Home();
                            break;
                        case 1:
                            fragment = new WeiTao();
                            break;
                        case 2:
                            fragment = new message();
                            break;
                        case 3:
                            fragment = new Shopping();
                            break;
                        case 4:
                            fragment = new my();
                            break;

                    }
                    return fragment;
                }

                @Override
                public int getCount() {
                    return 5;
                }
            });

        }


    public void bt(View view){



        switch (view.getId()){
            case R.id.home_ll:
                vp.setCurrentItem(0);
                break;
            case R.id.weitao:
                vp.setCurrentItem(1);
                break;
            case R.id.message:
                vp.setCurrentItem(2);
                break;
            case R.id.shopping:
                vp.setCurrentItem(3);
                break;
            case R.id.my:
                vp.setCurrentItem(4);
                getData();

                break;

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
      yn2=  Url_data.read.getBoolean("YN",false);
    }

    public void getData() {



        System.out.println("xxxbuoolean"+yn2);
        if(yn2==false){
            Toast.makeText(MainActivity.this,"未登录",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, Login_go.class));
        }else {
            return;
        }
    }
}
