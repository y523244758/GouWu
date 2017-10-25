package hjy.gouwu.Adapter.null_shopp;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hjy.gouwu.R;
import hjy.gouwu.bean.goodList.goodlistbean;

/**
 * Created by 胡靖宇 on 2017/10/20.
 */

public class MyshoppgoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<goodlistbean.DatasBean.CartListBean> gblist;
    public MyshoppgoodsAdapter(Context context, List<goodlistbean.DatasBean.CartListBean> gblist) {
        this.context=context;
        this.gblist=gblist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_shoppinggoods,parent,false);
        Myshoppingtwo mpt=new Myshoppingtwo(view);



        return mpt;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Myshoppingtwo mpt=new Myshoppingtwo(holder.itemView);
        //商品名
        mpt.tv_item_shopcart_clothname.setText(gblist.get(0).getGoods().get(position).getGoods_name().toString());
        //价格
        mpt.tv_item_shopcart_cloth_price.setText("¥"+gblist.get(0).getGoods().get(position).getGoods_price().toString());
        //个数
        mpt.tv_item_shopcart_cloth_num.setText("个数："+gblist.get(0).getGoods().get(position).getGoods_num().toString());
        //可编辑个数
        mpt.et_item_shopcart_cloth_num.setText(gblist.get(0).getGoods().get(position).getGoods_num().toString());
        //加载图片
        Picasso.with(mpt.iv_item_shopcart_cloth_pic.getContext()).load(gblist.get(0).getGoods().get(position).getGoods_image_url()).into(mpt.iv_item_shopcart_cloth_pic);

    }

    @Override
    public int getItemCount() {
        return gblist.get(0).getGoods().size();
    }

    public class Myshoppingtwo extends RecyclerView.ViewHolder{



        //名字，价格，数量，改变数量
        TextView tv_item_shopcart_clothname,tv_item_shopcart_cloth_price,tv_item_shopcart_cloth_num,et_item_shopcart_cloth_num;
        ImageView iv_item_shopcart_cloth_pic;
        //选中
        ImageView tv_item_shopcart_clothselect;



        public Myshoppingtwo(View itemView) {
            super(itemView);

            tv_item_shopcart_clothname=itemView.findViewById(R.id.tv_item_shopcart_clothname);
            tv_item_shopcart_cloth_price=itemView.findViewById(R.id.tv_item_shopcart_cloth_price);
            tv_item_shopcart_cloth_num=itemView.findViewById(R.id.tv_item_shopcart_cloth_num);
            et_item_shopcart_cloth_num=itemView.findViewById(R.id.et_item_shopcart_cloth_num);

            iv_item_shopcart_cloth_pic=itemView.findViewById(R.id.iv_item_shopcart_cloth_pic);
            //选中
            tv_item_shopcart_clothselect=itemView.findViewById(R.id.tv_item_shopcart_clothselect);




        }
    }
}
