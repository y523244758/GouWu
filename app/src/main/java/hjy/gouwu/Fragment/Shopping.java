package hjy.gouwu.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hjy.gouwu.R;

/**
 * Created by 胡靖宇 on 2017/9/27.
 */

public class Shopping extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //加载布局
        View view=inflater.inflate(R.layout.shopping, container, false);

        return view;
    }
}
