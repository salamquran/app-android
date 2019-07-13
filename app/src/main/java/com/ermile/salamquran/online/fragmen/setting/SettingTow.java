package com.ermile.salamquran.online.fragmen.setting;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ermile.salamquran.R;
import com.ermile.salamquran.saveData.Value;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingTow extends Fragment {
    private static String TAG = "fSettingTow";
    private View fragment_setting_tow;
    private RecyclerView rv_fSettingTow;
    private boolean itemIsAdded = false;
    private List<SettingTow.Item> mItem = new ArrayList<>();
    private SettingTow.ItemAdapter mAdapter;

    public SettingTow() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragment_setting_tow=inflater.inflate(R.layout.fragment_setting_tow, container, false);
        findViewById();

        mAdapter = new SettingTow.ItemAdapter(mItem,getContext());


        /*Set*/
        LinearLayoutManager sLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rv_fSettingTow.setLayoutManager(sLayoutManager);
        rv_fSettingTow.setItemAnimator(new DefaultItemAnimator());
        rv_fSettingTow.setHasFixedSize(true);
        rv_fSettingTow.setAdapter(mAdapter);
        getMenu();


        return fragment_setting_tow;

    }

    /*Get Language*/
    void getMenu() {
        try {
            String Json_text = readFromMyFile(Value.jsonFileName);
            JSONObject jsonOffline = new JSONObject(Json_text);
            Log.d(TAG, "GetLanguage: "+Json_text);
            boolean ok = jsonOffline.getBoolean("ok");
            JSONObject result = jsonOffline.getJSONObject("result");
            JSONArray menu = result.getJSONArray("menu");
            JSONObject keyMenu = menu.getJSONObject(1);
            JSONArray child = keyMenu.getJSONArray("child");
            for (int i = 0 ; i<= child.length() ; i++){
                JSONObject keyChild = child.getJSONObject(i);
                String title = keyChild.getString("title");
                mItem.add(new SettingTow.Item(title));
                mAdapter.notifyDataSetChanged();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /*Read Json*/
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
    /*Find Id*/
    private void findViewById(){
        rv_fSettingTow=fragment_setting_tow.findViewById(R.id.rv_fSettingTow);
    }

    /**Item*/
    class Item {

        private String title;

        public Item(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }


    /**Adapter */
    class ItemAdapter extends RecyclerView.Adapter<SettingTow.ItemAdapter.MyViewHolder> {

        List<SettingTow.Item> itemList;
        Context mContext;

        public ItemAdapter(List<SettingTow.Item> itemList, Context mContext) {
            this.itemList = itemList;
            this.mContext = mContext;
        }

        @Override
        public SettingTow.ItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View aView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting_menuitem, parent, false);
            return new SettingTow.ItemAdapter.MyViewHolder(aView);

        }

        @Override
        public void onBindViewHolder(final SettingTow.ItemAdapter.MyViewHolder holder, final int position) {

            SettingTow.Item aItem = itemList.get(position);

            holder.titel.setText(aItem.getTitle());


        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView titel;


            public MyViewHolder(View itemView) {
                super(itemView);
                titel = itemView.findViewById(R.id.tvTitle_itemMenuSetting);
            }
        }

    }

}
