package com.ermile.salamquran.Functions;


import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.ermile.salamquran.Value.format;

import java.io.File;

import static android.content.Context.DOWNLOAD_SERVICE;

public class Download {

    private static long downloadID;

    public static void File(Context context,
                    String URL,
                    String Folder, String FileName, String Format,
                    String Title_Downloading, String Discretion_Downloading)
    {
        if (Title_Downloading == null){
            Title_Downloading = "Salam Quran";
        }
        if (Discretion_Downloading == null){
            Discretion_Downloading = "Downloading...";
        }

        if (URL != null && FileName != null){
            File file = new File(context.getExternalFilesDir(null),Folder + FileName + Format);
            DownloadManager.Request request=new DownloadManager.Request(Uri.parse(URL))
                    .setTitle(Title_Downloading)// Title of the Download Notification
                    .setDescription(Discretion_Downloading)// Description of the Download Notification
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)// Visibility of the download Notification
                    .setDestinationUri(Uri.fromFile(file))// Uri of the destination file
//                .setRequiresCharging(false)// Set if charging is required to begin the download
                    .setAllowedOverMetered(true)// Set if download is allowed on Mobile network
                    .setAllowedOverRoaming(true);// Set if download is allowed on roaming network

            DownloadManager downloadManager= (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
            if (downloadManager != null) {
                downloadID = downloadManager.enqueue(request);
            }
        }

    }

    public static BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent ) {

            //Fetching the download id received with the broadcast
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            //Checking if the received broadcast is for our enqueued download by matching download id
            if (downloadID == id) {
                Toast.makeText(context, "Download Completed", Toast.LENGTH_SHORT).show();
            }
        }
    };


    public static void Aya(Context context,String url,String QariName,String sura,String aya){
        Download.File(context,url,"/"+QariName+"/"+sura+"/",aya, format.mp3,"در حال دانلود سوره" + sura, "با تلاوت " + QariName);

    }
    public static void Font(Context context,String url,String Type,String Page){
        Download.File(context,url,"/"+Type+"/",Page, format.ttf,"در حال دانلود فونت صفحه " + Page, "");

    }
}
