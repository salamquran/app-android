package com.ermile.salamquran.online.fragmen.quran.tabLayout;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ermile.salamquran.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class JuzList extends Fragment {

    View fragment_juz_list;
    RecyclerView recylerview_juz;
    QuranList_adapter quranList_adapter;
    LinearLayoutManager LayoutManager;

    public JuzList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment_juz_list= inflater.inflate(R.layout.fragment_juz_list, container, false);

        final ArrayList<QuranList_item> quranlist= new ArrayList<>();
        recylerview_juz = (RecyclerView) fragment_juz_list.findViewById(R.id.recylerview_juz);
        quranList_adapter = new QuranList_adapter(quranlist,getContext());
        LayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        return fragment_juz_list;


    }

}
