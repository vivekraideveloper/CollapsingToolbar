package com.vijayjaidewan01vivekrai.collapsingtoolbar_github;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Adapters.Card1Adapter;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Adapters.CardAdapter;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private AppBarLayout appBarLayout;
    private View layout;
    private LinearLayout linearLayout;
    private RecyclerView recyclerView;
    private NestedScrollView nestedScrollView;

    int collapseValue = 2;
    int searchValue = 0;
    List<CardData> cardDataList;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        linearLayout = findViewById(R.id.linear_layout);
        appBarLayout = findViewById(R.id.app_bar);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        nestedScrollView = findViewById(R.id.scrollView);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar = findViewById(R.id.tool_bar);
        //layout = findViewById(R.id.layout_content);

        cardDataList = new ArrayList<>();

        // Setting the Collapsing Toolbar = 2 or Simple Toolbar = 1

        if (collapseValue == 1) {
            recyclerView = findViewById(R.id.recyclerViewLinear);

            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(R.string.title);
            coordinatorLayout.setVisibility(View.GONE);
            //linearLayout.setVisibility(View.VISIBLE);
            //layout.setVisibility(View.VISIBLE);
        }
        if (collapseValue == 2) {
            recyclerView = findViewById(R.id.recycler_view);

            setSupportActionBar(toolbar);
            linearLayout.setVisibility(View.GONE);
            appBarLayout.setExpanded(true);
            getSupportActionBar().setTitle(R.string.title);
            mToolbar.setVisibility(View.GONE);

            setRecyclerViewMargins();
            //layout.setVisibility(View.GONE);
        }

        // Setting the recycler view
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for(int i=0;i<10;i++)
        {
            CardData cardData = new CardData("Heading","Sub-Heading","Description");
            cardDataList.add(cardData);
        }

        //setRecyclerViewMargins();
        setAdapter(3);


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public void setAdapter()
    {
        adapter = new Card1Adapter(cardDataList, ScrollingActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setAdapter(int pos)
    {
        adapter = new CardAdapter(cardDataList,ScrollingActivity.this,pos);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    //Search Bar controlled by searchValue = 0(Not Present), 1(Present)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        MenuInflater inflater = getMenuInflater();
        searchValue = 1;
        if(searchValue == 1) {
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setRecyclerViewMargins()                    // Setting the margins of Recycler View while the toolbar is collapsed to remove the empty space in between the toolbar and recycler view
    {
        //ppBar = findViewById(R.id.appBar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
                if(Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()){
                    //Log.d("appbar",""+recyclerView.getScaleY());
                    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) recyclerView.getLayoutParams(); // Redundant Code with line 119

                    layoutParams.setMargins(0,0,0,0);
                    recyclerView.setLayoutParams(layoutParams);
                    //Animate the scrolling
                }
                else if(Math.abs(verticalOffset) == 0){
                    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams)recyclerView.getLayoutParams();
                    layoutParams.setMargins(0,150,0,0);
                    recyclerView.setLayoutParams(layoutParams);
                }
            }
        });
    }
}
