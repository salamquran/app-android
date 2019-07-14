package com.ermile.salamquran.statice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ermile.salamquran.R;
import com.ermile.salamquran.Splash;
import com.ermile.salamquran.saveData.SessionManager;
import com.ermile.salamquran.saveData.Value;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        LinearLayoutManager sLayoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        relv_Language.setLayoutManager(sLayoutManager);
        relv_Language.setItemAnimator(new DefaultItemAnimator());
        relv_Language.setHasFixedSize(true);
        relv_Language.setAdapter(mAdapter);
        GetLanguage();




    }

    /*Get Language*/
    void GetLanguage() {
        String appLanguage = SessionManager.get(getApplicationContext()).getAppLanguage().get(SessionManager.pref_appLanguage);
        try {
            String Json_text = readFromMyFile(Value.jsonFile_local);
            JSONObject jsonOffline = new JSONObject(Json_text);
            Log.d(TAG, "GetLanguage: "+Json_text);
            boolean ok = jsonOffline.getBoolean("ok");
            JSONObject result = jsonOffline.getJSONObject("result");
            JSONObject lang_list = result.getJSONObject("lang_list");
            Iterator<?> keys = lang_list.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                JSONObject lang_key = lang_list.getJSONObject(key);
                if (lang_list.get(key) instanceof JSONObject) {
                    if (appLanguage == lang_key.getString("name")) {
                        mItem.add(new Item(
                                lang_key.getString("localname"),
                                lang_key.getString("name"),
                                View.VISIBLE));
                        mAdapter.notifyDataSetChanged();
                    } else {
                        mItem.add(new Item(
                                lang_key.getString("localname"),
                                lang_key.getString("name"),
                                View.INVISIBLE));
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /*Read Json*/
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

        private String title;
        private String tag;
        private int chBoxVisibel;

        Item(String title, String tag, int chBoxVisibel) {
            this.title = title;
            this.tag = tag;
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
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            Item aItem = itemList.get(position);

            holder.titel.setText(aItem.getTitle());
            holder.titel.setTag(aItem.getTag());
            holder.checkLanguage.setVisibility(aItem.getChBoxVisibel());
            holder.linrLnaguage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SessionManager.get(mContext).saveAppLanguage(holder.titel.getTag().toString(),holder.titel.getTag().toString());
                    finish();
                    mContext.startActivity(new Intent(mContext,Splash.class));
                }
            });

        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView titel;
            ImageView checkLanguage;
            LinearLayout linrLnaguage;


            public MyViewHolder(View itemView) {
                super(itemView);
                linrLnaguage = itemView.findViewById(R.id.linrLnaguage);
                titel = itemView.findViewById(R.id.titleLanguage);
                checkLanguage = itemView.findViewById(R.id.checkLanguage);
            }
        }

    }
}



