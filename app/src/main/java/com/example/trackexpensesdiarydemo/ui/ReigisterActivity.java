package com.example.trackexpensesdiarydemo.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.trackexpensesdiarydemo.dao.UserDao;
import com.example.trackexpensesdiarydemo.entity.User;

import com.example.trackexpensesdiarydemo.databinding.ActivityReigisterBinding;
import com.example.trackexpensesdiarydemo.ui.home.HomeFragment;

public class ReigisterActivity extends AppCompatActivity {
    private ActivityReigisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReigisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.OKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cnickname = binding.nickNameText.getText().toString();
                String cuseremail = binding.EmailText.getText().toString();
                String cpassword = binding.passwordText.getText().toString();
                String cpassword2 = binding.password2Text.getText().toString();
                if (cnickname.length() < 1 || cuseremail.length() < 9 || cpassword.length() < 4 || cpassword2.length() < 4){
                    Toast.makeText(ReigisterActivity.this,"請輸入正確的格式",Toast.LENGTH_LONG).show();
                    return;
                }
                User user = new User();
                user.setNickname(cnickname);
                user.setUseremail(cuseremail);
                user.setPassword(cpassword);

                new Thread(){
                    @Override
                    public void run() {
                        int msg = 0;
                        UserDao userDao = new UserDao();
                        User uu = userDao.findUser(user.getUseremail());
                        if (uu != null){
                            msg = 1;
                        }
                        boolean flag = userDao.register(user);
                        if (flag){
                            msg = 2;
                        }
                        hand.sendEmptyMessage(msg);
                    }
                }.start();
            }
            final Handler hand = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    switch (msg.what){
                        case 0:
                            Toast.makeText(ReigisterActivity.this,"註冊失敗",Toast.LENGTH_LONG).show();
                        case 1:
                            Toast.makeText(ReigisterActivity.this,"已有同信箱帳號，請換一個信箱或是登入",Toast.LENGTH_LONG).show();
                        case 2:
                            Toast.makeText(ReigisterActivity.this,"註冊成功，即將回到登入介面",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent();
                            intent.putExtra("a","註冊");
                            setResult(RESULT_CANCELED,intent);
                            finish();
                            Intent intent1 = new Intent(ReigisterActivity.this, HomeFragment.class);
                            startActivity(intent1);
                    }
                    return false;
                }
            });
        });
    }
}