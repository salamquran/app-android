package com.ermile.salamquran.online;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ermile.salamquran.R;
import com.ermile.salamquran.online.fragmen.quran.Quran;
import com.ermile.salamquran.online.fragmen.setting.Setting;
import com.ermile.salamquran.saveData.Value;
import com.ermile.salamquran.statice.AboutApp;
import com.ermile.salamquran.statice.ShearApp;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main_online extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationViewEx bottomNavigation;
    Fragment fragment = null;
    FrameLayout fragment_container;
    int MENU_ITEM_ID_ONE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_online);

        bottomNavigation=findViewById(R.id.bottom_navigation);
        bottomNavigation.enableAnimation(false); /*remove anim for navigation*/
        bottomNavigation.enableShiftingMode(false);
        bottomNavigation.enableItemShiftingMode(false);
        bottomNavigation.setTextSize(8f);
        bottomNavigation.setIconSize(28,28);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        bottomNavigation.setSelectedItemId(R.id.nav_quran);
        setTitleBottomNav();





    }

    @Override
    public void onBackPressed() {
        if (bottomNavigation.getMenu().findItem(R.id.nav_quran).isChecked()) {
            super.onBackPressed();
        }else {
            bottomNavigation.setSelectedItemId(R.id.nav_quran);
        }


    }


    private void setTitleBottomNav(){
        try {
            String json = readFromMyFile(Value.jsonFile_local);
            JSONObject respone = new JSONObject(json);
            JSONObject result = respone.getJSONObject("result");
            JSONArray navigation = result.getJSONArray("navigation");

            Menu menuNavigation = bottomNavigation.getMenu();
            for (int i=0 ; i<= navigation.length() ; i++){
                JSONObject objectNav = navigation.getJSONObject(i);
                String icon = objectNav.getString("icon");
                String type = objectNav.getString("type");
                String title = objectNav.getString("title");
                String link = objectNav.getString("link");
                menuNavigation.getItem(i).setTitle(title);
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

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    /*Bottom Navigation Item Selected*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_learn:
                startActivity(new Intent(this, ShearApp.class));
                break;
            case R.id.nav_meg:
                startActivity(new Intent(this, AboutApp.class));
                break;
            case R.id.nav_quran:
                fragment=new Quran();
                break;
            case R.id.nav_search:
                break;
            case R.id.nav_setting:
                fragment = new Setting();
                break;

        }

        return loadFragment(fragment);
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Toast.makeText(this, ""+Boolean.toString(hasCapture), Toast.LENGTH_SHORT).show();

    }
}
