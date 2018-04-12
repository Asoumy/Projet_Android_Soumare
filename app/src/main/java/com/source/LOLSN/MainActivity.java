package com.source.LOLSN;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.hsoumare.learnig_android.R;
import com.source.LOLSN.request.ApiRequest;


public class MainActivity extends AppCompatActivity {

    EditText etSummuner;
    Button btn_search;
    ProgressBar pb_Loader;
    ListView lvRecent;
    private RequestQueue queue;
    private ApiRequest request;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        Intent intent = new Intent ( this, LoginActivity.class );
        startActivity ( intent );


        queue = MySingleton.getInstance (this).getRequestQueue ();
        request = new ApiRequest (queue, this  );

        request = new ApiRequest ( queue, this );
        handler =new Handler (  );

        etSummuner = (EditText) findViewById ( R.id.input_Summoner );
        btn_search = (Button) findViewById ( R.id.btn_search );
        pb_Loader = (ProgressBar) findViewById ( R.id.pb_search );
        lvRecent = (ListView) findViewById ( R.id.lv_recent );

        btn_search.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                final String search= etSummuner.getText().toString ().trim ();

                    if(search.length () > 0){

                        pb_Loader.setVisibility ( View.VISIBLE );
                        handler.postDelayed ( new Runnable () {
                            @Override
                            public void run() {

                                request.checkPlayerName ( search, new ApiRequest.CheckPlayerCallback () {
                                    @Override
                                    public void onSuccess(String name, long id) {

                                        pb_Loader.setVisibility ( View.INVISIBLE );
                                        Intent intent = new Intent ( getApplicationContext (),HistoriqueActivity.class );
                                        Bundle extras = new Bundle ();
                                        extras.putString ( "NAME", name);
                                        extras.putLong ( "ID", id);
                                        intent.putExtras ( extras );
                                        startActivity ( intent );
                                    }

                                    @Override
                                    public void dontExist(String message) {

                                        pb_Loader.setVisibility ( View.INVISIBLE );
                                        Toast.makeText ( getApplicationContext (),message,Toast.LENGTH_SHORT).show ();
                                    }

                                    @Override
                                    public void onError(String message) {

                                        pb_Loader.setVisibility ( View.INVISIBLE );
                                        Toast.makeText ( getApplicationContext (),message,Toast.LENGTH_SHORT).show ();
                                    }
                                } );


                            }
                        } ,2000);


                    }else {
                        Toast.makeText ( getApplicationContext(), "you must tape a summuner name",Toast.LENGTH_SHORT).show ();
                    }
            }
        } );

    }
}


