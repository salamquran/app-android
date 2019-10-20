package com.ermile.salamquran.Function.Utility;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import com.ermile.salamquran.Static.filePath;
import com.ermile.salamquran.Static.tag;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileManager {
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


    public static void UnZipFile(Context context, String FileName, String Foramt) {
        String File_Name = FileName+Foramt;
        AssetManager manager = context.getAssets();
        final String OUTPUT_FOLDER = context.getExternalFilesDir(null).getAbsolutePath();
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


    public static void WriteFile(Context context, String Folder , String FileName, String Format, String Values) {
        try {
            File file = new File(context.getExternalFilesDir(null),   FileName+Format);
            FileOutputStream fileOutput = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutput);
            outputStreamWriter.write(Values);
            outputStreamWriter.flush();
            fileOutput.getFD().sync();
            outputStreamWriter.close();
            Log.d(tag.FileManager, "WriteFile: FileOutputStream: " + file + "\n value: "+Values);
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


    public static String ReadString(File file) throws IOException {
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
}