package com.a1051743852.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends Activity implements OnClickListener {

    // 注册按钮
    private Button registerBtn;

    // 调试文本，注册文本
    private TextView logLink, tip;
    // 显示用户名和密码
    EditText pwd, email, pwd_conf;
    // 创建等待框
    private ProgressDialog dialog;
    // 返回的数据
    private String info;
    // 返回主线程更新数据
    private static Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // 获取控件
        email = findViewById(R.id.input_email);
        pwd = findViewById(R.id.input_password);
        pwd_conf = findViewById(R.id.input_reEnterPassword);
        tip = findViewById(R.id.info);

        registerBtn = findViewById(R.id.btn_signup);
        logLink = findViewById(R.id.link_login);

        // 设置按钮监听器
        registerBtn.setOnClickListener(this);
        logLink.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
                // 检测网络，无法检测wifi
                if (!checkNetwork()) {
                    Toast toast = Toast.makeText(SignupActivity.this, "网络未连接", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    break;
                }
                // 提示框
                dialog = new ProgressDialog(this);
                dialog.setTitle("提示");
                dialog.setMessage("注册账号中，请稍后...");
                dialog.setCancelable(false);
                dialog.show();
                // 创建子线程，分别进行Get和Post传输
                new Thread(new MyThread()).start();
                break;
            case R.id.link_login:
                Intent regItn = new Intent(SignupActivity.this, Login.class);
                // overridePendingTransition(anim_enter);
                startActivity(regItn);
                break;
        }

    }

    // 子线程接收数据，主线程修改数据
    public class MyThread implements Runnable {
        @Override
        public void run() {
            if (email.getText().toString().equals("") || !pwd.getText().toString().equals(pwd_conf.getText().toString())
                    || pwd.getText().toString().equals("") || pwd_conf.getText().toString().equals("")) {
                tip.setText("请检查注册的内容！！");
                dialog.dismiss();
                // alert.dismiss();
            } else {

                info = WebService_register.executeHttpGet(email.getText().toString(), pwd.getText().toString());
                tip.setText("注册成功");
                //info = WebServicePost.executeHttpPost(username.getText().toString(), password.getText().toString());


                handler.post(new Runnable() {
                    @Override
                    public void run() {


//                    infot.setText(info);
                        dialog.dismiss();
                    }
                });
            }

        }
    }

    // 检测网络
    private boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }

}
