package com.example.trackexpensesdiarydemo.ui.diary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trackexpensesdiarydemo.databinding.FragmentDiaryBinding;

public class DiaryFragment extends Fragment {

    private FragmentDiaryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DiaryViewModel slideshowViewModel =
                new ViewModelProvider(this).get(DiaryViewModel.class);

        binding = FragmentDiaryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}