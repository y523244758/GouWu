package hjy.gouwu.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import hjy.gouwu.Adapter.Three_level.One_levelAdapter;
import hjy.gouwu.Adapter.Three_level.Three_leveAdapter;
import hjy.gouwu.Adapter.Three_level.Two_leveAdapter;
import hjy.gouwu.R;
import hjy.gouwu.Url_data.Url_data;
import hjy.gouwu.bean.Three_levebean.Oneleve;
import hjy.gouwu.bean.Three_levebean.Two_levebean;
import hjy.gouwu.utils.GsonObjectCallback;
import hjy.gouwu.utils.OkHttp3Utils;
import okhttp3.Call;

/**
 * Created by 胡靖宇 on 2017/9/27.
 */

public class message extends Fragment{

    RecyclerView recyclerView,re2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //加载布局
        View view=inflater.inflate(R.layout.message, container, false);
        re2=view.findViewById(R.id.message_two);

        recyclerView=view.findViewById(R.id.message_one);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        re2.setLayoutManager(new LinearLayoutManager(getContext()));




        okhttp();

        return view;
    }

    private void okhttp() {
        //一级
        OkHttp3Utils.doGet(Url_data.My_oneLeve, new GsonObjectCallback<Oneleve>() {
            @Override
            public void onUi(Oneleve oneleve) {
             final List<Oneleve.DatasBean.ClassListBean> one=   oneleve.getDatas().getClass_list();

                final One_levelAdapter oa=new One_levelAdapter(getContext(),one);
                recyclerView.setAdapter(oa);

                //第一级的点击事件
                oa.setOnItemClickListener(new One_levelAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //二级
                       int i=Integer.parseInt(one.get(position).getGc_id());
                        OkHttp3Utils.doGet(Url_data.My_oneLeve_two + i, new GsonObjectCallback<Two_levebean>() {
                            @Override
                            public void onUi(Two_levebean two_levebean) {
                            final List<Two_levebean.DatasBean.ClassListBean> two=two_levebean.getDatas().getClass_list();

                                Two_leveAdapter tl=new Two_leveAdapter(getContext(),two);
                                re2.setAdapter(tl);
                                //二级点击事件


                            }

                            @Override
                            public void onFailed(Call call, IOException e) {

                            }
                        });
                    }
                });

            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
