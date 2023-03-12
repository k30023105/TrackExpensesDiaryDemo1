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
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[3];
                        field[0] = "username";
                        field[1] = "useremail";
                        field[2] = "password";
                        String[] data = new String[3];
                        data[0] = "username";
                        data[1] = "useremail";
                        data[2] = "password";
                        PutData putData = new PutData("http://projects.vishnus.com/AdvancedHttpURLConnection/putData.php","POST",field,data);
                        if (putData.startPut()){
                            if (putData.onComplete()){
                                String result = putData.getResult();
                            }
                        }
                    }
                });
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