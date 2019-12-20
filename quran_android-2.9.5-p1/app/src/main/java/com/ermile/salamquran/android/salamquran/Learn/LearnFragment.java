package com.ermile.salamquran.android.salamquran.Learn;



import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.api.LearnApi;
import com.ermile.salamquran.android.salamquran.Utility.TempLoginUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearnFragment extends Fragment {

  private RecyclerView recyclerView;
  private ArrayList<LearnModel> mainModel;
  private ArrayList<LearnModel.group> groupModel;
  private LearnAdapter adaptor;
  private LinearLayoutManager layoutManager;

  /*TryAgain*/
  private ProgressBar progressBar;
  private View viewTry;

  public LearnFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    new TempLoginUtil(getContext());
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_learn, container, false);



    recyclerView = view.findViewById(R.id.recycler_view);
    mainModel = new ArrayList<>();
    groupModel = new ArrayList<>();
    adaptor = new LearnAdapter(getContext(),mainModel,groupModel,null);
    layoutManager =
        new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
    recyclerView.setAdapter(adaptor);


    /*TryAgain*/
    progressBar = view.findViewById(R.id.progress);
    viewTry = view.findViewById(R.id.itemTryAgain);
    Button btnTry = viewTry.findViewById(R.id.btn_try_again);

    getItem();
    btnTry.setOnClickListener(v -> getItem());

    return view;
  }

  private void getItem(){
    progressBar.setVisibility(View.VISIBLE);
    viewTry.setVisibility(View.GONE);
    LearnApi.group(getContext(), new LearnApi.groupListener() {
      @Override
      public void onReceived(String Result) {
        try {
          viewTry.setVisibility(View.GONE);
          progressBar.setVisibility(View.GONE);
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
        progressBar.setVisibility(View.GONE);
        viewTry.setVisibility(View.VISIBLE);
      }
    });
  }

  private void addItem(String Response){
    try {
      JSONArray array = new JSONArray(Response);
      for (int i = 0; i < array.length(); i++) {
        JSONObject object = array.getJSONObject(i);
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
