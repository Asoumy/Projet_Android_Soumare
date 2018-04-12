package com.source.LOLSN;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.app.Notification;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.android.volley.RequestQueue;

import com.example.hsoumare.learnig_android.R;
import com.source.LOLSN.adapter.MyAdapter;
import com.source.LOLSN.entity.MatchEntity;
import com.source.LOLSN.request.ApiRequest;

import java.util.ArrayList;
import java.util.List;


public class HistoriqueActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private String playerName;
    private long playerId;
    private TextView test;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private ApiRequest request;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_historique );

//        queue = MySingleton.getInstance(this).getRequestQueue();
//        request = new ApiRequest(queue, this);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras ();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        setSupportActionBar(toolbar);

        if (extras.getString ( "NAME" ) != null && extras.getLong ( "ID" )>0){

            playerName=extras.getString ( "NAME" );
            playerId=extras.getLong ( "ID" );
            setTitle(playerName);
        }else{

        }
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close){

                    @Override
            public void onDrawerOpened(View drawerView) {
                                super.onDrawerOpened(drawerView);
                            }

            @Override
            public void onDrawerClosed(View drawerView) {
                                super.onDrawerClosed(drawerView);
                            }
        };

                mDrawerLayout.setDrawerListener(toggle);
                toggle.syncState();
                navigationView.setNavigationItemSelectedListener(this);

//        request.getHistoryMatches(65218394, new ApiRequest.HistoryCallback() {
//            @Override
//            public void onSuccess(List<MatchEntity> matches) {
//                                Log.d("APP", "MATCH = " + matches.toString());
//                            }
//
//            @Override
//            public void noMatch(String message) {
//                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//                            }
//
//            @Override
//            public void onError(String message) {
//                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//                            }
//        });
        recyclerView = (RecyclerView) findViewById(R.id.rv_match);

        List<String> data = new ArrayList<>();
        data.add("premiere donnée");
        data.add("seconde donnée");
        data.add("troisieme donnée");

        recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );
        recyclerView.setHasFixedSize ( true );
        mAdapter = new MyAdapter ( this,data );
        recyclerView.setAdapter ( mAdapter );

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

                if(menuItem.isChecked()){
                        menuItem.setChecked(false);
                    }else{
                        menuItem.setChecked(true);
                    }

                        mDrawerLayout.closeDrawers();

                        switch (menuItem.getItemId()){

                                case R.id.menu_principal :
                                Toast.makeText(getApplicationContext(), "Menu Principal", Toast.LENGTH_SHORT).show();
                                return true;
                        case R.id.rechercher :
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                                return true;
                        case R.id.stats :
                                Toast.makeText(getApplicationContext(), "Statistiques", Toast.LENGTH_SHORT).show();
                                return true;

                            }

                        return true;
            }

    }

