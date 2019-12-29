package com.ermile.salamquran.android.salamquran.Mag;


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

import com.ermile.salamquran.android.QuranApplication;
import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.salamquran.Utility.TempLoginUtil;
import com.ermile.salamquran.android.salamquran.api.MagApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class  MagFragment extends Fragment {
  private RecyclerView recyclerView;
  private ArrayList<MagModel.list_long> model;
  private MagAdapter adaptor;
  private LinearLayoutManager layoutManager;

  /*TryAgain*/
  private ProgressBar progressBar;
  private View viewTry;

  @Override
  public void onResume() {
    super.onResume();
    ((QuranApplication)
        Objects.requireNonNull(getActivity()).getApplication())
        .refreshLocale(Objects.requireNonNull(getContext()), true);
  }

  public MagFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    new TempLoginUtil(getContext());
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_learn, container, false);

    recyclerView = view.findViewById(R.id.recycler_view);
    model = new ArrayList<>();
    adaptor = new MagAdapter(getContext(),model);
    layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
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
    MagApi.newsList(getContext(),50, new MagApi.magListListener() {
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
      String image, gallery_array, source_title, source_url;

      JSONArray array = new JSONArray(Response);
      for (int i = 0; i < array.length(); i++) {

        image = null;
        gallery_array = null;
        source_title = null;
        source_url = null;

        JSONObject object = array.getJSONObject(i);

        if (!object.isNull("meta")){
          JSONObject mata = object.getJSONObject("meta");

          if (!mata.isNull("source")){
            JSONObject source = mata.getJSONObject("source");
            if (!source.isNull("title")){
              source_title = source.getString("title");
            }
            if (!source.isNull("Url")){
              source_url = source.getString("Url");
            }
          }

          if (!mata.isNull("thumb")){
            image = mata.getString("thumb");
          }

          try {
            if (!mata.isNull("gallery")){
              JSONArray gallery = mata.getJSONArray("gallery");
              gallery_array = String.valueOf(gallery);
            }
          }catch (Exception e){
            e.printStackTrace();
          }

        }
        model.add(
            new MagModel.list_long(
                object.getString("id"),
                object.getString("title"),
                object.getString("excerpt"),
                object.getString("subtitle"),
                object.getString("content"),
                source_title,
                source_url,
                image,
                object.getString("special"),
                gallery_array
            ));
      }

        /*
  *
  {
  "id": "4S",
  "title": "ترجمه‌های غیرحرفه‌ای به تحریف قرآن می‌انجامد",
  "link": "https://salamquran.com/fa/news/ابوالفضل-بهرام-پور-ترجمه-های-غیرحرفه-ای-به-تحریف-قرآن-می-انجامد",
  "excerpt": "حریف می‌انجامد.",
  "subtitle": "ابوالفضل ب",
  "content": "ابوالفضل م‌پور",
  "meta": {
  	"source": {
		"title": "خبرگذاری ایکنا",
		"Url": null
	  },
    "thumb": "https://salamquran.com/files/1/180-73c70e9ee92dd6819093c5efe62727bd.jpg",
    "gallery": [
		"https://salamquran.com/files/1/121-8f4eabc982f82b81d3f4740333923f67.png",
		"https://salamquran.com/files/1/122-f4482ae64ef6fda1ac485e35e0473dfe.png",
		"https://salamquran.com/files/1/123-4e0a30231c3b945f2dfd8a28fb6cd9fe.png",
		"https://salamquran.com/files/1/124-2a6e0ca583ba6f257c0143fbf4185a56.png"
	]
  },
  "special": "editor",
}
*/
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

}
