package hjy.gouwu.Adapter.address_Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import hjy.gouwu.R;
import hjy.gouwu.UserAddress.Myaddress;
import hjy.gouwu.bean.address_bean.address_All;

import static android.R.id.list;

/**
 * Created by 胡靖宇 on 2017/10/13.
 */

public class Myaddress_Adapter extends BaseAdapter {
    Context context;
    List<address_All.DatasBean.AddressListBean> listall;
    public Myaddress_Adapter(Context context, List<address_All.DatasBean.AddressListBean> listall) {
        this.context=context;
        this.listall=listall;
    }

    @Override
    public int getCount() {
        return listall.size();
    }

    @Override
    public Object getItem(int i) {
        return listall.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder2 holder;
        if(view==null){
            holder=new ViewHolder2();
            view=View.inflate(context, R.layout.myaddressitem,null);
            holder.aname=view.findViewById(R.id.item_address_name);
            holder.aphone=view.findViewById(R.id.item_address_phone);
            holder.aaddress=view.findViewById(R.id.item_address_address);
            holder.aare_id=view.findViewById(R.id.item_address_area_id);
            holder.mr=view.findViewById(R.id.item_address_true);

            view.setTag(holder);

        }else {
            holder= (ViewHolder2) view.getTag();
        }

        address_All.DatasBean.AddressListBean aa= listall.get(i);
        holder.aname.setText(aa.getTrue_name());
        holder.aphone.setText(aa.getMob_phone()+"");
        holder.aaddress.setText(aa.getAddress());
        holder.aare_id.setText(aa.getArea_info());
        if(aa.getDlyp_id().equals(1)){
            holder.mr.setText("[默认]");
        }else {
            holder.mr.setText("[非默认]");
        }

        return view;

    }

    class ViewHolder2{
        TextView aname,aphone,aaddress,aare_id,mr;
    }
}
