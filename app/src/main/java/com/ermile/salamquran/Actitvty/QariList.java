package com.ermile.salamquran.Actitvty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ermile.salamquran.Function.Utility.Download;
import com.ermile.salamquran.Function.Utility.FileManager;
import com.ermile.salamquran.Function.Utility.SaveManager;
import com.ermile.salamquran.Function.api.Get;
import com.ermile.salamquran.Item.item_Qari;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.file;
import com.ermile.salamquran.Static.format;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.ermile.salamquran.Function.Utility.SaveManager.qari_Slug;

public class QariList extends Dialog {

    private RecyclerView recyclerView;
    private LinearLayoutManager LayoutManager;
    private ArrayList<item_Qari> itemList;


    Activity activity;
    Quran ac_quran;

    public QariList(Activity a) {
        super(a);
        this.activity = a;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qari_list);

        ac_quran = new Quran();
        itemList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView_QariList);
        QariList.Adaptor Adaptors = new QariList.Adaptor(itemList);
        LayoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        addItem();
        recyclerView.setAdapter(Adaptors);
    }

    private void addItem(){
        Get.qariList(new Get.Get_qariList_Listener() {
            @Override
            public void response(String respone) {
                try {
                    JSONArray result = new JSONArray(respone);
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonObject = result.getJSONObject(i);
                        int index = jsonObject.getInt("index");
                        String lang = jsonObject.getString("lang");
                        String type = jsonObject.getString("type");
                        String addr = jsonObject.getString("addr");
                        String slug = jsonObject.getString("slug");
                        String name = jsonObject.getString("name");
                        String image = jsonObject.getString("image");
                        String short_name = jsonObject.getString("short_name");

                        itemList.add(new item_Qari(index,lang,type,addr,slug,name,image,short_name));
                        recyclerView.setLayoutManager(LayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed() {

            }
        });
    }

    private class Adaptor extends RecyclerView.Adapter<QariList.Adaptor.MyViewHolder> {
        List<item_Qari> itemList;

        public Adaptor(List<item_Qari> itemList) {
            this.itemList = itemList;
        }

        @Override
        public QariList.Adaptor.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View aView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qari_list, parent, false);
            return new QariList.Adaptor.MyViewHolder(aView);

        }

        @Override
        public void onBindViewHolder(final QariList.Adaptor.MyViewHolder holder, final int position) {

            final item_Qari aItem = itemList.get(position);

            holder.name.setText(aItem.getName());
            holder.type.setText(aItem.getType());
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SaveManager.get(getContext()).change_qari(aItem.getAddr(),aItem.getName(),aItem.getSlug(),aItem.getType());
                    dismiss();
                }
            });
            if (!FileManager.findFile_storage(file.qari_image,aItem.getSlug()+format.png)){
                Glide   .with(getContext())
                        .load(aItem.getImage())
                        .into(holder.image);
                Download.File(getContext(),aItem.getImage(), file.qari_image,aItem.getSlug(), format.png,"","");
            }
            else {
                File imgFiles = FileManager.getFile_storage(file.qari_image,aItem.getSlug()+format.png);
                if(imgFiles.exists()){
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFiles.getAbsolutePath());
                    holder.image.setImageBitmap(myBitmap);
                }
            }
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView name,type;
            ImageView image;
            RelativeLayout relativeLayout;

            MyViewHolder(View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.imag_itemQariList);
                name = itemView.findViewById(R.id.name_itemQariList);
                type = itemView.findViewById(R.id.type_itemQariLis);
                relativeLayout = itemView.findViewById(R.id.main_itemQariList);
            }
        }

    }
}
