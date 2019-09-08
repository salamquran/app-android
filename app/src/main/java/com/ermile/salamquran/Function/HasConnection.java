package com.ermile.salamquran.Function;

import android.content.Context;

import java.io.IOException;

public class HasConnection {
    Context context;

    public Boolean HasConnection(Context context) {
        this.context = context;
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }

}