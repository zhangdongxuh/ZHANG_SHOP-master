package shop.yunifang.com.yunifang.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import shop.yunifang.com.yunifang.MainActivity;
import shop.yunifang.com.yunifang.R;

import static com.mob.tools.utils.ResHelper.getStringRes;

public class LoginMainActivity extends AppCompatActivity {

    private ImageView img_zh;
    private ImageView zh1;
    private ImageView zh3;
    private ImageView sj2;
    private ImageView sj4;
    private EditText ed_name;
    private EditText ed_parss;
    private EditText ed_yz;
    private Button img_yzm;
    private TextView wjparss;
    private ImageView img_back;
    private TextView login_dsf;
    private String TAG = "LoginMainActivity";
    private String APPKEY="18e9737cd154b";
    private String APPSECRET="36158714c7858c45a9a0d94ddf947eb6";
    private Button but_login;
    private String iPhone;
    private String iCord;
    private int time = 60;
    private boolean flag = true;// 验证码是否正确标记



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        //初始化控件
        initView();
        login();

    }

    private void login() {

        SMSSDK.initSDK(this, "1914d1e0f1a38",
                "658801a43e091a2f650c349eb9e62425");
        EventHandler eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }

        };
        SMSSDK.registerEventHandler(eh);


    }

    private void initView() {

        //登录输入框
        ed_name =(EditText)findViewById(R.id.ed_name);
        ed_parss=(EditText)findViewById(R.id.ed_parss);
        ed_yz =(EditText)findViewById(R.id.ed_yz);
        //登录控件
        zh1 =(ImageView) findViewById(R.id.img_zh1);
        zh3 =(ImageView) findViewById(R.id.img_zh3);
        sj2 =(ImageView) findViewById(R.id.img_sj2);
        sj4 =(ImageView) findViewById(R.id.img_sj4);
        //获取验证码控件
        img_yzm=(Button) findViewById(R.id.img_yzm);
        //登录控件
        but_login=(Button)findViewById(R.id.but_login);
        //第三方手机登录监听
        but_login.setOnClickListener(loginoncli);
        img_yzm.setOnClickListener(loginoncli);
        //忘记密码控件
        wjparss=(TextView)findViewById(R.id.tv_wj_parss);
        //返回控件
        img_back=(ImageView)findViewById(R.id.img_back);
        //第三方登录控件
        login_dsf=(TextView)findViewById(R.id.img_login_dsf);

        //第三方登录监听点击弹出popwind
        login_dsf.setOnClickListener(dsfonclist);



        //登录切换
        //账号登录监听
        zh3.setOnClickListener(zhonlist);
        zh1.setOnClickListener(zhonlist);
        //手机登录监听
        sj4.setOnClickListener(sjonlist);
        sj2.setOnClickListener(sjonlist);
        //返回监听
        img_back.setOnClickListener(backonclist);
        //手机第三方登录appkey
        SMSSDK.initSDK(this,APPKEY,APPSECRET);
    }

    //账号登录监听
    View.OnClickListener  zhonlist=new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            //布局-------------------------------
            ed_parss.setVisibility(View.VISIBLE);
            img_yzm.setVisibility(View.GONE);
            ed_yz.setVisibility(View.GONE);
            sj2.setVisibility(View.VISIBLE);
            sj4.setVisibility(View.GONE);
            zh1.setVisibility(View.VISIBLE);
            zh3.setVisibility(View.GONE);
            wjparss.setVisibility(View.VISIBLE);
            //--------------------------------
        }
    };
    //手机登录监听
    View.OnClickListener sjonlist=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //布局-------------------------------
            wjparss.setVisibility(View.GONE);
            ed_parss.setVisibility(View.GONE);
            img_yzm.setVisibility(View.VISIBLE);
            ed_yz.setVisibility(View.VISIBLE);
            zh3.setVisibility(View.VISIBLE);
            zh1.setVisibility(View.GONE);
            sj4.setVisibility(View.VISIBLE);
            sj2.setVisibility(View.GONE);
            //--------------------------------
        }
    };

    //返回de监听
    View.OnClickListener backonclist=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SharedPreferences sharedPreferences = getSharedPreferences("itcast", Context.MODE_PRIVATE);
            boolean code = sharedPreferences.getBoolean("name", false);
            if(code!=false){

            }else {
                Intent intent2=getIntent();
                String gw_name = intent2.getStringExtra("name");
                if(gw_name!=null){
                    Intent intent=new Intent(LoginMainActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }

        }
    };


    //第三方qq
    // 登录监听
    View.OnClickListener dsfonclist=new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            View view1= View.inflate(LoginMainActivity.this, R.layout.popw, null);

            Log.d(TAG,"--------------------------------------");

            // 创建 popuWindow对象 视图文件宽高是否能得到焦点
            PopupWindow popuwind = new PopupWindow(view1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            // 点击其他区域进行关闭popuWindow对话框
            popuwind.setOutsideTouchable(true);
            popuwind.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
//				popuwind.showAsDropDown(ks_text);
            popuwind.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        }
    };

    //第三方手机登录监听
    View.OnClickListener loginoncli=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.img_yzm:
                    if (!TextUtils.isEmpty(ed_name.getText().toString().trim())) {
                        if (ed_name.getText().toString().trim().length() == 11) {
                            iPhone = ed_name.getText().toString().trim();
                            //请求获取短信验证码 在监听中返回
                            SMSSDK.getVerificationCode("86", iPhone);
                            ed_yz.requestFocus();
                            //img_yzm.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(LoginMainActivity.this, "请输入完整电话号码",
                                    Toast.LENGTH_LONG).show();
                            ed_name.requestFocus();
                        }
                    } else {
                        Toast.makeText(LoginMainActivity.this, "请输入您的电话号码",
                                Toast.LENGTH_LONG).show();
                        ed_name.requestFocus();
                    }
                    break;

                case R.id.but_login:
                    if (!TextUtils.isEmpty(ed_yz.getText().toString().trim())) {
                        if (ed_yz.getText().toString().trim().length() == 4) {
                            iCord = ed_yz.getText().toString().trim();
                            //提交短信验证码 在监听中返回
                            SMSSDK.submitVerificationCode("86", iPhone, iCord);
                            flag = false;
                        } else {
                            Toast.makeText(LoginMainActivity.this, "请输入完整验证码",
                                    Toast.LENGTH_LONG).show();
                            ed_yz.requestFocus();
                        }
                    } else {
                        Toast.makeText(LoginMainActivity.this, "请输入验证码", Toast.LENGTH_LONG)
                                .show();
                        ed_yz.requestFocus();
                    }
                    break;

                default:
                    break;
            }

        }
    };
    // 验证码送成功后提示文字
    private void reminderText() {
        img_yzm.setVisibility(View.VISIBLE);
        handlerText.sendEmptyMessageDelayed(1, 1000);
    }
    Handler handlerText = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (time > 0) {
                    img_yzm.setText(+ time + "s");
                    time--;
                    handlerText.sendEmptyMessageDelayed(1, 1000);
                } else {
                    img_yzm.setText("提示信息");
                    time = 60;
                    img_yzm.setVisibility(View.GONE);
                    img_yzm.setVisibility(View.VISIBLE);
                }
            } else {
                ed_yz.setText("");
                img_yzm.setText("提示信息");
                time = 60;
                img_yzm.setVisibility(View.GONE);
                img_yzm.setVisibility(View.VISIBLE);
            }
        };
    };


    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event=" + event);
            if (result == SMSSDK.RESULT_COMPLETE) {

                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功,验证通过
                    Toast.makeText(getApplicationContext(), "验证码校验成功",
                            Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("itcast", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("name", true);
                    editor.commit();//提交修改
                    finish();

                    handlerText.sendEmptyMessage(2);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {// 服务器验证码发送成功
                    reminderText();
                    Toast.makeText(getApplicationContext(), "验证码已经发送",
                            Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {// 返回支持发送验证码的国家列表
                    Toast.makeText(getApplicationContext(), "获取国家列表成功",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                if (flag) {
                    img_yzm.setVisibility(View.VISIBLE);
                    Toast.makeText(LoginMainActivity.this, "验证码获取失败，请重新获取",
                            Toast.LENGTH_SHORT).show();
                    ed_name.requestFocus();
                } else {
                    ((Throwable) data).printStackTrace();
                    int resId = getStringRes(LoginMainActivity.this,
                            "smssdk_network_error");
                    Toast.makeText(LoginMainActivity.this, "验证码错误",
                            Toast.LENGTH_SHORT).show();
                    ed_yz.selectAll();
                    if (resId > 0) {
                        Toast.makeText(LoginMainActivity.this, resId,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }


}
