package com.salamquran.android.salamquran.Lms;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salamquran.android.R;
import com.salamquran.android.salamquran.api.LmsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearnFragment extends Fragment {

  private RecyclerView recyclerView;
  private List<LmsModel_group> model;
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
    layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
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
      for (int i = 0; i < 10; i++) {
        JSONObject object = array.getJSONObject(0);
        model.add(
            new LmsModel_group(
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
