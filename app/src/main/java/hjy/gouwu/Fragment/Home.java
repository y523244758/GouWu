package hjy.gouwu.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.acker.simplezxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import hjy.gouwu.Adapter.MyAdapter;
import hjy.gouwu.MainActivity;
import hjy.gouwu.R;
import hjy.gouwu.search_home.search_homeActivity;


/**
 * Created by 胡靖宇 on 2017/9/27.
 */

public class Home extends Fragment{

    List<String> list_title =new ArrayList<String>();
    List<Integer> imgLsit=new ArrayList<Integer>();
    ImageView img;
    RecyclerView rv;
    LinearLayout ll;
    MyAdapter md;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //加载布局
        View view=  View.inflate(getContext(),R.layout.myhome,null);
        rv=view.findViewById(R.id.fv);


        list_title.add("天猫");
        list_title.add("聚划算");
        list_title.add("天猫国际");
        list_title.add("外卖");
        list_title.add("天猫超市");
        list_title.add("充值中心");
        list_title.add("飞猪旅行");
        list_title.add("领金币");
        list_title.add("拍卖");
        list_title.add("分类");

        imgLsit.add(R.drawable.tianmao);
        imgLsit.add(R.drawable.juhuasuan);
        imgLsit.add(R.drawable.juhuasuan);
        imgLsit.add(R.drawable.juhuasuan);
        imgLsit.add(R.drawable.juhuasuan);
        imgLsit.add(R.drawable.juhuasuan);
        imgLsit.add(R.drawable.juhuasuan);
        imgLsit.add(R.drawable.juhuasuan);
        imgLsit.add(R.drawable.juhuasuan);
        imgLsit.add(R.drawable.juhuasuan);


        GridLayoutManager manager = new GridLayoutManager(getContext(),5);
        rv.setLayoutManager(manager);

        md=new MyAdapter(getContext(),list_title,imgLsit);
        rv.setAdapter(md);






        ll=view.findViewById(R.id.ss);
        img=view.findViewById(R.id.saosao);

        //二维码
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class), CaptureActivity.REQ_CODE);
            }
        });

        //搜索点击跳转
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),search_homeActivity.class));
            }
        });



        return view;
    }






}
