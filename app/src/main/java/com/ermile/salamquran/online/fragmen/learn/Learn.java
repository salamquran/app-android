package com.ermile.salamquran.online.fragmen.learn;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.ermile.salamquran.R;
import com.ermile.salamquran.saveData.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Learn extends Fragment {
    WebView webView;
    SwipeRefreshLayout swipeRefresh_learn;

    public Learn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment_learn =  inflater.inflate(R.layout.fragment_learn, container, false);
        webView = fragment_learn.findViewById(R.id.webView_learn);


        final Map<String, String> send_headers = new HashMap<String, String>();
        send_headers.put("x-app-request", "android");

        swipeRefresh_learn = fragment_learn.findViewById(R.id.swipeRefresh_learn);

        /*swipe Refresh*/
        swipeRefresh_learn.setRefreshing(true);
        swipeRefresh_learn.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.loadUrl(webView.getUrl(),send_headers);
            }
        });


        /*Web View*/
        WebSettings webViewSettings = webView.getSettings();
        webViewSettings.setJavaScriptEnabled(true); /* set JavaScript for webview*/

        webView.loadUrl(Value.url_learn,send_headers);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                Toast.makeText(getContext(), "Error page Load !", Toast.LENGTH_SHORT).show();
            }
            // in refresh send header
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                HashMap<String, String> headerMap = new HashMap<>();
                headerMap.put("x-app-request", "android");
                view.loadUrl(url, headerMap);


                return false;

            }
            /*swipe Refresh*/
            @Override
            public void onPageFinished(WebView view, String url) {
                swipeRefresh_learn.setRefreshing(false);
            }});


        return fragment_learn;
    }

}
