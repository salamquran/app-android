package com.ermile.salamquran.statice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ListView;

import com.ermile.salamquran.R;
import com.ermile.salamquran.saveData.SessionManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Language extends AppCompatActivity {
    public static String TAG = "Language";

    RecyclerView relv_Language;
    List<Item> mItem = new ArrayList<>();
    ItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        /*add RecyclerView and Adapter*/
        relv_Language = findViewById(R.id.lv_Language);
        mAdapter = new ItemAdapter(mItem, this);
        /*Set*/
        StaggeredGridLayoutManager sLayoutManager = new StaggeredGridLayoutManager(0, StaggeredGridLayoutManager.VERTICAL);
        relv_Language.setLayoutManager(sLayoutManager);
    }

    /*Get Language*/
    void GetLanguage(){
        String appLanguage= SessionManager.get(getApplicationContext()).getAppLanguage().get(SessionManager.pref_appLanguage);
        try {
            String Json_text = readFromMyFile("en");
            JSONObject jsonOffline = new JSONObject(Json_text);
            boolean ok = jsonOffline.getBoolean("ok");
            JSONObject result = jsonOffline.getJSONObject("result");
            JSONObject lang_list = result.getJSONObject("lang_list");
            Iterator<?> keys = lang_list.keys();
            while( keys.hasNext() ) {
                String key = (String)keys.next();
                JSONObject lang_key = lang_list.getJSONObject(key);
                if ( lang_list.get(key) instanceof JSONObject ) {
                    if (appLanguage == lang_key.getString("name") )
                    {
                        mItem.add(new Item(
                                lang_key.getString("localname"),
                                lang_key.getString("name"),
                                true,
                                View.VISIBLE));
                    }else {
                        mItem.add(new Item(
                                lang_key.getString("localname"),
                                lang_key.getString("name"),
                                false,
                                View.INVISIBLE));
                    }

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private String readFromMyFile(String filename) throws IOException {
        File file = new File(getApplicationContext().getFilesDir(), filename+".json");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder text = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null) {
            text.append(line);
        }
        bufferedReader.close();
        return text.toString();
    }

    /**Item*/
    class Item {
        public String title;
        public String tag;
        public boolean chBox;
        public int chBoxVisibel;

        public Item(String title, String tag, boolean chBox, int chBoxVisibel) {
            this.title = title;
            this.tag = tag;
            this.chBox = chBox;
            this.chBoxVisibel = chBoxVisibel;
        }

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getTag() {
            return tag;
        }
        public void setTag(String tag) {
            this.tag = tag;
        }
        public boolean isChBox() {
            return chBox;
        }
        public void setChBox(boolean chBox) {
            this.chBox = chBox;
        }
        public int getChBoxVisibel() {
            return chBoxVisibel;
        }
        public void setChBoxVisibel(int chBoxVisibel) {
            this.chBoxVisibel = chBoxVisibel;
        }
    }
    /**Adapter */
    class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

        List<Item> itemList;
        Context mContext;

        public ItemAdapter(List<Item> itemList, Context mContext) {
            this.itemList = itemList;
            this.mContext = mContext;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View aView = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_language, parent, false);
            return new MyViewHolder(aView);

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {

            Item aItem = itemList.get(position);

            holder.titel.setText(aItem.getTitle());
            holder.titel.setTag(aItem.getTag());
            holder.checkBox.setChecked(aItem.chBox);
            holder.checkBox.setVisibility(aItem.chBoxVisibel);
            holder.linrLnaguage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "Item: " + position, Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView titel;
            public CheckBox checkBox;
            public LinearLayout linrLnaguage;


            public MyViewHolder(View itemView) {
                super(itemView);

                linrLnaguage = findViewById(R.id.linrLnaguage);
                titel = itemView.findViewById(R.id.titleLanguage);
                checkBox = itemView.findViewById(R.id.cbLanguage);
            }
        }

    }




}
