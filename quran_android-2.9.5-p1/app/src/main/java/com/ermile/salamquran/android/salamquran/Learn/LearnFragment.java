package com.ermile.salamquran.android.salamquran.Learn;



import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.api.LearnApi;

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
  private ArrayList<LearnModel> mainModel;
  private ArrayList<LearnModel.group> groupModel;
  private LearnAdaptor adaptor;
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
    mainModel = new ArrayList<>();
    groupModel = new ArrayList<>();
    adaptor = new LearnAdaptor(getContext(),mainModel,groupModel,null);
    layoutManager =
        new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
    recyclerView.setAdapter(adaptor);


    LearnApi.group(getContext(), new LearnApi.groupListener() {
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
        mainModel.add(new LearnModel(LearnModel.GROUP));
        groupModel.add(new LearnModel.group(
                object.getString("id"),
                object.getString("file"),
                object.getString("title"),
                object.getString("desc"),
                object.getString("type"),
                object.getString("type_title")
            ));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

}
