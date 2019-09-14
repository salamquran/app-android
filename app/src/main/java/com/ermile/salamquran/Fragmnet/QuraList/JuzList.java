package com.ermile.salamquran.Fragmnet.QuraList;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ermile.salamquran.Adaptor.QuranListAdaptor;
import com.ermile.salamquran.Function.Utility.ReadFile;
import com.ermile.salamquran.Item.item_QuranList;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.file;
import com.ermile.salamquran.Static.format;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class JuzList extends Fragment {

    private RecyclerView recylerview_juz;
    private LinearLayoutManager LayoutManager;
    private ArrayList<item_QuranList> quranlist;
    private int HIZB = 0;

    public JuzList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment_juz_list = inflater.inflate(R.layout.fragment_juz_list, container, false);

        quranlist= new ArrayList<>();
        recylerview_juz = fragment_juz_list.findViewById(R.id.recylerview_juz);
        QuranListAdaptor quranList_adapter = new QuranListAdaptor(quranlist, getContext());
        LayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        getHezb();
        recylerview_juz.setAdapter(quranList_adapter);
        return fragment_juz_list;

    }

    /*Get Value*/
    private void getHezb(){
        try {
            String Json_text = ReadFile.FromStorage(Objects.requireNonNull(getContext()), file.list_juz, format.json);
            JSONObject json_objet  = new JSONObject(Json_text);
            JSONObject result = json_objet.getJSONObject("result");

            String number_hezb;
            String title_hezb = null;
            String page_hezb = null;
            String aya_hezb = null;
            String sureh_hezb = null;
            int bgHezb_hezb = 0;

            for(Iterator<String> key_result = result.keys(); key_result.hasNext();) {
                String get_keyResult = key_result.next();
                JSONObject juz = result.getJSONObject(get_keyResult);
                quranlist.add(new item_QuranList(item_QuranList.JUZ_TYPE,0,null,
                        "Juz "+get_keyResult,null,
                        "",null,0));
                recylerview_juz.setLayoutManager(LayoutManager);
                recylerview_juz.setItemAnimator(new DefaultItemAnimator());

                for(Iterator<String> key_juz = juz.keys();key_juz.hasNext();) {
                    String get_keyhezb = key_juz.next();
                    JSONObject hezb = juz.getJSONObject(get_keyhezb);
                    HIZB++;

                    for(Iterator<String> key_rubHezb = hezb.keys();key_rubHezb.hasNext();) {
                        String get_keyrubHezb = key_rubHezb.next();
                        JSONObject rubHezb = hezb.getJSONObject(get_keyrubHezb);


                        if (!rubHezb.isNull("sura_detail")){
                            JSONObject surehHezb = rubHezb.getJSONObject("sura_detail");
                            if (!surehHezb.isNull("tname")){
                                sureh_hezb = surehHezb.getString("tname");
                            }
                        }

                        if (rubHezb.getInt("index_rub") == 1){
                            number_hezb = String.valueOf(HIZB);
                        }else {
                            number_hezb = "";
                        }

                        int getHezb = rubHezb.getInt("index_rub");

                        switch (getHezb){
                            case 1:
                                bgHezb_hezb = R.drawable.hezb_1;
                                break;
                            case 2:
                                bgHezb_hezb = R.drawable.hezb_2;
                                break;
                            case 3:
                                bgHezb_hezb = R.drawable.hezb_3;
                                break;
                            case 4:
                                bgHezb_hezb = R.drawable.hezb_4;
                                break;
                        }
                        if (!rubHezb.isNull("first_word")){
                            title_hezb = rubHezb.getString("first_word");
                        }
                        if (!rubHezb.isNull("page")){
                            page_hezb = rubHezb.getString("page");
                        }
                        if (!rubHezb.isNull("aya")){
                            aya_hezb = rubHezb.getString("aya");
                        }

                        quranlist.add(new item_QuranList(item_QuranList.HEZB_TYPE,0,
                                null,
                                title_hezb,
                                "Surah "+sureh_hezb+ ", Aya "+aya_hezb,
                                page_hezb,
                                number_hezb,
                                bgHezb_hezb));

                        recylerview_juz.setLayoutManager(LayoutManager);
                        recylerview_juz.setItemAnimator(new DefaultItemAnimator());

                    }

                }


            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

}
