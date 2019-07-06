/*
package com.ermile.salamquran.json_maker;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ermile.salamquran.item.dialog_language.MainItem;
import com.ermile.salamquran.item.dialog_language.Result;

import org.json.JSONException;
import org.json.JSONObject;

public class dialog_language {


    private Requesthandler requesthandler;


    private dialog_language(Requesthandler requesthandler) {
        this.requesthandler = requesthandler;
    }

    public static dialog_language getRequestMaker(Requesthandler requesthandler) {
        return new dialog_language(requesthandler);
    }

    public void makeAGetRequest(String url) {
        // JSON
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject responses) {


                try {

                    MainItem mainItem = new MainItem();
                    mainItem.setOk(responses.getBoolean("ok"));

                    if (mainItem.getOk()) {
                        Result result
                                = new Result();
                        JSONObject resultObject = responses.getJSONObject("result");
                        result.setTitle(resultObject.getString("title"));
                        result.setContent(resultObject.getString("content"));
                        mainItem.setResult(result);
                        requesthandler.onResponse(mainItem);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requesthandler.onError(error);
//                ERROR_GETING();
            }


        });
        AppContoroler.getInstance().addToRequestQueue(req);
    }


    interface Requesthandler {
        void onResponse(MainItem responses);

        void onError(VolleyError error);
    }
}

public class dialog_language {
}
*/
