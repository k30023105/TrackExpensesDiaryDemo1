package com.example.trackexpensesdiarydemo.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.trackexpensesdiarydemo.MainActivity;
import com.example.trackexpensesdiarydemo.R;
import com.example.trackexpensesdiarydemo.dao.UserDao;
import com.example.trackexpensesdiarydemo.databinding.FragmentHomeBinding;
import com.example.trackexpensesdiarydemo.ui.FunctionActivity;
import com.example.trackexpensesdiarydemo.ui.ReigisterActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding.ReigisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ReigisterActivity.class);
                startActivity(intent);
            }
        });
        binding.LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        UserDao userDao = new UserDao();
                        boolean aa = userDao.login(binding.EmailText.getText().toString(),binding.PasswordText.getText().toString());
                        int msg = 0;
                        if (aa){
                            msg = 1;
                        }
                        hand1.sendEmptyMessage(msg);
                    }
                }.start();
            }
            final Handler hand1 = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    switch (msg.what){
                        case 1:
                            Toast.makeText(getActivity(),"登入成功",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), FunctionActivity.class);
                            startActivity(intent);
                        default:
                            Toast.makeText(getActivity(),"登入失敗",Toast.LENGTH_LONG).show();
                    }
                    return false;
                }
            });
        });
        return root;
    }
    public void onDestroyView () {
        super.onDestroyView();
        binding = null;
    }
}