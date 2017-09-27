package hjy.gouwu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import hjy.gouwu.Fragment.Home;
import hjy.gouwu.Fragment.Shopping;
import hjy.gouwu.Fragment.WeiTao;
import hjy.gouwu.Fragment.message;
import hjy.gouwu.Fragment.my;

import static hjy.gouwu.R.id.home;

public class MainActivity extends FragmentActivity {

    ViewPager vp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        vp= (ViewPager) findViewById(R.id.vp);

        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;

                switch (position){
                    case 0:
                        fragment=new Home();
                        break;
                    case 1:
                        fragment=new WeiTao();
                        break;
                    case 2:
                        fragment=new message();
                        break;
                    case 3:
                        fragment=new Shopping();
                        break;
                    case 4:
                        fragment=new my();
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
                break;

        }

    }
}
