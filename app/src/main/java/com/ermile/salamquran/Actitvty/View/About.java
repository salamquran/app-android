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
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ermile.salamquran.Function.Utility.Download;
import com.ermile.salamquran.Function.Utility.FileManager;
import com.ermile.salamquran.R;
import com.ermile.salamquran.Static.format;
import com.ermile.salamquran.Static.url;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.ermile.salamquran.Function.Utility.Download.onDownloadComplete;

public class About extends AppCompatActivity {





        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


            Button DownloadImage = (Button) findViewById(R.id.DownloadImage);
            DownloadImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 1; i <= 7; i++) {
                        Download.File(getApplication(),"https://dl.salamquran.com/ayat/afasy-murattal-192/00100"+i+".mp3","afasy-murattal-192/Page-1/","00"+i,format.mp3,null,null);
                    }
                }
            });
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(onDownloadComplete, filter);

    }








}