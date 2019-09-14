package com.ermile.salamquran.Function.Utility;

import android.content.Context;
import android.util.Log;

import com.ermile.salamquran.Static.tag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadFile {
    public static String FromStorage(Context context,String filename , String format) throws IOException {
        File file = new File(context.getFilesDir(), filename+format);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder text = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null) {
            text.append(line);
        }
        bufferedReader.close();
        Log.d(tag.FileManager, "ReadFile: "+text);
        return text.toString();

    }

    public static String FromAsset(Context context,String langName,String format, String CbarsetName) {
        try {
            InputStream file= context.getAssets().open(langName+format);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file, CbarsetName));
            String line;
            StringBuilder text = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line);
            }
            Log.d(tag.FileManager, "LoadFromAsset: Value: \n" +text.toString());
            bufferedReader.close();
            return text.toString();
        } catch (IOException ex) {
            Log.e(tag.FileManager, "LoadFromAsset: ",ex );
            Log.e(tag.error, "LoadFromAsset: ",ex );
            ex.printStackTrace();
            return null;
        }
    }
}
