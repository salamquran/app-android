package com.ermile.salamquran.offline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.ermile.salamquran.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.lang.Character.toChars;

public class Main_offline extends AppCompatActivity {
    public static final String TAG = "Main_offline";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_offline);

        String p  = "&#xfb58;";

        TextView textView = findViewById(R.id.textview);
        textView.setText(Html.fromHtml(p).toString());


    }





}
