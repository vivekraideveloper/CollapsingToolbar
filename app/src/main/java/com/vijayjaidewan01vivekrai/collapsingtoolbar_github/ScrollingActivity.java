package com.vijayjaidewan01vivekrai.collapsingtoolbar_github;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SquaringDrawable;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Adapters.CardAdapter;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.Data;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.Login;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.Results;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.TestResults;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.ToolBar;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Okhttpclient.ApiService;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Okhttpclient.ApiUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScrollingActivity extends AppCompatActivity implements OnClickSet {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Toolbar mToolbar;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private AppBarLayout appBarLayout;
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshLayout swipeRefreshLayoutCoordinator;
    private View login;
    private NavigationView navigationView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    CardAdapter adapter;
    int drawerValue = 2;
    int searchValue = 1;
    String BASE_URL = "http://bydegreestest.agnitioworld.com/test/mobile_app.php";
    String backUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        linearLayout = findViewById(R.id.linear_layout);
        appBarLayout = findViewById(R.id.app_bar);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayoutCoordinator = findViewById(R.id.swipe_coordinator);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        mToolbar = findViewById(R.id.tool_bar);
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        drawerLayout = findViewById(R.id.drawer_layout);

        //layout = findViewById(R.id.layout_content);

        //cardDataList = new ArrayList<>();

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            // notify user you are online
            callHttp(BASE_URL);
        }
        else if (conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {
            // notify user you are not online

            View view = findViewById(android.R.id.content);
            Snackbar snackbar = Snackbar.make(view, "Please ensure stable internet connectivity!", 10000).setAction("Action", null);
            snackbar.show();
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            coordinatorLayout.setVisibility(View.GONE);
// collapsingToolbarLayout.setVisibility(View.GONE);
            mToolbar.setVisibility(View.GONE);
//            toolbar.setVisibility(View.GONE);

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.drawer_layout, new Offline_fragment());
            ft.commit();
        }
    }

    public void callHttp(String URL) {
        BASE_URL = URL;
        ApiService apiService = ApiUtils.getAPIService();
        // ADD A PROGRESS BAR TO BE SHOWN TO THE USER BEFORE THE DATA IS LOADED
        final ContentLoadingProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.show();
        apiService.results(URL).enqueue(new Callback<TestResults>() {

            @Override
            public void onResponse(Call<TestResults> call, Response<TestResults> response) {
                if (response.isSuccessful()) {
                    if (response.body().getMsg().equals("success")) {
                        progressBar.hide();
                        //DISAPPEAR THE PROGRESS BAR SHOWN EARLIER
                        int collapseValue;
                        drawerValue = Integer.parseInt(response.body().getResults().getToolBar().getIs_back());
//                        drawerValue = 3;
                        collapseValue = Integer.parseInt(response.body().getResults().getToolBar().getTop_image());

                        Log.d("Collapse", "" + collapseValue);
                        Log.d("Drawer", "" + drawerValue);

                        Results results = response.body().getResults();
//                        filter(response.body().getResults().getData().get(posi).getText1());
                        backUrl = response.body().getResults().getToolBar().getBack_url();
                        setCollapse(collapseValue, results);
                        setNavigation(drawerValue);

                        int viewType = Integer.parseInt(response.body().getResults().getView_type());
                        Log.d("View Type", "" + viewType);
                        switch (viewType) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                adapter = new CardAdapter(response.body().getResults().getData(), ScrollingActivity.this, viewType);
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                adapter.setClickListener(ScrollingActivity.this);
                                break;
                            case 5: //add webview
                                break;
                            case 6:
                                setLogin(response.body().getResults().getLogin());
                                break;
                            default:
                                Log.e("View Type", "Wrong view Type value - " + viewType);
                        }

                    } else {
                        Toast.makeText(ScrollingActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TestResults> call, Throwable t) {
                Log.e("Url error",t.getLocalizedMessage());

            }
        });
    }
//
//    public void setAdapter()
//    {
//        adapter = new Card1Adapter(cardDataList, ScrollingActivity.this);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//
//    }
//
//    public void setAdapter(int pos)
//    {
//        adapter = new CardAdapter(cardDataList,ScrollingActivity.this,pos);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//    }


    //Search Bar controlled by searchValue = 0(Not Present), 1(Present)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        MenuInflater inflater = getMenuInflater();

        if (searchValue == 1) {
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            inflater.inflate(R.menu.search_layout, menu);
            MenuItem item = menu.findItem(R.id.search_view);
            SearchView searchView = (SearchView) item.getActionView();
            searchView.setIconified(true);
//            SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());
//            searchView.setSearchableInfo(searchableInfo);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                public static final String TAG = "TAG";

                @Override
                public boolean onQueryTextSubmit(String s) {
                    Log.d(TAG, "onQueryTextSubmit: called:");

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

        if (drawerValue == 0) {
            if (toggle.onOptionsItemSelected(item)) {
                return true;
            }
        }

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    // Setting the margins of Recycler View while the toolbar is collapsed to remove the empty space in between the toolbar and recycler view
    public void setRecyclerViewMargins() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
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

    private void setCollapse(int collapseValue, final Results results) {

        if (collapseValue == 1) {
            recyclerView = findViewById(R.id.recyclerViewLinear);

            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(results.getToolBar().getCollapsed_top_title());
            mToolbar.setTitleTextColor(Color.parseColor(results.getToolBar().getCollapsed_top_title_color()));
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(results.getToolBar().getExtended_top_title_color())));
            //getSupportActionBar().setTitle(results.getTop_heading());

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
                            callHttp(BASE_URL);
                        }
                    }, 3000);

                }
            });
        }
        if (collapseValue == 2) {
            recyclerView = findViewById(R.id.recycler_view);

            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(results.getToolBar().getExtended_top_title());

//            collapsingToolbarLayout.setContentScrim(new ColorDrawable(Color.parseColor("#ff00ff")));

            linearLayout.setVisibility(View.GONE);
            appBarLayout.setExpanded(true);
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
                            callHttp(BASE_URL);
                        }
                    }, 3000);
                }
            });
            //layout.setVisibility(View.GONE);
            RoundedImage roundedImage = findViewById(R.id.rounded_image);
            Glide.with(this)
                    .load(results.getToolBar().getTop_image_fg())
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new BitmapImageViewTarget(roundedImage) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            super.setResource(resource);
                        }
                    });

            AppCompatImageView background = findViewById(R.id.backImage);
            Glide.with(ScrollingActivity.this)
                    .load(results.getToolBar().getTop_image_bg())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(background);
        }

        int col = Integer.parseInt(results.getGrid_columns());
        int orientation = Integer.parseInt(results.getGrid_orientation());

        Log.d("Columns", "" + col);
        Log.d("Orientation", "" + orientation);

        setRecyclerView(col, orientation);
        //setNavigation(drawerValue);
    }

    private void setRecyclerView(int columns, int orientation) {
        // Setting the recycler view
        recyclerView.setHasFixedSize(true);
        if (columns == 0)
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        else {
            switch (orientation) {
                case 1:
                    orientation = LinearLayoutManager.VERTICAL;
                    break;
                case 2:
                    orientation = LinearLayoutManager.HORIZONTAL;
                    break;
                default:
                    Log.e("Orientation", "Wrong orientation value provided.  -  " + orientation);
            }
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), columns);
            gridLayoutManager.setOrientation(orientation); // set Horizontal Orientation
            recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        }
    }

    private void setNavigation(final int drawerValue) {
        if (drawerValue == 0) {
            backUrl = null;
            drawerLayout = findViewById(R.id.drawer_layout);
            toggle = new ActionBarDrawerToggle(ScrollingActivity.this, drawerLayout, R.string.open, R.string.close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SetNavDrawer navDrawer = new SetNavDrawer(navigationView, ScrollingActivity.this);
                    navDrawer.getJSON();
                    drawerLayout.closeDrawers();
                    navDrawer.setClickListener(ScrollingActivity.this);

                }
            }, 250);
            //head.setBackground();

        } else {
            drawerLayout = findViewById(R.id.drawer_layout);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),backUrl,Toast.LENGTH_SHORT).show();
                    callHttp(backUrl);
                }
            });
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),backUrl,Toast.LENGTH_SHORT).show();
                    callHttp(backUrl);
                }
            });
        }
    }

    private void setLogin(Login login) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment fragment = new LoginFragment();
        fragmentTransaction.add(R.id.frame, fragment);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Login", login);
//            bundle.putString("Activity_bg_color",login.getActivity_bg_color());
//            bundle.putString("Background_image",login.getBackground_image());
//            bundle.putString("Button_bg_color",login.getButton_bg_color());
//            bundle.putString("Button_text",login.getButton_text());
//            bundle.putString("Button_text_color",login.getButton_text_color());
//            bundle.putString("Card_bg_color",login.getCard_bg_color());
//            bundle.putString("Edit_text_bg",login.getEdit_text_bg());
//            bundle.putString("Input_box1",login.getInput_box1());
//            bundle.putString("Input_box2",login.getInput_box2());
//            bundle.putString("Login_url",login.getLogin_url());
//            bundle.putString("Profile_image",login.getProfile_image());
//            bundle.putString("Alpha",login.getAlpha());

        fragment.setArguments(bundle);
        fragmentTransaction.commit();

        setNavigation(0);
    }

    @Override
    public void onBackPressed() {
        if(backUrl == null)
            super.onBackPressed();
        else {
            Toast.makeText(getApplicationContext(), backUrl, Toast.LENGTH_SHORT).show();
            callHttp(backUrl);
        }
    }

    @Override
    public void onClickFunction(String url) {
        callHttp(url);
        Log.i("IN Scrolling", url);
    }

    public interface SetLayout {
        void setUrl(String url);
    }
}

