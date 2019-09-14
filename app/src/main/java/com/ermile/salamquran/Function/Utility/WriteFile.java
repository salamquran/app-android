package com.ermile.salamquran.Function.Utility;


import android.content.Context;
import android.util.Log;

import com.ermile.salamquran.Static.tag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile {

    public WriteFile(Context context, String FileName, String Format, String Values) {
        File file = new File(context.getFilesDir(), FileName+Format);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(FileName+Format, Context.MODE_PRIVATE);
            fileOutputStream.write(Values.getBytes());
            fileOutputStream.close();
            Log.d(tag.FileManager, "WriteFile: File: " + file + "\n value: "+Values);
            Log.d(tag.function, "WriteFile: File: " + file + "\n value: "+Values);
            Log.d(tag.ac_Splash, "WriteFile: File: " + file + "\n value: "+Values);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e(tag.error, "WriteFile: ",e );
            Log.e(tag.FileManager, "WriteFile: ",e );
            Log.e(tag.function, "WriteFile: ",e );
            Log.e(tag.ac_Splash, "WriteFile: ",e );
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(tag.error, "WriteFile: ",e );
            Log.e(tag.FileManager, "WriteFile: ",e );
            Log.e(tag.function, "WriteFile: ",e );
            Log.e(tag.ac_Splash, "WriteFile: ",e );
        }
    }
}
