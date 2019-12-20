package com.ermile.salamquran.android.salamquran.Learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.ermile.salamquran.android.QuranApplication;
import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.api.LearnApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LearnActivity_level_list extends AppCompatActivity {

  private RecyclerView recyclerView;
  private ArrayList<LearnModel> mainModel;
  private ArrayList<LearnModel.level_list> level_listModel;
  private LearnAdapter adaptor;
  private LinearLayoutManager layoutManager;

  /*TryAgain*/
  private ProgressBar progressBar;
  private View viewTry;

  @Override
  protected void onResume() {
    super.onResume();
    ((QuranApplication) getApplication()).refreshLocale(this, true);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_level_list);

    recyclerView = findViewById(R.id.recycler_view);
    mainModel = new ArrayList<>();
    level_listModel = new ArrayList<>();
    adaptor = new LearnAdapter(getApplication(),mainModel,null,level_listModel);
    layoutManager =
        new LinearLayoutManager(getApplication(), RecyclerView.VERTICAL, false);
    recyclerView.setAdapter(adaptor);

    /*TryAgain*/
    progressBar = findViewById(R.id.progress);
    viewTry = findViewById(R.id.itemTryAgain);
    Button btnTry = viewTry.findViewById(R.id.btn_try_again);

    getItem();
    btnTry.setOnClickListener(v -> getItem());
  }

  private void getItem(){
    progressBar.setVisibility(View.VISIBLE);
    viewTry.setVisibility(View.GONE);

    String id = getIntent().getStringExtra("id");
    LearnApi.levelList(getApplication(), id, new LearnApi.levelListListener() {
      @Override
      public void onReceived(String Result) {
        progressBar.setVisibility(View.GONE);
        viewTry.setVisibility(View.GONE);

        addItem(Result);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adaptor.notifyDataSetChanged();
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
        mainModel.add(new LearnModel(LearnModel.LEVEL_LIST));
        level_listModel.add(new LearnModel.level_list(
            object.getString("id"),
            null,
            object.getString("title"),
            object.getString("desc"),
            object.getString("type"),
            object.getString("type_title"),
            object.getString("file"),
            object.getString("filepic"),
            null,
            null,
            null,
            null,
            null,
            null

        ));
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}
