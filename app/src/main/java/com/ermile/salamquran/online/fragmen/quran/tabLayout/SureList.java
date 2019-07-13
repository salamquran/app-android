package com.ermile.salamquran.online.fragmen.quran.tabLayout;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ermile.salamquran.R;
import com.ermile.salamquran.saveData.Value;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SureList extends Fragment {
    View fragment_sure_list;
    RecyclerView recylerview_surah;
    QuranList_adapter quranList_adapter;
    LinearLayoutManager LayoutManager;
    public SureList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment_sure_list = inflater.inflate(R.layout.fragment_sure_list, container, false);
        final ArrayList<QuranList_item> quranlist= new ArrayList<>();


        recylerview_surah =  fragment_sure_list.findViewById(R.id.recylerview_surah);
        quranList_adapter = new QuranList_adapter(quranlist,getContext());
        LayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        return fragment_sure_list;



    }


    /*Get Value*/
    private void getSure(){
        try {
            String Json_text = readFromMyFile(Value.jsonFileName);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromMyFile(String filename) throws IOException {
        File file = new File(getContext().getFilesDir(), filename+".json");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder text = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null) {
            text.append(line);
        }
        bufferedReader.close();
        return text.toString();
    }

}
