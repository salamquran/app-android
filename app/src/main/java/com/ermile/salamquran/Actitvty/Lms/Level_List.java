package com.ermile.salamquran.Actitvty.Lms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ermile.salamquran.Fragmnet.Lms.LMS;
import com.ermile.salamquran.Function.api.LMS_api;
import com.ermile.salamquran.Item.item_LMS.GroupList;
import com.ermile.salamquran.Item.item_LMS.LevelList;
import com.ermile.salamquran.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Level_List extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager LayoutManager;
    private ArrayList<LevelList> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_list);

        int idLevelList = Objects.requireNonNull(getIntent().getIntExtra("id",0));

        itemList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView_lms_levelList);
        Level_List.Adaptor Adaptors = new Level_List.Adaptor(itemList);
        LayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        setLevelList(idLevelList);
        recyclerView.setAdapter(Adaptors);

    }

    private void setLevelList(int id){
            /*      {
        "id": "d",
        "lm_group_id": "3",
        "title": "آزمون سراسری تابستان",
        "desc": null,
        "type": "quran",
        "type_title": "Quran",
        "quranfrom": "1",
        "quranto": "36",
        "besmellah": "1",
        "file": "http://salamquran.local/static/siftal/images/default/logo.png",
        "setting": null,
        "sort": null,
        "ratio": null,
        "unlockscore": null,
        "status": "enable",
        "datecreated": "2019-07-27 15:53:15",
        "questionrandcount": null,
        "filepic": null,
        "userstar": "3"
      }*/
        LMS_api.getLevelList(this, id, new LMS_api.levelList_ListListener() {
            @Override
            public void onGetLevelList(String token) {
                try {
                    JSONArray jsonArray = new JSONArray(token);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String userstar = null
                                ,file = null;
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String title = jsonObject.getString("title");
                        if (!jsonObject.isNull("userstar")){
                            userstar = jsonObject.getString("userstar");
                        }
                        if (!jsonObject.isNull("file")){
                            file = jsonObject.getString("file");
                        }


                        itemList.add(new LevelList(id,null,title,null,null,null,null,
                                null,null,file,null,null,null,null,null,null,
                                null,null,userstar,null));
                        recyclerView.setLayoutManager(LayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onErrorLevelList(String error) {

            }
        });
    }

    private class Adaptor extends RecyclerView.Adapter<Level_List.Adaptor.MyViewHolder> {
        List<LevelList> itemList;

        public Adaptor(List<LevelList> itemList) {
            this.itemList = itemList;
        }

        @Override
        public Level_List.Adaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View aView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lms_gruoplist, parent, false);
            return new Level_List.Adaptor.MyViewHolder(aView);

        }

        @Override
        public void onBindViewHolder(final Level_List.Adaptor.MyViewHolder holder, final int position) {

            LevelList aItem = itemList.get(position);

            holder.titel.setText(aItem.getTitle());
            holder.desc.setText(aItem.getUserstar());
//            holder.image.setba(aItem.getFile());

        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView titel,desc;
            ImageView image;

            MyViewHolder(View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.item_groupList_2_imageView);
                titel = itemView.findViewById(R.id.item_groupList_3_title);
                desc = itemView.findViewById(R.id.item_groupList_4_desc);
            }
        }

    }
}
