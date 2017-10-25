package hjy.gouwu.Adapter.null_shopp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import hjy.gouwu.R;
import hjy.gouwu.bean.goodList.goodlistbean;

/**
 * Created by 胡靖宇 on 2017/10/18.
 */

public class MyshoppAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{


    Context context;
    List<goodlistbean.DatasBean.CartListBean> gblist;
    boolean onechek=false;

    private OnimgClickListener mOnimgClickListener = null;
    public MyshoppAdapter(Context context, List<goodlistbean.DatasBean.CartListBean> gblist) {
        this.context=context;
        this.gblist=gblist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_shopping,parent,false);
        Myshopping mp=new Myshopping(view);
        mp.iv_item_shopcart_shopselect.setOnClickListener(this);
        return mp;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final Myshopping msp=new Myshopping(holder.itemView);

        msp.tv_item_shopcart_shopname.setText(gblist.get(0).getStore_name());




//        if(position==0){
//            msp.llShopCartHeader.setVisibility(View.VISIBLE);
//        }else {
//            msp.llShopCartHeader.setVisibility(View.GONE);
//        }

        //传递数据tag
        msp.iv_item_shopcart_shopselect.setTag(position);

    }

    //点击事件
    public static interface OnimgClickListener {
        void onimgClick(View view , int position);

    }

    @Override
    public int getItemCount() {
        return gblist.size();
    }

    @Override
    public void onClick(View view) {


        if (mOnimgClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnimgClickListener.onimgClick(view,(int)view.getTag());
        }
    }

    //最后暴露给外面的调用者，定义一个设置Listener的方法
    public void setOnimgClickListener(OnimgClickListener listener) {
        this.mOnimgClickListener = listener;
    }

    public class Myshopping extends RecyclerView.ViewHolder{

        ImageView iv_item_shopcart_shopselect;
        TextView tv_item_shopcart_shopname;

        //名字，价格，数量，改变数量
        //TextView tv_item_shopcart_clothname,tv_item_shopcart_cloth_price,tv_item_shopcart_cloth_num,et_item_shopcart_cloth_num;
        //ImageView iv_item_shopcart_cloth_pic;
        //选中
        //ImageView tv_item_shopcart_clothselect;

        LinearLayout llShopCartHeader;

        public Myshopping(View itemView) {
            super(itemView);


            iv_item_shopcart_shopselect=itemView.findViewById(R.id.iv_item_shopcart_shopselect);
            tv_item_shopcart_shopname=itemView.findViewById(R.id.tv_item_shopcart_shopname);

//            tv_item_shopcart_clothname=itemView.findViewById(R.id.tv_item_shopcart_clothname);
//            tv_item_shopcart_cloth_price=itemView.findViewById(R.id.tv_item_shopcart_cloth_price);
//            tv_item_shopcart_cloth_num=itemView.findViewById(R.id.tv_item_shopcart_cloth_num);
//            et_item_shopcart_cloth_num=itemView.findViewById(R.id.et_item_shopcart_cloth_num);
//
//            iv_item_shopcart_cloth_pic=itemView.findViewById(R.id.iv_item_shopcart_cloth_pic);
//            //选中
//            tv_item_shopcart_clothselect=itemView.findViewById(R.id.tv_item_shopcart_clothselect);
            llShopCartHeader = itemView.findViewById(R.id.ll_shopcart_header);


            MyshoppgoodsAdapter ma=new MyshoppgoodsAdapter(context,gblist);


        }
    }
}
