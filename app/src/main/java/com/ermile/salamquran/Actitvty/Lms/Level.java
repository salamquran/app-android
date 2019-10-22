package com.ermile.salamquran.Actitvty.Lms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ermile.salamquran.Item.item_LMS.LevelList;
import com.ermile.salamquran.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Level extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager LayoutManager;
    private ArrayList<LevelList> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        int idLevelList = Objects.requireNonNull(getIntent().getIntExtra("id",0));

        itemList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView_lms_level);
        Level.Adaptor Adaptors = new Level.Adaptor(itemList);
        LayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        setLevelList(idLevelList);
        recyclerView.setAdapter(Adaptors);
    }



    private class Adaptor extends RecyclerView.Adapter<Level.Adaptor.MyViewHolder> {
        List<LevelList> itemList;

        public Adaptor(List<LevelList> itemList) {
            this.itemList = itemList;
        }

        @Override
        public Level.Adaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View aView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lms_gruoplist, parent, false);
            return new Level.Adaptor.MyViewHolder(aView);

        }

        @Override
        public void onBindViewHolder(final Level.Adaptor.MyViewHolder holder, final int position) {

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
