package com.example.trackexpensesdiarydemo.ui.trackexpenses;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrackExpensesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TrackExpensesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}