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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
public class Setting extends Fragment {

    private static final String TAG = "fSetting";
    private View activity_setting;
    private LinearLayout ll_fragment_setting;
    private RecyclerView rv_fSetting;
    private boolean itemIsAdded = false;
    private List<Setting.Item> mItem = new ArrayList<>();
    private Setting.ItemAdapter mAdapter;


    public Setting() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity_setting = inflater.inflate(R.layout.fragment_setting, container, false);
        findViewById();
        mAdapter = new Setting.ItemAdapter(mItem, getContext());


        /*Set*/
        LinearLayoutManager sLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rv_fSetting.setLayoutManager(sLayoutManager);
        rv_fSetting.setItemAnimator(new DefaultItemAnimator());
        rv_fSetting.setHasFixedSize(true);
        rv_fSetting.setAdapter(mAdapter);
        if (!itemIsAdded){
            getMenu();
            itemIsAdded=true;
        }

        return activity_setting;
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
            for (int i = 0 ; i<= menu.length() ; i++){
                JSONObject keyMenu = menu.getJSONObject(i);
                String title = keyMenu.getString("title");
                if (!keyMenu.isNull("child")){
                    mItem.add(new Setting.Item(title,true,i));
                    mAdapter.notifyDataSetChanged();
                }else {
                    mItem.add(new Setting.Item(title,false,i));
                    mAdapter.notifyDataSetChanged();
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
        rv_fSetting = activity_setting.findViewById(R.id.rv_fSetting);
        ll_fragment_setting=activity_setting.findViewById(R.id.ll_fragment_setting);
    }



    /**Item*/
    class Item {

        private String title;
        private boolean nextMenu;
        private int jsonKeyMeun;

        public Item(String title, boolean nextMenu, int jsonKeyMeun) {
            this.title = title;
            this.nextMenu = nextMenu;
            this.jsonKeyMeun = jsonKeyMeun;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isNextMenu() {
            return nextMenu;
        }

        public void setNextMenu(boolean nextMenu) {
            this.nextMenu = nextMenu;
        }

        public int getJsonKeyMeun() {
            return jsonKeyMeun;
        }

        public void setJsonKeyMeun(int jsonKeyMeun) {
            this.jsonKeyMeun = jsonKeyMeun;
        }
    }


    /**Adapter */
    class ItemAdapter extends RecyclerView.Adapter<Setting.ItemAdapter.MyViewHolder> {

        List<Setting.Item> itemList;
        Context mContext;

        public ItemAdapter(List<Setting.Item> itemList, Context mContext) {
            this.itemList = itemList;
            this.mContext = mContext;
        }

        @Override
        public Setting.ItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View aView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting_menuitem, parent, false);
            return new Setting.ItemAdapter.MyViewHolder(aView);

        }

        @Override
        public void onBindViewHolder(final Setting.ItemAdapter.MyViewHolder holder, final int position) {

            final Setting.Item aItem = itemList.get(position);

            holder.titel.setText(aItem.getTitle());
            if (aItem.isNextMenu()){
                holder.titel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("jsonKeyValue",aItem.getJsonKeyMeun());
                        SettingTow nextFrag= new SettingTow();
                        nextFrag.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    }
                });
            }


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
