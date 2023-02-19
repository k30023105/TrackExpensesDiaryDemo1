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

import com.example.trackexpensesdiarydemo.R;
import com.example.trackexpensesdiarydemo.databinding.FragmentHomeBinding;
import com.example.trackexpensesdiarydemo.ui.FunctionActivity;
import com.example.trackexpensesdiarydemo.ui.ReigisterActivity;

import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    
    private SharedPreferences loginPreference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        loginPreference = this.getSharedPreferences("userInfo", MODE_PRIVATE);
        binding.ReigisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReigisterActivity.class);
                startActivity(intent);
            }
        });
        binding.StartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),FunctionActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    private Map<String, Object> readLogin() {
        return null;
    }

    private SharedPreferences getSharedPreferences(String login, int modePrivate) {
        return null;
    }

    public void onDestroyView () {
        super.onDestroyView();
        binding = null;
    }
}
