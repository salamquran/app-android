package com.ermile.salamquran.Functions;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import com.ermile.salamquran.Value.filePath;
import com.ermile.salamquran.Value.tag;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileManager {
    /**
     * Read File
     */
    public static String readFile(File file) throws IOException {
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

    public static String read_FromStorage(Context context, String filename , String format) throws IOException {
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

    public static String read_FromAsset(Context context,String langName,String format, String CbarsetName) {
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

    /**
     * Write File
     */
    public static void write_InStorage(String Folder , String FileName, String Format, String Values) {
        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String pathDir = baseDir + "/"+ filePath.android_data_files + Folder;
        try {
            File file = new File(pathDir);
            if (!file.exists()) {
                file.mkdirs();
            }
            File gpxfile = new File(file, FileName+Format);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(Values);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write_OutStorage(Context context, String FileName, String Format, String Values) {
        File file = new File(context.getFilesDir(), FileName+Format);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(FileName+Format, Context.MODE_PRIVATE);
            fileOutputStream.write(Values.getBytes());
            fileOutputStream.close();
            Log.d(tag.FileManager, "WriteFile: File: " + file + "\n value: "+Values);
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

    /**
     * Find & Get File
     */
    public static boolean findFile_storage(String DirectionFile,String fileName){
        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String pathDir = baseDir + "/"+ filePath.android_data_files + DirectionFile;
        if (DirectionFile == null){
            pathDir = baseDir + "/"+ filePath.android_data_files;
        }
        File file = new File(pathDir + File.separator + fileName);
        return file.exists();
    }

    public static File getFile_storage(String DirectionFile,String fileName){
        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String pathDir = baseDir + "/"+ filePath.android_data_files + DirectionFile;
        if (DirectionFile == null){
            pathDir = baseDir + "/"+ filePath.android_data_files;
        }
        return new File(pathDir + File.separator + fileName);
    }

    /**
     * Unzipping File
     */
    public static void UnZip_FromAssets(Context context, String FileName, String Foramt) {
        String File_Name = FileName+Foramt;
        AssetManager manager = context.getAssets();
        final String OUTPUT_FOLDER = Objects.requireNonNull(context.getExternalFilesDir(null)).getAbsolutePath();
        // create output directory if not exists
        String[] fileNameSplit = File_Name.split("/");
        File folder = new File(OUTPUT_FOLDER + "/" + fileNameSplit[0]);
        if (!folder.exists()) {
            folder.mkdir();
        }
        try {
            // get the zip file content
            BufferedInputStream bis = new BufferedInputStream(manager.open(File_Name));
            ZipInputStream zis = new ZipInputStream(bis);
            // get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileEntry = ze.getName();
                File newFile = new File(OUTPUT_FOLDER + File.separator + fileEntry);
                // create all non existent folders from zip archive
                if (fileEntry.endsWith("/")) {
                    // new File(newFile.getParent()).mkdirs();
                    newFile.mkdirs();
                }
                File parent = newFile.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }
                // FileOutputStream fos = new FileOutputStream(newFile);
                FileOutputStream fos = context.openFileOutput(newFile.getName(),
                        0);
                byte[] buffer = new byte[4096];
                int length = 0;
                while ((length = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                    fos.flush();
                }
                fos.close();
                ze = zis.getNextEntry();
            }
            // close open resources
            bis.close();
            zis.closeEntry();
            zis.close();
        } catch (IOException ex) {
            Log.e(tag.FileManager, "UnZipFile: ",ex );
            Log.e(tag.error, "UnZipFile: ",ex );
        }
    }




}
