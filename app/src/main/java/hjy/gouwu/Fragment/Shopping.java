package hjy.gouwu.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hjy.gouwu.Login_register.Login_go;
import hjy.gouwu.R;
import hjy.gouwu.Url_data.Url_data;
import hjy.gouwu.bean.goodList.goodlistbean;
import hjy.gouwu.bean.goodList.goodsdele;
import hjy.gouwu.utils.GsonObjectCallback;
import hjy.gouwu.utils.OkHttp3Utils;
import okhttp3.Call;

/**
 * Created by 胡靖宇 on 2017/9/27.
 */

public class Shopping extends Fragment {
    private Map<String,String> mapList=new HashMap<String, String>();
    private TextView goodssize,tv_shopcart_totalprice,tv_shopcart_totalnum,tv_shopcart_submit,null_goods;
    CheckBox tv_shopcart_addselect;
    List<hjy.gouwu.bean.goodList.goodlistbean.DatasBean.CartListBean> gblist;
    private ExpandableListView expandableListView;
    ExpandableAdapter exd;
    Map<String,String> mapdele=new HashMap<String, String>();
    LinearLayout ll_pay;

    //订单集合
    List<String> ding=new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //加载布局
        View view=inflater.inflate(R.layout.shopping, container, false);
        expandableListView=view.findViewById(R.id.els);
        //全选
        tv_shopcart_addselect=view.findViewById(R.id.tv_shopcart_addselect);
        //总价
        tv_shopcart_totalprice=view.findViewById(R.id.tv_shopcart_totalprice);
        //总商品量
        tv_shopcart_totalnum=view.findViewById(R.id.tv_shopcart_totalnum);
        //结算按钮
        tv_shopcart_submit=view.findViewById(R.id.tv_shopcart_submit);
        tv_shopcart_addselect=view.findViewById(R.id.tv_shopcart_addselect);

        tv_shopcart_submit=view.findViewById(R.id.tv_shopcart_submit);

        null_goods=view.findViewById(R.id.null_goods);
        ll_pay=view.findViewById(R.id.ll_pay);
        goodssize=view.findViewById(R.id.shopping_size);
        //判断登陆
       if(Url_data.read.getString("key","").toString().equals("")){
            Toast.makeText(getActivity(),"请登陆",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), Login_go.class));
       }
       //添加到请求
        mapList.put("key",Url_data.read.getString("key",""));

        //设置一个空的指示器
        expandableListView.setGroupIndicator(null);

        tv_shopcart_addselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {

                    List<goodlistbean.DatasBean.CartListBean> data = gblist;

                    for (int i = 0; i < data.size(); i++) {
                        Log.i("xxx", data.get(i).isAllCheck() + "");
                        goodlistbean.DatasBean.CartListBean groupDatas = gblist.get(i);
                        groupDatas.setAllCheck(true);
                        List<goodlistbean.DatasBean.CartListBean.GoodsBean> datas = gblist.get(i).getGoods();
                        for (int j = 0; j < datas.size(); j++) {
                            Log.i("xxx", datas.get(j).isItemCheck() + "");
                            List<goodlistbean.DatasBean.CartListBean.GoodsBean> childDatas = gblist.get(i).getGoods();
                            for (goodlistbean.DatasBean.CartListBean.GoodsBean childData : childDatas) {
                                childData.setItemCheck(true);
                            }
                        }
                    }
                    //刷新界面
                    notifyCheckAdapter();
                } else {

                    List<goodlistbean.DatasBean.CartListBean> data = gblist;
                    for (int i = 0; i < data.size(); i++) {
                        Log.i("xxx", data.get(i).isAllCheck() + "");
                        goodlistbean.DatasBean.CartListBean groupDatas = gblist.get(i);
                        groupDatas.setAllCheck(false);
                        List<goodlistbean.DatasBean.CartListBean.GoodsBean> datas = gblist.get(i).getGoods();
                        for (int j = 0; j < datas.size(); j++) {
                            Log.i("xxx", datas.get(j).isItemCheck() + "");
                            List<goodlistbean.DatasBean.CartListBean.GoodsBean> childDatas = gblist.get(i).getGoods();
                            for (goodlistbean.DatasBean.CartListBean.GoodsBean childData : childDatas) {
                                childData.setItemCheck(false);
                            }
                        }
                    }
                    //刷新界面
                    notifyCheckAdapter();
                }
            }
        });

        tv_shopcart_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        okhttp();


        return view;
    }



    private void okhttp() {
        OkHttp3Utils.doPost(Url_data.MygoodsShoopingList, mapList, new GsonObjectCallback<goodlistbean>() {
            @Override
            public void onUi(goodlistbean goodlistbean) {
             gblist=   goodlistbean.getDatas().getCart_list();
                //几件商品
            goodssize.setText(goodlistbean.getDatas().getCart_count()+"");
            //适配器

                String storename =gblist.get(0).getStore_name().toString();

                exd=new ExpandableAdapter(getContext(),storename,gblist);

                expandableListView.setAdapter(exd);
                expandableListView.expandGroup(0);

            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });


    }
    public class ExpandableAdapter extends BaseExpandableListAdapter {
        Context context;
        String storename;
        List<goodlistbean.DatasBean.CartListBean> gblist;



        public ExpandableAdapter(Context context, String storename, List<goodlistbean.DatasBean.CartListBean> gblist) {
            this.context=context;
            this.storename=storename;
            this.gblist=gblist;
        }

        //删除条目的方法
        public void removeChildItem(int groupPosition,int childPosition){
            this.gblist.get(groupPosition).getGoods().remove(childPosition);
            notifyDataSetChanged();
        }
        //删除条目的方法
        public void removeGroupItem(int groupPosition){
            this.gblist.remove(groupPosition);
            notifyDataSetChanged();
        }

        @Override
        public int getGroupCount() {
            return gblist.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return gblist.get(i).getGoods().size();
        }

        @Override
        public Object getGroup(int i) {
            return gblist.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return gblist.get(i).getGoods().get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {


                view=View.inflate(context, R.layout.item_shopping, null);

                CheckBox iv_item_shopcart_shopselect=view.findViewById(R.id.iv_item_shopcart_shopselect);
                TextView tv_item_shopcart_shopname=(TextView) view.findViewById(R.id.tv_item_shopcart_shopname);




            tv_item_shopcart_shopname.setText(storename);

            if (gblist.get(i).isAllCheck()) {
                iv_item_shopcart_shopselect.setChecked(true);
            } else {
                iv_item_shopcart_shopselect.setChecked(false);
            }
            //一级监听
            iv_item_shopcart_shopselect.setOnClickListener(new OnGroupClickListener(i, iv_item_shopcart_shopselect));
            return view;
        }

        @Override
        public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {



                view=View.inflate(context, R.layout.item_shoppinggoods, null);

                TextView tv_item_shopcart_clothname=(TextView) view.findViewById(R.id.tv_item_shopcart_clothname);
                TextView tv_item_shopcart_cloth_price=view.findViewById(R.id.tv_item_shopcart_cloth_price);
                TextView tv_item_shopcart_cloth_num=view.findViewById(R.id.tv_item_shopcart_cloth_num);
                ImageView iv_item_shopcart_cloth_pic=(ImageView) view.findViewById(R.id.iv_item_shopcart_cloth_pic);
                ImageView iv_item_shopcart_cloth_delete=view.findViewById(R.id.iv_item_shopcart_cloth_delete);

                CheckBox tv_item_shopcart_clothselect=view.findViewById(R.id.tv_item_shopcart_clothselect);

            tv_item_shopcart_clothname.setText(gblist.get(i).getGoods().get(i1).getGoods_name().toString());
            tv_item_shopcart_cloth_price.setText("¥"+gblist.get(i).getGoods().get(i1).getGoods_price().toString());
            tv_item_shopcart_cloth_num.setText("个数："+gblist.get(i).getGoods().get(i1).getGoods_num().toString());
            Picasso.with(iv_item_shopcart_cloth_pic.getContext()).load(gblist.get(i).getGoods().get(i1).getGoods_image_url()).into(iv_item_shopcart_cloth_pic);

            mapdele.put("key",Url_data.read.getString("key",""));
            mapdele.put("cart_id",gblist.get(i).getGoods().get(i1).getCart_id().toString());
            iv_item_shopcart_cloth_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {



                    OkHttp3Utils.doPost(Url_data.MygoodsShoopingdele, mapdele, new GsonObjectCallback<goodsdele>() {
                        @Override
                        public void onUi(goodsdele goodsdele) {

                            if(goodsdele.getCode()==200){
                                removeChildItem(i,i1);
                                //从新获得数量
                                goodssize.setText(gblist.get(i).getGoods().size()+"");

                                if(gblist.get(i).getGoods().size()==0){
                                    removeGroupItem(i);

                                }
                                //判断是否有值
                                if (gblist.size()==0) {
                                    //没有值将列表和总价隐藏，显示提示语句
                                    ll_pay.setVisibility(View.GONE);
                                    null_goods.setVisibility(View.VISIBLE);
                                    return;
                                }
                            }

                        }

                        @Override
                        public void onFailed(Call call, IOException e) {

                        }
                    });

                }
            });

            if (gblist.get(i).getGoods().get(i1).isItemCheck()) {
                tv_item_shopcart_clothselect.setChecked(true);
            } else {
                tv_item_shopcart_clothselect.setChecked(false);
            }
            tv_item_shopcart_clothselect.setOnClickListener(new OnChildCheckListener(i, i1, tv_item_shopcart_clothselect));

            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }




    }
    //一级监听
    private class OnGroupClickListener implements View.OnClickListener {
        int groupPosition;
        CheckBox iv_item_shopcart_shopselect;

        public OnGroupClickListener(int groupPosition, CheckBox iv_item_shopcart_shopselect) {
            this.iv_item_shopcart_shopselect = iv_item_shopcart_shopselect;
            this.groupPosition = groupPosition;
        }


        @Override
        public void onClick(View v) {
            if (((CheckBox) v).isChecked()) {
                //一级全选
                setCheck(true);

            } else {
                //取消全选
                setCheck(false);
                iv_item_shopcart_shopselect.setChecked(false);
            }
            notifyCheckAdapter();
        }

        public void setCheck(boolean checkFlag) {

            goodlistbean.DatasBean.CartListBean groupDatas = gblist.get(groupPosition);
            List<goodlistbean.DatasBean.CartListBean> data= gblist;

            //一级状态
            groupDatas.setAllCheck(checkFlag);

            //全选状态判断
            int num = 0;
            for (int i = 0; i < data.size(); i++) {
                boolean allCheck = data.get(i).isAllCheck();
                if (!allCheck) {
                    num++;
                }
            }
            if (num == 0) {
                iv_item_shopcart_shopselect.setChecked(true);
            } else {
                iv_item_shopcart_shopselect.setChecked(false);
            }

            //二级状态
            List<goodlistbean.DatasBean.CartListBean.GoodsBean> childDatas = groupDatas.getGoods();
            for (goodlistbean.DatasBean.CartListBean.GoodsBean childData : childDatas) {
                //二级状态
                childData.setItemCheck(checkFlag);

            }

        }
    }


    //二级监听
    private class OnChildCheckListener implements View.OnClickListener {
        int groupPosition;
        int childPosition;
        CheckBox tv_item_shopcart_clothselect;

        public OnChildCheckListener(int groupPosition, int childPosition, CheckBox tv_item_shopcart_clothselect) {
            this.tv_item_shopcart_clothselect = tv_item_shopcart_clothselect;
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
        }

        @Override
        public void onClick(View v) {
            if (((CheckBox) v).isChecked()) {
                //子选中
                gblist.get(groupPosition).getGoods().get(childPosition).setItemCheck(true);
                //获得所需要的cartid，num
                ding.add(gblist.get(groupPosition).getGoods().get(childPosition).getCart_id().toString()+gblist.get(groupPosition).getGoods().get(childPosition).getGoods_num().toString());



            } else {
                //子未选中
                gblist.get(groupPosition).getGoods().get(childPosition).setItemCheck(false);

            }
            //二级联动一级状态
            setParentCheckFlag();

            //检测状态 二级联动全选
            int num = 0;
            for (int i = 0; i < gblist.size(); i++) {
                boolean allCheck = gblist.get(i).isAllCheck();
                if (!allCheck) {
                    num++;
                }

            }
            if (num == 0) {
                tv_item_shopcart_clothselect.setChecked(true);
            } else {
                tv_item_shopcart_clothselect.setChecked(false);
            }
        }

        //二级联动一级状态
        private void setParentCheckFlag() {
            goodlistbean.DatasBean.CartListBean dataInfo = gblist.get(groupPosition);
            List<goodlistbean.DatasBean.CartListBean.GoodsBean> datasInfos= gblist.get(groupPosition).getGoods();

            for (int i = 0; i < datasInfos.size(); i++) {
                if (!datasInfos.get(i).isItemCheck()) {
                    //子未选中 父取消选中
                    dataInfo.setAllCheck(false);
                    notifyCheckAdapter();
                    return;
                }
                if (i == datasInfos.size() - 1) {
                    //子选中 父选中
                    dataInfo.setAllCheck(true);
                    notifyCheckAdapter();

                    return;
                }


            }
//            没出现全选或者取消全选的时候执行的
            sum(datasInfos);
        }

    }
    //统计数量和价格
    private void sum(List<goodlistbean.DatasBean.CartListBean.GoodsBean> datasInfos) {
        int num = 0;
        int price = 0;

        List<goodlistbean.DatasBean.CartListBean> data = gblist;
        for (goodlistbean.DatasBean.CartListBean parentData : data) {
            for (goodlistbean.DatasBean.CartListBean.GoodsBean child :datasInfos) {
                if (child.isItemCheck()) {
                    num++;

                    double d=Double.valueOf(child.getGoods_price().toString()).doubleValue();
                    int i = (int)d;
                    price +=i;
                }
            }
        }
        tv_shopcart_totalnum.setText("共"+num+"件商品");
        tv_shopcart_totalprice.setText("总价:"+price);
    }

    //刷新适配器界面
    private void notifyCheckAdapter() {
        sum(gblist.get(0).getGoods());
        expandableListView.setAdapter(exd);
        int count = expandableListView.getCount();
        for (int i = 0; i < count; i++) {
            expandableListView.expandGroup(i);
        }
    }

}
