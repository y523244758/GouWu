package hjy.gouwu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

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
                Fragment fragment;

                switch (position){
                    case 0:

                        break;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });

    }
}
