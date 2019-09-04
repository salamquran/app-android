package com.ermile.salamquran.Function.FileManager;


import android.content.Context;
import android.util.Log;

import com.ermile.salamquran.Static.tag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.content.Context.MODE_PRIVATE;

public class WriteFile {
    Context context;
    public WriteFile(Context context, String FileName, String Format, String Value) {
        this.context = context;
        File file = new File(context.getFilesDir(), FileName+Format);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = context.openFileOutput(FileName+Format, MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.write(Value.getBytes());
            objectOutputStream.close();
            Log.d(tag.FileManager, "WriteFile: File: " + file + "\n value: "+Value);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(tag.error, "WriteFile: ",e );
            Log.e(tag.FileManager, "WriteFile: ",e );
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(tag.error, "WriteFile: ",e );
            Log.e(tag.FileManager, "WriteFile: ",e );
        }
    }
}
