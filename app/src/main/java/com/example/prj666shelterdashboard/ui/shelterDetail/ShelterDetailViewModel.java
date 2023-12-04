package com.example.prj666shelterdashboard.ui.shelterDetail;

import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShelterDetailViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private TextView locationAddress;

    public ShelterDetailViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("In the Detail Tab");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
