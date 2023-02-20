package com.example.trackexpensesdiarydemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.trackexpensesdiarydemo.databinding.ActivityReigisterBinding;

public class ReigisterActivity extends AppCompatActivity {
    private ActivityReigisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReigisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Nickname", binding.nickNameText.getText().toString());
        editor.putString("Email", binding.EmailText.getText().toString());
        editor.putString("Password", binding.passwordText.getText().toString());
        editor.putString("Password2", binding.password2Text.getText().toString());
        editor.apply();
        String str = binding.nickNameText.getText().toString();
        String str2 = binding.EmailText.getText().toString();
        String str3 = binding.passwordText.getText().toString();
        String str4 = binding.password2Text.getText().toString();
        binding.OKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.nickNameText.toString().isEmpty()) {
                    Toast.makeText(ReigisterActivity.this, "暱稱不能為空", Toast.LENGTH_SHORT).show();
                } else if (binding.EmailText.toString().isEmpty()) {
                    Toast.makeText(ReigisterActivity.this, "email不能為空", Toast.LENGTH_SHORT).show();
                } else if (binding.passwordText.toString().isEmpty()) {
                    Toast.makeText(ReigisterActivity.this, "密碼不能為空", Toast.LENGTH_SHORT).show();
                } else if (binding.password2Text.toString().isEmpty()) {
                    Toast.makeText(ReigisterActivity.this, "請再次輸入密碼", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ReigisterActivity.this, "註冊成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ReigisterActivity.this, FunctionActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}