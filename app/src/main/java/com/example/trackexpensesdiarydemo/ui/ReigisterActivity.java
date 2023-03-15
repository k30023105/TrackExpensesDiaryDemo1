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
import com.vishnusivadas.advanced_httpurlconnection.PutData;

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
                String nickname, useremail, password, password2;
                nickname = String.valueOf(binding.nickNameText.getText());
                useremail = String.valueOf(binding.EmailText.getText());
                password = String.valueOf(binding.passwordText.getText());
                password2 = String.valueOf(binding.password2Text.getText());

                if (nickname.equals("") && useremail.equals("") && password.equals("") && password2.equals("")) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[3];
                            field[0] = "nickname";
                            field[1] = "useremail";
                            field[2] = "password";
                            field[3] = "password2";
                            String[] data = new String[3];
                            data[0] = "username";
                            data[1] = "useremail";
                            data[2] = "password";
                            data[3] = "password2";
                            PutData putData = new PutData("http://localhost/LoginRegister/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if (result.equals("Sign Up Success")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), HomeFragment.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "請完成全部欄位的填寫", Toast.LENGTH_SHORT).show();
                }
            }
        });
            }
        }