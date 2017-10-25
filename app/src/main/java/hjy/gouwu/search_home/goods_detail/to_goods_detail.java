package hjy.gouwu.search_home.goods_detail;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hjy.gouwu.MainActivity;
import hjy.gouwu.R;
import hjy.gouwu.Url_data.Url_data;
import hjy.gouwu.bean.addShopping.addshoppbead;
import hjy.gouwu.bean.goods_detail.goodsdetail;
import hjy.gouwu.utils.GsonObjectCallback;
import hjy.gouwu.utils.OkHttp3Utils;
import okhttp3.Call;

import static hjy.gouwu.R.id.edt;

public class to_goods_detail extends AppCompatActivity {

    private TextView gname, gjinglename, gpromotion_price, gprice, garea_name, gcontent, store_cn, store_name, price, count1;
    private ImageView gimg;
    private Button gshopping, g_goshopping;
    private EditText numt;

    private  hjy.gouwu.bean.goods_detail.goodsdetail.DatasBean.GoodsInfoBean gd;
    private  String count;
    private String popprice, imgurl;
    private int goodsid, num;
    private PopupWindow pop;
    private Map<String,String> mapadd=new HashMap<String,String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_goods_detail);


        gname = (TextView) findViewById(R.id.goodsdetail_name);
        gjinglename = (TextView) findViewById(R.id.goodsdetail_jingle);
        gpromotion_price = (TextView) findViewById(R.id.goodsdetail_promotion_price);
        gprice = (TextView) findViewById(R.id.goodsdetail_price);
        garea_name = (TextView) findViewById(R.id.goodsdetail_area_name);
        gcontent = (TextView) findViewById(R.id.goodsdetail_content);
        store_cn = (TextView) findViewById(R.id.goodsdetail_store_cn);
        store_name = (TextView) findViewById(R.id.goodsdetail_store_name);

        gimg = (ImageView) findViewById(R.id.goodsdetail_img);
        gshopping = (Button) findViewById(R.id.goodsdetai_shopping);
        g_goshopping = (Button) findViewById(R.id.goodsdetail_joinshopping);
        Intent getIntent = getIntent();
        goodsid = getIntent.getIntExtra("id", 0);

        gshopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent show=new Intent(to_goods_detail.this,MainActivity.class);
//
//                startActivity(show);

            }
        });

        //wv= (WebView) findViewById(R.id.goodsdetail_wbview);

//        wv.loadUrl(Url_data.Mygoods_webview+goodsid);
//        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
//        wv.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                // TODO Auto-generated method stub
//                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
//                view.loadUrl(url);
//                return true;
//            }
//        });
        //Toast.makeText(this,goodsid+"",Toast.LENGTH_SHORT).show();


        okhttp();

    }

    private void okhttp() {
        OkHttp3Utils.doGet(Url_data.Mygoods_detail + goodsid, new GsonObjectCallback<goodsdetail>() {
            @Override
            public void onUi(goodsdetail goodsdetail) {
                gd = goodsdetail.getDatas().getGoods_info();

                gname.setText(gd.getGoods_name().toString());
                gjinglename.setText(gd.getGoods_jingle());
                //获取值
                popprice = gd.getGoods_promotion_price().toString();
                count = goodsdetail.getDatas().getStore_info().getGoods_count().toString();
                imgurl = goodsdetail.getDatas().getSpec_image().get(0).toString();

                gpromotion_price.setText("￥" + gd.getGoods_promotion_price().toString());
                gprice.setText("市场价格￥" + gd.getGoods_price() + "-" + gd.getGoods_marketprice().toString());
                gprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                garea_name.setText(goodsdetail.getDatas().getGoods_hair_info().getArea_name().toString());
                gcontent.setText(goodsdetail.getDatas().getGoods_hair_info().getContent().toString());
                store_cn.setText(goodsdetail.getDatas().getGoods_hair_info().getIf_store_cn().toString());
                store_name.setText(goodsdetail.getDatas().getStore_info().getStore_name().toString());
                Picasso.with(gimg.getContext()).load(goodsdetail.getDatas().getGoods_image()).into(gimg);


                g_goshopping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(Url_data.read.getString("key","").toString().equals("")) {
                            Toast.makeText(to_goods_detail.this, "请先登录",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            showPopupWindow(popprice, count);
                        }


                    }
                });
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }


    //popwindow
    private void showPopupWindow(String pprice, String counts) {


        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.goods_popwin, null);
        pop = new PopupWindow(contentView,
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        pop.setContentView(contentView);
        pop.setBackgroundDrawable(new ColorDrawable());
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        //设置各个控件的点击响应

        price = contentView.findViewById(R.id.popprice);
        count1 = contentView.findViewById(R.id.popcount);

        price.setText("￥" + pprice);
        count1.setText("库存" + counts + "件");

        ImageView img = contentView.findViewById(R.id.imageView);
        ImageView imgpop = contentView.findViewById(R.id.popimg);
        ImageButton add = contentView.findViewById(R.id.addbt);
        ImageButton sub = contentView.findViewById(R.id.subbt);
        Button join=contentView.findViewById(R.id.popjoinshooping);
        numt = contentView.findViewById(edt);

        //给buttonTag赋值以便判断
        add.setTag("-");
        sub.setTag("+");
        //按钮点击事件
        add.setOnClickListener(new OnButtonClickListener());
        sub.setOnClickListener(new OnButtonClickListener());
        numt.addTextChangedListener(new OnTextChangeListener());
        Picasso.with(imgpop.getContext()).load(imgurl).into(imgpop);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop.dismiss();
            }
        });

        //提交添加购物车
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(to_goods_detail.this,numt.getText().toString()+"件商品加入购物车",
                        Toast.LENGTH_SHORT).show();

                //int numadd=Integer.parseInt(numt.getText().toString());
               String s=String.valueOf(goodsid);
                mapadd.put("key",Url_data.read.getString("key",""));
                mapadd.put("goods_id",s);
                mapadd.put("quantity",numt.getText().toString());

                if(Url_data.read.getString("key","").toString().equals("")) {
                    Toast.makeText(to_goods_detail.this, "请先登录",
                            Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    okhttpjoin();
                }
            }
        });

        //显示PopupWindow
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        pop.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

    }

    private void okhttpjoin() {

        OkHttp3Utils.doPost(Url_data.MygoodsjoinShooping, mapadd, new GsonObjectCallback<addshoppbead>() {
            @Override
            public void onUi(addshoppbead addshoppbead) {
                if(addshoppbead.getCode()==200){
                    showNormalDialog();
                }


            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }

    //加减两个按钮
    class OnButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //得到输入框里的数字
            String numString = numt.getText().toString();
            //进行判断为空或是没文字设置为0
            if (numString == null || numString.equals("")) {
                num = 0;
                numt.setText("0");
            } else {
                //当点击-的时候一次递减（num--）
                if (v.getTag().equals("-")) {
                    //判断（大于0的才能往下减）
                    if (++num < 0)  //先加，再判断
                    {
                        num--;
                        Toast.makeText(to_goods_detail.this, "购买大于0件",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        numt.setText(String.valueOf(num));
                    }
                } else if (v.getTag().equals("+")) {
                    //判断（自减限制数不小于0）
                    if (--num < 0)  //先减，再判断
                    {
                        num++;
                        Toast.makeText(to_goods_detail.this, "不能小于0件", Toast.LENGTH_SHORT).show();
                    } else {
                        numt.setText(String.valueOf(num));
                    }
                }
            }
        }
    }

    //输入框的获取值
    class OnTextChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String numString = s.toString();
            if (numString == null || numString.equals("")) {
                num = 0;
            } else {
                int numInt = Integer.parseInt(numString);
                if (numInt < 0) {
                    Toast.makeText(to_goods_detail.this, "购买大于0件",
                            Toast.LENGTH_SHORT).show();
                } else {
                    //设置EditText光标位置 为文本末端
                    numt.setSelection(numt.getText().toString().length());
                    num = numInt;

                }
            }
        }
    }

    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(to_goods_detail.this);
        normalDialog.setIcon(R.mipmap.ic_launcher_round);
        normalDialog.setTitle("已经加入购物车！");
        normalDialog.setMessage("加入的商品："+gd.getGoods_name().toString()+"------"+numt.getText().toString()+"件");
        normalDialog.setPositiveButton("继续看看",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        dialog.dismiss();
                    }
                });
        normalDialog.setNegativeButton("去购物车",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        Intent show=new Intent(to_goods_detail.this,MainActivity.class);

                        show.putExtra("go",1);
                        startActivity(show);
                        finish();

                    }
                });
        // 显示
        normalDialog.show();
    }

}
