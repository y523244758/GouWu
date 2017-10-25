package hjy.gouwu.Adapter.Three_level;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hjy.gouwu.R;
import hjy.gouwu.bean.Three_levebean.Two_levebean;

/**
 * Created by 胡靖宇 on 2017/10/16.
 */

public class Three_leveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<Two_levebean.DatasBean.ClassListBean> three;
    public Three_leveAdapter(Context context, List<Two_levebean.DatasBean.ClassListBean> three) {
        this.context=context;
        this.three=three;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.threeleve_item,parent,false);
        return new Mythree(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            Mythree mt=new Mythree(holder.itemView);
            mt.tvthree.setText(three.get(position).getGc_name());
    }

    @Override
    public int getItemCount() {
        return three.size();
    }

    public class Mythree extends RecyclerView.ViewHolder{

        TextView tvthree;

        public Mythree(View itemView) {
            super(itemView);
            tvthree=itemView.findViewById(R.id.three_item_tv);


        }
    }
}
