package hjy.gouwu.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hjy.gouwu.R;
import hjy.gouwu.bean.search_bean;


/**
 * Created by 胡靖宇 on 2017/10/10.
 */

public class Mysearch_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    Context context;
    private OnItemClickListener mOnItemClickListener = null;
    List<search_bean.DatasBean.GoodsListBean> list_goods;
    public Mysearch_Adapter(Context context, List<search_bean.DatasBean.GoodsListBean> list_goods) {
        this.context=context;
        this.list_goods=list_goods;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //if(viewType==0){
            View view= LayoutInflater.from(context).inflate(R.layout.item_goods,parent,false);

            view.setOnClickListener(this);
            return new MySearch_viewHolder(view);

//        }else if(viewType==1){
//            View view2= LayoutInflater.from(context).inflate(R.layout.item_goods2,parent,false);
//            view2.setOnClickListener(this);
//            return new MySearch_viewHolder2(view2);
//        }

        //return null;

    }

    @Override
    public int getItemCount() {
        return list_goods.size();
    }

//    @Override
//    public int getItemViewType(int position) {
//        if(position%2==0){
//            return 0;
//        }else {
//            return 1;
//        }
//
//    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        int type = getItemViewType(position);
//        if(type==0){
            MySearch_viewHolder mv=new MySearch_viewHolder(holder.itemView);
            //保存到tag
            mv.itemView.setTag(position);
            search_bean.DatasBean.GoodsListBean sb= list_goods.get(position);
            mv.tv_name.setText(sb.getGoods_name());
            mv.tv_name2.setText(sb.getGoods_jingle());
            mv.tv_priceNow.setText("￥"+sb.getGoods_price());
            mv.tv_priceOld.setText("￥"+sb.getGoods_marketprice());
            mv.tv_storename.setText(sb.getStore_name());
            mv.tv_priceOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            Picasso.with(mv.img.getContext()).load(sb.getGoods_image_url()).into(mv.img);
//        } if (type==1){
//            MySearch_viewHolder2 mv2=new MySearch_viewHolder2(holder.itemView);
//            search_bean.DatasBean.GoodsListBean sb= list_goods.get(position);
//            mv2.tv_name2.setText(sb.getGoods_name());
//            mv2.tv_name22.setText(sb.getGoods_jingle());
//            mv2.tv_priceNow2.setText("￥"+sb.getGoods_price());
//            mv2.tv_priceOld2.setText("￥"+sb.getGoods_marketprice());
//            mv2.tv_storename2.setText(sb.getStore_name());
//            mv2.tv_priceOld2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//            Picasso.with(mv2.img2.getContext()).load(sb.getGoods_image_url()).into(mv2.img2);
//        }


    }
    //自动生成的点击事件
    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view,(int)view.getTag());
        }
    }

    //点击事件
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);

    }

    //最后暴露给外面的调用者，定义一个设置Listener的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public class MySearch_viewHolder extends RecyclerView.ViewHolder{

        TextView tv_name,tv_name2,tv_priceNow,tv_priceOld,tv_storename;
        ImageView img;
        public MySearch_viewHolder(View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.goods_name);
            tv_name2=itemView.findViewById(R.id.goods_jingle);
            tv_priceNow=itemView.findViewById(R.id.goods_price);
            tv_priceOld=itemView.findViewById(R.id.goods_marketprice);
            tv_storename=itemView.findViewById(R.id.goods_storename);
            img=itemView.findViewById(R.id.goods_img);

        }
    }

//    public class MySearch_viewHolder2 extends RecyclerView.ViewHolder{
//
//        TextView tv_name2,tv_name22,tv_priceNow2,tv_priceOld2,tv_storename2;
//        ImageView img2;
//        public MySearch_viewHolder2(View itemView) {
//            super(itemView);
//            tv_name2=itemView.findViewById(R.id.goods_name2);
//            tv_name22=itemView.findViewById(R.id.goods_jingle2);
//            tv_priceNow2=itemView.findViewById(R.id.goods_price2);
//            tv_priceOld2=itemView.findViewById(R.id.goods_marketprice2);
//            tv_storename2=itemView.findViewById(R.id.goods_storename2);
//            img2=itemView.findViewById(R.id.goods_img2);
//
//        }
//    }
}
