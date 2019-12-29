package com.ermile.salamquran.android.salamquran;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.Nullable;

import com.ermile.salamquran.android.BuildConfig;
import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.Utility.SaveManager;
import com.ermile.salamquran.android.util.QuranSettings;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFirstFile {
  private static final String QURAN_BASE = "salam_quran/";
  private Boolean isUnziping = false;
  private Context context;

  public UnzipFirstFile(Context context) {
    this.context = context;
    isUnziping = SaveManager.get(context).getBoolValue().get(SaveManager.unZipFileReq);
    if (!isUnziping){
      unzip();
      SaveManager.get(context).save_status_unzip_file_rq(true);
    }
  }
  private void unzip(){
    try {
      BufferedOutputStream dest = null;
      ZipInputStream zis = new ZipInputStream(context.getResources().openRawResource(R.raw.archive));
      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        File file = new File(getQuranBaseDirectory() + entry.getName());
        if (file.exists()) {
          Log.d("amingol", "extractZipfile: "+file.getAbsolutePath());
          continue;
        }
        if (entry.isDirectory()) {
          if (!file.exists())
            file.mkdirs();
          Log.d("amingol", "extractZipfile: Create directory: "+file.getAbsolutePath());
          continue;
        }
        Log.d("amingol", "extractZipfile: nExtracting: "+entry);
        int count;
        byte data[] = new byte[2048];
        Log.d("amingol", "extractZipfile: to: "+file.getAbsolutePath());
        FileOutputStream fos = new FileOutputStream(file);
        dest = new BufferedOutputStream(fos, 2048);
        while ((count = zis.read(data, 0, 2048)) != -1) {
          dest.write(data, 0, count);
        }
        dest.flush();
        dest.close();
      }
      zis.close();
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      Log.e("amingoli", "extractZipfile: ",e );
    }
  }

  @Nullable
  private String getQuranBaseDirectory() {
    return getQuranBaseDirectory(context);
  }

  @Nullable
  private String getQuranBaseDirectory(Context context) {
    String basePath = QuranSettings.getInstance(context).getAppCustomLocation();

    if (!isSDCardMounted()) {
      // if our best guess suggests that we won't have access to the data due to the sdcard not
      // being mounted, then set the base path to null for now.
      if (basePath == null || basePath.equals(
          Environment.getExternalStorageDirectory().getAbsolutePath()) ||
          (basePath.contains(BuildConfig.APPLICATION_ID) && context.getExternalFilesDir(null) == null)) {
        basePath = null;
      }
    }

    if (basePath != null) {
      if (!basePath.endsWith(File.separator)) {
        basePath += File.separator;
      }
      return basePath + QURAN_BASE;
    }
    return null;
  }

  private boolean isSDCardMounted() {
    String state = Environment.getExternalStorageState();
    return state.equals(Environment.MEDIA_MOUNTED);
  }

}
