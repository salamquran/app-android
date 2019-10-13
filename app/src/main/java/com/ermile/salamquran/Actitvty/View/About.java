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

public class About extends AppCompatActivity implements View.OnClickListener {

    private DownloadManager downloadManager;

    private long Image_DownloadId, Music_DownloadId;

    String test_FileName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);



        Button DownloadImage = (Button) findViewById(R.id.DownloadImage);
        DownloadImage.setOnClickListener(this);

        Button DownloadMusic = (Button) findViewById(R.id.DownloadMusic);
        DownloadMusic.setOnClickListener(this);

        Button DownloadStatus = (Button) findViewById(R.id.DownloadStatus);
        DownloadStatus.setOnClickListener(this);
        DownloadStatus.setEnabled(false);

        Button CancelDownload = (Button) findViewById(R.id.CancelDownload);
        CancelDownload.setOnClickListener(this);
        CancelDownload.setEnabled(false);

        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadReceiver, filter);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.DownloadImage:
                for (int i = 1; i <= 10; i++) {
                    test_FileName = "p" + i + format.ttf;
                    String pathFile = url.dl_font_Quran_v1 + test_FileName;
                    if (!FileManager.findFile_storage("font/Quran_v1/",test_FileName)){
                        Uri image_uri = Uri.parse(pathFile);
                        Image_DownloadId = DownloadData(image_uri, v);
                    }
                    else {
                        Toast.makeText(this, "File is Downloaded", Toast.LENGTH_SHORT).show();
                    }


                }
                break;
            //Download Music
            case R.id.DownloadMusic:
                Uri music_uri = Uri.parse("https://dl.salamquran.com/ayat/husary-mujawwad-128/002282.mp3");
                Music_DownloadId = DownloadData(music_uri, v);
                break;
            case R.id.DownloadStatus:
                Check_Image_Status(Image_DownloadId);
                Check_Music_Status(Music_DownloadId);
                break;
            case R.id.CancelDownload:
                downloadManager.remove(Image_DownloadId);
                downloadManager.remove(Music_DownloadId);
                break;
        }
    }

    private void Check_Image_Status(long Image_DownloadId) {

        DownloadManager.Query ImageDownloadQuery = new DownloadManager.Query();
        ImageDownloadQuery.setFilterById(Image_DownloadId);

        Cursor cursor = downloadManager.query(ImageDownloadQuery);
        if(cursor.moveToFirst()){
            DownloadStatus(cursor, Image_DownloadId);
        }

    }

    private void Check_Music_Status(long Music_DownloadId) {

        DownloadManager.Query MusicDownloadQuery = new DownloadManager.Query();
        MusicDownloadQuery.setFilterById(Music_DownloadId);

        Cursor cursor = downloadManager.query(MusicDownloadQuery);
        if(cursor.moveToFirst()){
            DownloadStatus(cursor, Music_DownloadId);
        }

    }

    private void DownloadStatus(Cursor cursor, long DownloadId){

        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
        int status = cursor.getInt(columnIndex);
        int columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);
        int reason = cursor.getInt(columnReason);
        int filenameIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
        String filename = cursor.getString(filenameIndex);

        String statusText = "";
        String reasonText = "";

        switch(status){
            case DownloadManager.STATUS_FAILED:
                statusText = "STATUS_FAILED";
                switch(reason){
                    case DownloadManager.ERROR_CANNOT_RESUME:
                        reasonText = "ERROR_CANNOT_RESUME";
                        break;
                    case DownloadManager.ERROR_DEVICE_NOT_FOUND:
                        reasonText = "ERROR_DEVICE_NOT_FOUND";
                        break;
                    case DownloadManager.ERROR_FILE_ALREADY_EXISTS:
                        reasonText = "ERROR_FILE_ALREADY_EXISTS";
                        break;
                    case DownloadManager.ERROR_FILE_ERROR:
                        reasonText = "ERROR_FILE_ERROR";
                        break;
                    case DownloadManager.ERROR_HTTP_DATA_ERROR:
                        reasonText = "ERROR_HTTP_DATA_ERROR";
                        break;
                    case DownloadManager.ERROR_INSUFFICIENT_SPACE:
                        reasonText = "ERROR_INSUFFICIENT_SPACE";
                        break;
                    case DownloadManager.ERROR_TOO_MANY_REDIRECTS:
                        reasonText = "ERROR_TOO_MANY_REDIRECTS";
                        break;
                    case DownloadManager.ERROR_UNHANDLED_HTTP_CODE:
                        reasonText = "ERROR_UNHANDLED_HTTP_CODE";
                        break;
                    case DownloadManager.ERROR_UNKNOWN:
                        reasonText = "ERROR_UNKNOWN";
                        break;
                }
                break;
            case DownloadManager.STATUS_PAUSED:
                statusText = "STATUS_PAUSED";
                switch(reason){
                    case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
                        reasonText = "PAUSED_QUEUED_FOR_WIFI";
                        break;
                    case DownloadManager.PAUSED_UNKNOWN:
                        reasonText = "PAUSED_UNKNOWN";
                        break;
                    case DownloadManager.PAUSED_WAITING_FOR_NETWORK:
                        reasonText = "PAUSED_WAITING_FOR_NETWORK";
                        break;
                    case DownloadManager.PAUSED_WAITING_TO_RETRY:
                        reasonText = "PAUSED_WAITING_TO_RETRY";
                        break;
                }
                break;
            case DownloadManager.STATUS_PENDING:
                statusText = "STATUS_PENDING";
                break;
            case DownloadManager.STATUS_RUNNING:
                statusText = "STATUS_RUNNING";
                break;
            case DownloadManager.STATUS_SUCCESSFUL:
                statusText = "STATUS_SUCCESSFUL";
                reasonText = "Filename:\n" + filename;
                break;
        }

        if(DownloadId == Music_DownloadId) {

            Toast toast = Toast.makeText(About.this,
                    "Music Download Status:" + "\n" + statusText + "\n" +
                            reasonText,
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 25, 400);
            toast.show();

        }
        else {

            Toast toast = Toast.makeText(About.this,
                    "Image Download Status:"+ "\n" + statusText + "\n" +
                            reasonText,
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 25, 400);
            toast.show();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                }
            }, 3000);

        }

    }

    private long DownloadData (Uri uri, View v) {

        long downloadReference;

        downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setTitle("درحال دریافت " + test_FileName);

        request.setDescription("با تلاوت مشاری العفاسی");

        if(v.getId() == R.id.DownloadMusic)
            request.setDestinationInExternalFilesDir(About.this, "Amin","Programchi.mp3");
        else if(v.getId() == R.id.DownloadImage)
            request.setDestinationInExternalFilesDir(About.this, "font/Quran_v1",test_FileName);

        downloadReference = downloadManager.enqueue(request);

        Button DownloadStatus = (Button) findViewById(R.id.DownloadStatus);
        DownloadStatus.setEnabled(true);
        Button CancelDownload = (Button) findViewById(R.id.CancelDownload);
        CancelDownload.setEnabled(true);

        return downloadReference;
    }

    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            if(referenceId == Image_DownloadId) {

                Toast toast = Toast.makeText(About.this,
                        "Image Download Complete", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
            }
            else if(referenceId == Music_DownloadId) {

                Toast toast = Toast.makeText(About.this,
                        "Music Download Complete", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
            }

        }
    };

}