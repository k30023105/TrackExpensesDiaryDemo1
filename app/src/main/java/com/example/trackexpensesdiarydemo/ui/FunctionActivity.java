package com.example.trackexpensesdiarydemo.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.trackexpensesdiarydemo.R;
import com.example.trackexpensesdiarydemo.databinding.ActivityFunctionBinding;
import com.example.trackexpensesdiarydemo.ui.gallery.GalleryFragment;
import com.example.trackexpensesdiarydemo.ui.slideshow.SlideshowFragment;

public class FunctionActivity extends AppCompatActivity implements View.OnClickListener{
private ActivityFunctionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFunctionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.trackExpensesBtn.setOnClickListener(this);
        binding.diaryBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.trackExpensesBtn:
                Intent intent = new Intent(FunctionActivity.this, GalleryFragment.class);
                startActivity(intent);
                break;
            case R.id.diaryBtn:
                Intent intent1 = new Intent(FunctionActivity.this, SlideshowFragment.class);
                startActivity(intent1);
                break;
        }
    }
}