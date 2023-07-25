package com.example.activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static String TAG = "Main";
    final static int CODE = 0x1111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button about = findViewById(R.id.about);

        Button submit = findViewById(R.id.submit);

        EditText usertext = findViewById(R.id.usertext);

        EditText pwdtext = findViewById(R.id.pwdtext);

        EditText repwdtext = findViewById(R.id.repwdtext);

        EditText emailtext = findViewById(R.id.emailtext);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, AboutActivity.class); //创建Intent对象
                startActivity(intent); //启动About Activity
            }
        });

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == CODE) {
                        Intent data = result.getData();
                        // 处理返回的数据
                        String user = data.getStringExtra("user");
                        String pwd = data.getStringExtra("pwd");
                        String email = data.getStringExtra("email");
                    }
                });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = usertext.getText().toString(); //获取输入的用户名
                String pwd = pwdtext.getText().toString(); //获取输入的密码
                String repwd = repwdtext.getText().toString(); //获取输入的确认密码
                String email = emailtext.getText().toString(); //获取输入的E-mail地址
                if (!"".equals(user) && !"".equals(pwd) && !"".equals(email)) {
                    if (!pwd.equals(repwd)) { //判断两次输入的密码是否一致
                        Toast.makeText(MainActivity.this, "两次输入的密码不一致，请重新输入！",
                                Toast.LENGTH_LONG).show();
                        pwdtext.setText(""); //清空“密码”编辑框
                        repwdtext.setText(""); //清空“确认密码”编辑框
                        pwdtext.requestFocus(); //让“密码”编辑框获得焦点
                } else {
                        //startActivity(intent);//跳转到新的Activity
                        Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                        intent.putExtra("user", user);
                        intent.putExtra("pwd", pwd);
                        intent.putExtra("email", email);
                        launcher.launch(intent);
                    }
            }else{
                Toast.makeText(MainActivity.this, "请将注册信息输入完整！", Toast.LENGTH_LONG).show();
            }
          }
        });

    }
    //恢复方法
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onPause: 恢复");
    }
    //暂停/失去焦点
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: 暂停");
    }

}