package com.salamquran.android.salamquran;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.salamquran.android.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearnFragment extends Fragment {

  boolean errorUrl = true;
  boolean errorNet = false;
  Map<String, String> send_headers = new HashMap<>();
  private String URL = null;
  int oneStart = 0;

  SwipeRefreshLayout swipeRefreshLayout;
  WebView webView_object;


  private String mCM;
  private ValueCallback<Uri> mUM;
  private ValueCallback<Uri[]> mUMA;
  private final static int FCR = 1;
  private final static int FILECHOOSER_RESULTCODE = 1;

  public LearnFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_learn, container, false);

    requestAudioPermissions();

    send_headers.put("x-app-request", "android");

    swipeRefreshLayout = view.findViewById(R.id.swipRefresh_WebView);
    webView_object = view.findViewById(R.id.webView_WebView);
    WebSettings webSettings = webView_object.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webSettings.setAllowFileAccess(true);
    webSettings.setAllowFileAccess(true);
    webSettings.setAllowContentAccess(true);



    URL = "https://salamquran.com/fa/lms";

    if (URL != null){
      swipeRefreshLayout.setRefreshing(true);
      swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        @SuppressLint("NewApi")
        @Override
        public void onRefresh() {
          webView_object.loadUrl(webView_object.getUrl(), send_headers);
        }
      });
      webView_object.loadUrl(URL, send_headers);
      webView_object.setWebChromeClient(new WebChromeClient()
      {
        // For 3.0+ Devices (Start)
        // onActivityResult attached before constructor
        protected void openFileChooser(ValueCallback uploadMsg, String acceptType)
        {
          // Update message
          // Update message
          ValueCallback mUploadMessage = uploadMsg;

          try{

            // Create AndroidExampleFolder at sdcard

            File imageStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES)
                , "AndroidExampleFolder");

            if (!imageStorageDir.exists()) {
              // Create AndroidExampleFolder at sdcard
              imageStorageDir.mkdirs();
            }

            // Create camera captured image file path and name
            File file = new File(
                imageStorageDir + File.separator + "IMG_"
                    + String.valueOf(System.currentTimeMillis())
                    + ".jpg");

            Uri mCapturedImageURI = Uri.fromFile(file);

            // Camera capture image intent
            final Intent captureIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);

            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");

            // Create file chooser intent
            Intent chooserIntent = Intent.createChooser(i, "Image Chooser");

            // Set camera intent to file chooser
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS
                , new Parcelable[] { captureIntent });

            // On select image call onActivityResult method of activity
            startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE);

          }
          catch(Exception e){
          }

        }


        // For Lollipop 5.0+ Devices

        public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams)
        {
          // Double check that we don't have any existing callbacks
          ValueCallback<Uri[]> mFilePathCallback = null;
          if (mFilePathCallback != null) {
            mFilePathCallback.onReceiveValue(null);
          }
          mFilePathCallback = filePathCallback;

          Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

          if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            String mCameraPhotoPath = null;
            try {
              photoFile = createImageFile();
              takePictureIntent.putExtra("PhotoPath", mCameraPhotoPath);
            } catch (IOException ex) {
              // Error occurred while creating the File
              //Log.e(TAG, "Unable to create Image File", ex);
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
              mCameraPhotoPath = "file:" + photoFile.getAbsolutePath();
              takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                  Uri.fromFile(photoFile));
            } else {
              takePictureIntent = null;
            }
          }

          Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
          contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
          contentSelectionIntent.setType("image/*");
          contentSelectionIntent.putExtra("crop", "true");
          contentSelectionIntent.putExtra("scale", true);
          contentSelectionIntent.putExtra("outputX", 40);
          contentSelectionIntent.putExtra("outputY", 40);
          contentSelectionIntent.putExtra("aspectX", 1);
          contentSelectionIntent.putExtra("aspectY", 1);
          contentSelectionIntent.putExtra("return-data", true);

          Intent[] intentArray;
          if (takePictureIntent != null) {
            intentArray = new Intent[]{takePictureIntent};
          } else {
            intentArray = new Intent[0];
          }

          Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
          chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
          chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
          chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);


          startActivityForResult(chooserIntent, 1);

          return true;
        }

        //For Android 4.1 only
        protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)
        {
          ValueCallback<Uri> mUploadMessage = uploadMsg;
          Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
          intent.addCategory(Intent.CATEGORY_OPENABLE);
          intent.setType("image/*");
          startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
        }

        protected void openFileChooser(ValueCallback<Uri> uploadMsg)
        {
          ValueCallback<Uri> mUploadMessage = uploadMsg;
          Intent i = new Intent(Intent.ACTION_GET_CONTENT);
          i.addCategory(Intent.CATEGORY_OPENABLE);
          i.setType("image/*");
          startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
        }
      });
    }

    return view;
  }

  public void openFileChooser(ValueCallback<Uri> uploadMsg) {
    this.openFileChooser(uploadMsg, "*/*");
  }

  public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
    this.openFileChooser(uploadMsg, acceptType, null);
  }

  public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
    i.addCategory(Intent.CATEGORY_OPENABLE);
    i.setType("*/*");
    this.startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
  }


  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    super.onActivityResult(requestCode, resultCode, intent);
    if (Build.VERSION.SDK_INT >= 21) {
      Uri[] results = null;
      //Check if response is positive
      if (resultCode == RESULT_OK) {
        if (requestCode == FCR) {
          if (null == mUMA) {
            return;
          }
          if (intent == null) {
            //Capture Photo if no image available
            if (mCM != null) {
              results = new Uri[]{Uri.parse(mCM)};
            }
          } else {
            String dataString = intent.getDataString();
            if (dataString != null) {
              results = new Uri[]{Uri.parse(dataString)};
            }
          }
        }
      }
      mUMA.onReceiveValue(results);
      mUMA = null;
    } else {
      if (requestCode == FCR) {
        if (null == mUM) return;
        Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
        mUM.onReceiveValue(result);
        mUM = null;
      }
    }
  }

  // Create an image file
  private File createImageFile() throws IOException {
    @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String imageFileName = "img_" + timeStamp + "_";
    File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    return File.createTempFile(imageFileName, ".jpg", storageDir);


  }



  private void Dialog_WebView(boolean Cancelable) {
    errorNet = true;
    webView_object.setVisibility(View.INVISIBLE);
    final AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
    /*Title*/
    builderSingle.setTitle("Dialog_WebView");
    /*Message*/
    builderSingle.setMessage("");
    /*Button*/
    builderSingle.setPositiveButton("Dialog_WebView",
        /*Open Url*/
        new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            swipeRefreshLayout.setRefreshing(true);
            webView_object.reload();
            errorNet = false;
            oneStart = 0;

          }
        });

    builderSingle.setNeutralButton("Dialog_WebView", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
      }
    });
    builderSingle.setCancelable(Cancelable);
    builderSingle.show();
  }


  private final int MY_PERMISSIONS_RECORD_AUDIO = 1;

  private void requestAudioPermissions() {
    if (ContextCompat.checkSelfPermission(getContext(),
        Manifest.permission.RECORD_AUDIO)
        != PackageManager.PERMISSION_GRANTED) {

      //When permission is not granted by user, show them message why this permission is needed.
      if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
          Manifest.permission.RECORD_AUDIO)) {
        Toast.makeText(getContext(), "Please grant permissions to record audio", Toast.LENGTH_LONG).show();

        //Give user option to still opt-in the permissions
        ActivityCompat.requestPermissions(getActivity(),
            new String[]{Manifest.permission.RECORD_AUDIO},
            MY_PERMISSIONS_RECORD_AUDIO);

      } else {
        // Show user dialog to grant permission to record audio
        ActivityCompat.requestPermissions(getActivity(),
            new String[]{Manifest.permission.RECORD_AUDIO},
            MY_PERMISSIONS_RECORD_AUDIO);
      }
    }
    //If permission is granted, then go ahead recording audio
    else if (ContextCompat.checkSelfPermission(getContext(),
        Manifest.permission.RECORD_AUDIO)
        == PackageManager.PERMISSION_GRANTED) {
    }
  }

  //Handling callback
  @Override
  public void onRequestPermissionsResult(int requestCode,
                                         String permissions[], int[] grantResults) {
    switch (requestCode) {
      case MY_PERMISSIONS_RECORD_AUDIO: {
        if (grantResults.length > 0
            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          // permission was granted, yay!
        } else {
          // permission denied, boo! Disable the
          // functionality that depends on this permission.
          Toast.makeText(getContext(), "Permissions Denied to record audio", Toast.LENGTH_LONG).show();
        }
        return;
      }
    }
  }


}
