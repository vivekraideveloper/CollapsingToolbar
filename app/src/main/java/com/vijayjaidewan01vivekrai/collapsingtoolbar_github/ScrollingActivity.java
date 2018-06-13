package com.vijayjaidewan01vivekrai.collapsingtoolbar_github;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Adapters.Card1Adapter;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Adapters.CardAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Toolbar mToolbar;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private AppBarLayout appBarLayout;
    private View layout;
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private NestedScrollView nestedScrollView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshLayout swipeRefreshLayoutCoordinator;
    private String url = "http://bydegreestest.agnitioworld.com/test/slider.php";
    private HashMap<String, String> hashMap;
    private ArrayList<HashMap<String, String>> arrayList;
    private NavigationView navigationView;
    List<CardData> cardDataList;
    RecyclerView.Adapter adapter;
    int collapseValue = 2;
    int searchValue = 2;
    int drawerValue = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        linearLayout = findViewById(R.id.linear_layout);
        appBarLayout = findViewById(R.id.app_bar);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        nestedScrollView = findViewById(R.id.scrollView);
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayoutCoordinator = findViewById(R.id.swipe_coordinator);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        mToolbar = findViewById(R.id.tool_bar);

        cardDataList = new ArrayList<>();
        arrayList = new ArrayList<>();


        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            // notify user you are online

            setCollapse(collapseValue);


        } else if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {
            // notify user you are not online

            View view = findViewById(android.R.id.content);
            Snackbar snackbar = Snackbar.make(view, "Please ensure stable internet connectivity!", 7000).setAction("Action", null);
            snackbar.show();

            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.drawer_layout, new Offline_fragment());
            ft.commit();

        }


    }

    public void setAdapter() {
        adapter = new Card1Adapter(cardDataList, ScrollingActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setAdapter(int pos) {
        adapter = new CardAdapter(cardDataList, ScrollingActivity.this, pos);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    //Search Bar controlled by searchValue = 0(Not Present), 1(Present)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        MenuInflater inflater = getMenuInflater();

        if (searchValue == 1) {
            inflater.inflate(R.menu.search_layout, menu);
            MenuItem item = menu.findItem(R.id.search_view);
            SearchView searchView = (SearchView) item.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (drawerValue == 1){
            if (toggle.onOptionsItemSelected(item)) {
                return true;
            }
        }

        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    public void setRecyclerViewMargins()                    // Setting the margins of Recycler View while the toolbar is collapsed to remove the empty space in between the toolbar and recycler view
    {
        //ppBar = findViewById(R.id.appBar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    //Log.d("appbar",""+recyclerView.getScaleY());
                    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) swipeRefreshLayoutCoordinator.getLayoutParams(); // Redundant Code with line 119
                    layoutParams.setMargins(0, 0, 0, 0);
                    swipeRefreshLayoutCoordinator.setLayoutParams(layoutParams);
                    //Animate the scrolling
                } else if (Math.abs(verticalOffset) == 0) {
                    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) swipeRefreshLayoutCoordinator.getLayoutParams();
                    layoutParams.setMargins(0, 150, 0, 0);
                    swipeRefreshLayoutCoordinator.setLayoutParams(layoutParams);

                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    private void setCollapse(int collapseValue) {


        if (collapseValue == 1) {
            recyclerView = findViewById(R.id.recyclerViewLinear);


            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(R.string.title);
            coordinatorLayout.setVisibility(View.GONE);
            //linearLayout.setVisibility(View.VISIBLE);
            //layout.setVisibility(View.VISIBLE);


            swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
            swipeRefreshLayout.setColorSchemeColors(Color.MAGENTA, Color.YELLOW, Color.GREEN, Color.RED, Color.BLUE);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(false);

                        }
                    }, 3000);

                }
            });
        }
        if (collapseValue == 2) {
            recyclerView = findViewById(R.id.recycler_view);

            setSupportActionBar(toolbar);
            linearLayout.setVisibility(View.GONE);
            appBarLayout.setExpanded(true);
            getSupportActionBar().setTitle(R.string.title);
            mToolbar.setVisibility(View.GONE);

            setRecyclerViewMargins();
            swipeRefreshLayoutCoordinator.setProgressBackgroundColorSchemeColor(Color.WHITE);
            swipeRefreshLayoutCoordinator.setColorSchemeColors(Color.MAGENTA, Color.YELLOW, Color.GREEN, Color.RED, Color.BLUE);
            swipeRefreshLayoutCoordinator.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayoutCoordinator.setRefreshing(false);

                        }
                    }, 3000);

                }
            });
            //layout.setVisibility(View.GONE);
        }

        // Setting the recycler view
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < 10; i++) {
            CardData cardData = new CardData("Heading", "Sub-Heading", "Description");
            cardDataList.add(cardData);
        }

        setAdapter(3);

        setNavigation(drawerValue);


    }

    private void setNavigation(int drawerValue) {
        if (drawerValue == 1) {
            drawerLayout = findViewById(R.id.drawer_layout);
            toggle = new ActionBarDrawerToggle(ScrollingActivity.this, drawerLayout, R.string.open, R.string.close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }else{
            drawerLayout = findViewById(R.id.drawer_layout);
            toggle = new ActionBarDrawerToggle(ScrollingActivity.this, drawerLayout, R.string.open, R.string.close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toolbar.setNavigationIcon(null);
            mToolbar.setNavigationIcon(null);
        }
    }

}