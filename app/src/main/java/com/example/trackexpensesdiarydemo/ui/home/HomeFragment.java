package com.example.trackexpensesdiarydemo.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trackexpensesdiarydemo.dao.UserDao;

import com.example.trackexpensesdiarydemo.R;
import com.example.trackexpensesdiarydemo.databinding.FragmentHomeBinding;
import com.example.trackexpensesdiarydemo.ui.FunctionActivity;
import com.example.trackexpensesdiarydemo.ui.ReigisterActivity;

import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private SharedPreferences loginPreference;
    private String useremail;
    private String password;

    private Boolean all = false;
    private Boolean every = false;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        loginPreference = getActivity().getSharedPreferences("userInfo", MODE_PRIVATE);
        all = binding.AllRememberCheck.isChecked();
        every = binding.EveryRememberCheck.isChecked();
        binding.AllRememberCheck.setChecked(true);
        if(loginPreference.getBoolean("all",false))
        {
            binding.AllRememberCheck.setChecked(true);
            binding.EmailText.setText(loginPreference.getString("useremail",""));
            binding.PasswordText.setText(loginPreference.getString("password",""));
            Log.e("自動恢復儲存的帳號密碼","自動恢復儲存的帳號密碼");
            if(loginPreference.getBoolean("every",false))
            {
                binding.EveryRememberCheck.setChecked(true);
                Intent intent = new Intent(getActivity(),FunctionActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "已自動登入", Toast.LENGTH_SHORT).show();
                Log.e("自動登入","自動登入");
            }
        }

        binding.LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useremail = binding.EmailText.getText().toString();
                password = binding.PasswordText.getText().toString();
                if(useremail.equals("k30023105@gmail.com") && password.equals("k300"))
                {
                    Toast.makeText(getActivity(), "登入成功", Toast.LENGTH_SHORT).show();
                    if(binding.AllRememberCheck.isChecked())
                    {
                        SharedPreferences.Editor editor = loginPreference.edit();
                        editor.putString("useremail",useremail);
                        editor.putString("password",password);
                        editor.putBoolean("all",all);
                        editor.putBoolean("every",every);
                        editor.commit();
                        Log.e("選中儲存密碼","信箱:" + useremail +
                                "\n" + "密碼:" + password +
                                "\n" + "是否記住信箱及密碼:" + all +
                                "\n" + "是否自動登入:" + every);
                        editor.commit();
                    }
                    Intent start = new Intent(getActivity(),FunctionActivity.class);
                    startActivity(start);
                }
                else
                {
                    Toast.makeText(getActivity(), "使用者信箱或密碼錯誤，請重新登入", Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.ReigisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(getActivity(),ReigisterActivity.class);
                startActivity(register);
            }
        });
        binding.StartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(getActivity(),FunctionActivity.class);
                startActivity(start);
            }
        });
        return root;
    }

    public void onDestroyView () {
        super.onDestroyView();
        binding = null;
    }
}
