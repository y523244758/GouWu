package hjy.gouwu.Url_data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 胡靖宇 on 2017/10/12.
 */

public class Url_data {
   public static SharedPreferences read;
   public static SharedPreferences.Editor editor;
    //ip
    public  static final String My_url="169.254.110.146";
    //我的收货地址
    public  static final String Myaddress_Url="http://"+My_url+"/mobile/index.php?act=member_address&op=address_list&key=";
    //添加收货地址
    public  static final String Add_Myaddress="http://169.254.110.146/mobile/index.php?act=member_address&op=address_add";
    //登陆
    public  static final String My_Login="http://"+My_url+"/mobile/index.php?act=login";
    //注册
    public  static final String My_registre="http://"+My_url+"/mobile/index.php?act=login&op=register";
    //1级列表
    public  static final String My_oneLeve="http://169.254.110.146/mobile/index.php?act=goods_class&gc_id=0";
    //2级
    public  static final String My_oneLeve_two="http://169.254.110.146/mobile/index.php?act=goods_class&gc_id=";
    //详情
    public  static final String Mygoods_detail ="http://169.254.110.146/mobile/index.php?act=goods&op=goods_detail&goods_id=";
    //webview
    public  static final String Mygoods_webview="http://169.254.110.146/mobile/index.php?act=goods&op=goods_body&goods_id=";
    //添加到购物车
    public  static final String MygoodsjoinShooping= "http://169.254.110.146/mobile/index.php?act=member_cart&op=cart_add";
    //购物车列表
    public  static final String MygoodsShoopingList="http://169.254.110.146/mobile/index.php?act=member_cart&op=cart_list";
    //删除购物车
    public  static final String MygoodsShoopingdele= "http://169.254.110.146/mobile/index.php?act=member_cart&op=cart_del";
    public  static  String save_name;
    public  static  String save_key;
    public  static  boolean save_YN;




    public  static void saveData(Context context){
        read = context.getSharedPreferences("test",context.MODE_PRIVATE);
        editor= read.edit();
        save_YN=read.getBoolean("YN",false);
        save_name= read.getString("usname","");
        save_key= read.getString("key","");
    }
}
