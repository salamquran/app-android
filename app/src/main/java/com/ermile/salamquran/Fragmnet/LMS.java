package com.ermile.salamquran.Fragmnet;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ermile.salamquran.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LMS extends Fragment {


    public LMS() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lms, container, false);
    }

}
