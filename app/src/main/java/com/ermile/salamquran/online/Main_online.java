package com.ermile.salamquran.online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ermile.salamquran.R;
import com.ermile.salamquran.online.enter.Enter;
import com.ermile.salamquran.saveData.Value;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main_online extends AppCompatActivity {

    BottomNavigationViewEx bottomNavigation;
    int MENU_ITEM_ID_ONE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_online);

        bottomNavigation=findViewById(R.id.bottom_navigation);
        bottomNavigation.enableAnimation(false); /*remove anim for navigation*/
        bottomNavigation.enableShiftingMode(false);
        bottomNavigation.enableItemShiftingMode(false);
        bottomNavigation.setTextSize(10f);
        bottomNavigation.setIconSize(28,28);
        setTitleBottomNav();




    }


    private void setTitleBottomNav(){
        try {
            String json = readFromMyFile(Value.jsonFileName);
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
}
