package hjy.gouwu.Adapter.Three_level;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import hjy.gouwu.R;
import hjy.gouwu.bean.Three_levebean.Oneleve;

/**
 * Created by 胡靖宇 on 2017/10/16.
 */

public class One_levelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    Context context;

    private OnItemClickListener mOnItemClickListener = null;
    List<Oneleve.DatasBean.ClassListBean> one;
    public One_levelAdapter(Context context, List<Oneleve.DatasBean.ClassListBean> one) {
        this.context=context;
        this.one=one;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.oneleve_item,parent,false);

        //将创建的View注册点击事件
        view.setOnClickListener(this);

        return new Myone(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Myone md=new Myone(holder.itemView);
        md.tvone.setText(one.get(position).getGc_name());
        //保存到tag
        md.itemView.setTag(position);
        if(one.get(position).getImage().isEmpty()) {
            return;
        }else {
            Picasso.with(md.imgone.getContext()).load(one.get(position).getImage()).into(md.imgone);
        }

    }

    @Override
    public int getItemCount() {
        return one.size();
    }

    //点击事件自动生成
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


    public class Myone extends RecyclerView.ViewHolder{

        TextView tvone;
        ImageView imgone;
        public Myone(View itemView) {
            super(itemView);
            tvone=itemView.findViewById(R.id.one_item_tv);
            imgone=itemView.findViewById(R.id.one_item_img);

        }
    }
}
