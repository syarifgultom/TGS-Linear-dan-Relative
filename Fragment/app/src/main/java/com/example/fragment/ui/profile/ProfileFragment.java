package com.example.fragment.ui.profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;


import com.example.fragment.R;

public class ProfileFragment extends Fragment{
    @Nullable
    @Override

    public  View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_profile, null);
    }
}