package com.ermile.salamquran.online.fragmen.quran;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ermile.salamquran.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Quran extends Fragment {


    public Quran() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quran, container, false);
    }

}
