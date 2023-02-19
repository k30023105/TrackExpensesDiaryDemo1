package com.example.trackexpensesdiarydemo.ui.uniforminvoicelottery;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trackexpensesdiarydemo.R;

public class UniformInvoiceLotteryFragment extends Fragment {

    private UniformInvoiceLotteryViewModel mViewModel;

    public static UniformInvoiceLotteryFragment newInstance() {
        return new UniformInvoiceLotteryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_uniform_invoice_lottery, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UniformInvoiceLotteryViewModel.class);
        // TODO: Use the ViewModel
    }

}