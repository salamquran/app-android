package com.salamquran.android.salamquran.Lms;


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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import com.salamquran.android.salamquran.api.LmsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearnFragment extends Fragment {

  private RecyclerView recyclerView;
  private List<LmsModel> model;
  private LmsAdaptor adaptor;
  private LinearLayoutManager layoutManager;

  public LearnFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_learn, container, false);
    recyclerView = view.findViewById(R.id.recycler_view);
    model = new ArrayList<>();
    adaptor = new LmsAdaptor(getContext(),model);
    layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
    recyclerView.setAdapter(adaptor);


    LmsApi.group(getContext(), new LmsApi.groupListener() {
      @Override
      public void onReceived(String Result) {
        try {
          addItem(Result);
          recyclerView.setLayoutManager(layoutManager);
          recyclerView.setItemAnimator(new DefaultItemAnimator());
          adaptor.notifyDataSetChanged();
        }catch (Exception e){
          e.printStackTrace();
        }

      }

      @Override
      public void onFiled() {

      }
    });


    return view;
  }

  private void addItem(String Response){
    try {
      JSONArray array = new JSONArray(Response);
      for (int i = 0; i < array.length(); i++) {
        JSONObject object = array.getJSONObject(i);
        model.add(
            new LmsModel(
                object.getString("id"),
                object.getString("file"),
                object.getString("title"),
                object.getString("desc"),
                object.getString("type"),
                object.getString("type_title"),
                object.getString("level_count")
            ));
        model.add(
            new LmsModel(
                object.getString("id"),
                object.getString("file"),
                object.getString("title"),
                object.getString("desc"),
                object.getString("type"),
                object.getString("type_title"),
                object.getString("level_count")
            ));
        model.add(
            new LmsModel(
                object.getString("id"),
                object.getString("file"),
                object.getString("title"),
                object.getString("desc"),
                object.getString("type"),
                object.getString("type_title"),
                object.getString("level_count")
            ));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

}
