package com.ermile.salamquran.Actitvty.View;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.ermile.salamquran.Function.Utility.Download;
import com.ermile.salamquran.Function.Utility.FileManager;
import com.ermile.salamquran.Function.Utility.even_odd;
import com.ermile.salamquran.MyDatabase;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.format;
import com.ermile.salamquran.Static.tag;
import com.ermile.salamquran.Static.url;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.ermile.salamquran.Function.Utility.Download.onDownloadComplete;

public class About extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

            ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
            pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
            pager.setOffscreenPageLimit(3);







    }




    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {

            if (even_odd.isOdd(pos)){
                return BlankFragment2.newInstance(""+pos);
            }
            else {
                return BlankFragment.newInstance(""+pos
                );
            }

        }

        @Override
        public int getCount() {
            return 605;
        }
    }



}