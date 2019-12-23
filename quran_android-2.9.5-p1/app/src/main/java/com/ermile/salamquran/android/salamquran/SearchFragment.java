package com.ermile.salamquran.android.salamquran;


import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.ermile.salamquran.android.QuranApplication;
import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.SearchActivity;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

  SearchView searchView;
  SearchManager searchManager;

  public SearchFragment() {
    // Required empty public constructor
  }

  @Override
  public void onResume() {
    super.onResume();
    ((QuranApplication)
        Objects.requireNonNull(getActivity()).getApplication())
        .refreshLocale(Objects.requireNonNull(getContext()), true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_search, container, false);



    searchView = view.findViewById(R.id.searchView);

/*    int id = searchView
        .getContext()
        .getResources()
        .getIdentifier("android:id/search_src_text", null, null);
    TextView textView = (TextView) searchView.findViewById(id);
//    textView.setTextColor(Color.WHITE);
    Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"dana_regular.ttf");
    textView.setTypeface(typeface);*/

    searchManager = (SearchManager)
        Objects.requireNonNull(getActivity()).getSystemService(Context.SEARCH_SERVICE);
    searchView.setSearchableInfo(searchManager.getSearchableInfo(
        new ComponentName(getActivity().getApplication(), SearchActivity.class)));

    searchView.setQueryHint(getString(R.string.search_hint));
    s();
    view.findViewById(R.id.box_search).setOnClickListener(v -> s());


    return view;
  }

  private void s(){
    searchView.setQuery(null,false);
    searchView.setFocusable(true);
    searchView.setIconified(false);
    searchView.requestFocusFromTouch();
  }
}
