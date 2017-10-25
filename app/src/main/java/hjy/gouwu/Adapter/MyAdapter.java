package hjy.gouwu.Adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import hjy.gouwu.R;

/**
 * Created by 胡靖宇 on 2017/9/28.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyviewHolder>  {

    Context context;
    List<Integer> imgLsit;
    List<String> list_title;


    public MyAdapter(Context context, List<String> list_title, List<Integer> imgLsit) {
        this.context=context;
        this.list_title=list_title;
        this.imgLsit=imgLsit;

    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



            View view= LayoutInflater.from(context).inflate(R.layout.item_gv,parent,false);
            return new MyviewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {


                holder.tv.setText(list_title.get(position));
                holder.img.setImageResource(imgLsit.get(position));

    }

    @Override
    public int getItemCount() {
        return list_title.size();

    }




    public class MyviewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        ImageView img;
        public MyviewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_gv);
            tv=itemView.findViewById(R.id.title_gv);
        }
    }



}
