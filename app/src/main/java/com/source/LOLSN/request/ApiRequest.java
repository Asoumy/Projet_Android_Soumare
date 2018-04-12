package com.source.LOLSN.request;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.source.LOLSN.entity.MatchEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by soums on 01/04/2018.
 */

public class ApiRequest {

    private RequestQueue queue;
    private Context context;
    private static final String API_KEY ="RGAPI-729ada4b-2dc6-4e1f-8a56-5bf96dda4973";
    private String region = "euw1";


    public ApiRequest(RequestQueue queue, Context context)
    {
        this.queue = queue;
        this.context=context;
    }

    public void checkPlayerName (final String name, final CheckPlayerCallback callback)
    {
        String url ="https://"+region+".api.riotgames.com/lol/summoner/v3/summoners/by-name/"+name+"?api_key="+API_KEY;
        JsonObjectRequest request = new JsonObjectRequest ( Request.Method.GET, url, new Response.Listener <JSONObject> () {

            @Override
            public void onResponse(JSONObject response) {

                Log.d ( "APP" , response.toString ());
                try {
//                    JSONObject json = response.getJSONObject ( name.toLowerCase () );
                    String name =response.getString( "name".toLowerCase () );
                    long id = response.getLong ( "id" );
                    long profileIconId = response.getLong ( "profileIconId" );
                    long summonerLevel = response.getLong ( "summonerLevel" );
                    long accountId = response.getLong ( "accountId" );
                    long revisionDate = response.getLong ( "revisionDate" );
                    callback.onSuccess ( name,id,profileIconId,summonerLevel, accountId,revisionDate);
                } catch (JSONException e) {
                    Log.d ( "APP","EXCEPTION ="+e);
                    e.printStackTrace ();
                }

            }
        }, new Response.ErrorListener () {

            @Override
            public void onErrorResponse(VolleyError error) {

                if(error instanceof NetworkError){

                    callback.onError ( "Unabled to connect" );
                }else if(error instanceof ServerError){
                    callback.dontExist ( "Player not exist" );
                }
                Log.d ( "APP" ,"ERROR = "+error);

            }
        } );

        queue.add ( request );
    }
    public interface CheckPlayerCallback{

        void onSuccess(String name,long id,long profileIconId,long summonerLevel, long accountId,long revisionDate);
        void dontExist(String message);
        void onError(String message);
    }

    public String getJsonFile(Context context, String filename){

        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return json;
    }

}
