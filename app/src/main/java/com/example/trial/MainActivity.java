package com.example.trial;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.Menu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Example_Adapter.onItemClickListener {

    public static final String EXTRA_URL="imageUrl";
    public static final String EXTRA_CREATOR="creatorName";
    public static final String EXTRA_LIKES="LikeCount";
    private RecyclerView recyclerView;
    private Example_Adapter adapter;
    private ArrayList<ExampleItem>mExampleList;
    private RequestQueue mRequestQueue;
    private LinearLayoutManager layoutManager;






    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);




        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getApplicationContext());


        recyclerView.setLayoutManager(layoutManager);

        mExampleList=new ArrayList<>();
        //mRequestQueue= Volley.newRequestQueue(this);
        // parseJSON();



       /* swipeRefreshLayout=findViewById(R.id.swipeContainer);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                parseJSON();

                swipeRefreshLayout.setRefreshing(false);
            }
        });*/

        mExampleList.add(new ExampleItem("agpO",1,R.drawable.agpo));
        mExampleList.add(new ExampleItem("E-Citizen",1,R.drawable.ecitizen));
        mExampleList.add(new ExampleItem("Women Enterprise Fund",1,R.drawable.wef));
        mExampleList.add(new ExampleItem("UWEZO",1,R.drawable.uwezo));
        mExampleList.add(new ExampleItem("KRA",1,R.drawable.kra));
        mExampleList.add(new ExampleItem("Youth Enterprise Fund",1,R.drawable.yef));
        adapter=new Example_Adapter(MainActivity.this,mExampleList);
        recyclerView.setAdapter(adapter);
        //add onscroll listener to the recycler view
        recyclerView.addOnScrollListener(prOnScrollListener);

        adapter.setOnItemClickListener(MainActivity.this);

    }

   /* private void parseJSON(){
        String url="https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray=response.getJSONArray("hits");

                            for(int i=0; i<jsonArray.length();i++){
                                JSONObject hit=jsonArray.getJSONObject(i);

                                String mImageUrl=hit.getString("webformatURL");
                                String mCreator=hit.getString("user");
                                int mLikes=hit.getInt("likes");

                                mExampleList.add(new ExampleItem(mCreator,mLikes,mImageUrl));
                            }



                            adapter=new Example_Adapter(MainActivity.this,mExampleList);
                            recyclerView.setAdapter(adapter);

                            //add onscroll listener to the recycler view
                            recyclerView.addOnScrollListener(prOnScrollListener);

                            adapter.setOnItemClickListener(MainActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }*/

   private RecyclerView.OnScrollListener prOnScrollListener=new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();


            if (islastItemDisplaying(recyclerView)){
                //Log.i("ExampleItem","LoadMore");
                ArrayList<ExampleItem>mExampleList;

            }
        }


    };

    private boolean islastItemDisplaying(RecyclerView recyclerView) {
        //check if the adapter item count is greater than 0
        if (recyclerView.getAdapter().getItemCount()!=0){
            //get the last visible item on screen using the layoutmanager
            int lastVisibleItemPosition=((LinearLayoutManager)recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            //apply some logic here
            if (lastVisibleItemPosition!=RecyclerView.NO_POSITION && lastVisibleItemPosition==recyclerView.getAdapter().getItemCount()-1){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(int i) {
        Intent detailIntent=new Intent(this, DetailActivity.class);
        ExampleItem clickedItem=mExampleList.get(i);
        detailIntent.putExtra(EXTRA_URL,clickedItem.getmImageUrl());
        detailIntent.putExtra(EXTRA_CREATOR, clickedItem.getmCreator());
        detailIntent.putExtra(EXTRA_LIKES,clickedItem.getmLikes());

        startActivity(detailIntent);
    }
}
