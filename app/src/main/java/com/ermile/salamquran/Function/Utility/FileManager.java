package com.ermile.salamquran.Function.Utility;

import android.os.Environment;
import android.widget.Toast;

import com.ermile.salamquran.Static.file;

import java.io.File;

public class FileManager {
    public static boolean findFile_storage(String DirectionFile,String fileName){
        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String pathDir = baseDir + "/"+ file.android_data_files + DirectionFile;
        File f = new File(pathDir + File.separator + fileName);
        return f.exists();
    }

    public static File getFile_storage(String DirectionFile,String fileName){
        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String pathDir = baseDir + "/"+ file.android_data_files + DirectionFile;
        File f = new File(pathDir + File.separator + fileName);
        return f ;
    }
}
