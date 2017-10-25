package hjy.gouwu.Adapter.Three_level;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import hjy.gouwu.R;
import hjy.gouwu.Url_data.Url_data;
import hjy.gouwu.bean.Three_levebean.Two_levebean;
import hjy.gouwu.utils.GsonObjectCallback;
import hjy.gouwu.utils.OkHttp3Utils;
import okhttp3.Call;

/**
 * Created by 胡靖宇 on 2017/10/16.
 */

public class Two_leveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>implements View.OnClickListener {
    Context context;
    Three_leveAdapter th;
    int i2;
    List<Two_levebean.DatasBean.ClassListBean> two;
    //第一实现
    private OnItemClickListener mOnItemClickListener = null;
    public Two_leveAdapter(Context context, List<Two_levebean.DatasBean.ClassListBean> two) {
        this.context=context;
        this.two=two;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.twoleve_item,parent,false);
        //2view点击事件
        view.setOnClickListener(this);
        return new Mytwo(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Mytwo md=new Mytwo(holder.itemView);
        //保存到tag
        md.itemView.setTag(position);
        md.tvtwo.setText(two.get(position).getGc_name());

        i2=Integer.parseInt(two.get(position).getGc_id());

    }

    @Override
    public int getItemCount() {
        return two.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view,(int)view.getTag());
        }
    }

    //可调用点击事件
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);

    }

    //最后暴露给外面的调用者，定义一个设置Listener的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener=listener;
    }

    public class Mytwo extends RecyclerView.ViewHolder{

        TextView tvtwo;
        RecyclerView rc3;
        public Mytwo(View itemView) {
            super(itemView);

            tvtwo=itemView.findViewById(R.id.two_item_tv);
            rc3=itemView.findViewById(R.id.message_three2);
            rc3.setLayoutManager(new GridLayoutManager(context,3));

            OkHttp3Utils.doGet(Url_data.My_oneLeve_two + i2, new GsonObjectCallback<Two_levebean>() {
                @Override
                public void onUi(Two_levebean two_levebean) {

                    List<Two_levebean.DatasBean.ClassListBean> three=two_levebean.getDatas().getClass_list();
                    th=new Three_leveAdapter(context,three);
                    rc3.setAdapter(th);
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });
        }
    }
}
