package com.ermile.salamquran.Function.FileManager;

import android.content.Context;
import android.util.Log;

import com.ermile.salamquran.Static.tag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    Context context;

    public String ReadFile(Context context,String filename , String format) throws IOException {
        this.context = context;
        File file = new File(context.getFilesDir(), filename+format);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder text = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null) {
            text.append(line);
        }
        bufferedReader.close();
        Log.d(tag.function, "ReadFile: "+text);
        Log.d(tag.FileManager, "ReadFile: "+text);
        Log.d(tag.ac_Splash, "ReadFile: "+text);
        return text.toString();

    }
}
