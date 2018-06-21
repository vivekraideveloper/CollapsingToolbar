package com.vijayjaidewan01vivekrai.collapsingtoolbar_github;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.TestResults;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Okhttpclient.ApiService;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Okhttpclient.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetNavDrawer {

    NavigationView navigationView;
    AppCompatImageView navHeaderImage;
    TextView navHeaderText;
    Context context;
    Menu menu;
    String url = "http://bydegreestest.agnitioworld.com/test/menu.php";

    public void setUrl(String url) {
        this.url = url;
    }

    public SetNavDrawer(NavigationView view,Context context) {
        navigationView = view;
        this.context = context;
    }

    public void getJSON()
    {
        navHeaderImage = navigationView.findViewById(R.id.nav_header_image);
        navHeaderText = navigationView.findViewById(R.id.nav_header_text);
        menu = navigationView.getMenu();
        menu.clear();
        //navigationView = view.findViewById(R.id.nav_view);
        Log.d("Header Count",""+navigationView.getHeaderCount());
        ApiService apiService = ApiUtils.getAPIService();
        apiService.results(url).enqueue(new Callback<TestResults>() {
            @Override
            public void onResponse(Call<TestResults> call, Response<TestResults> response) {
                if(response.isSuccessful())
                {
                    Glide.with(context)
                            .load(response.body().getResults().getNavDrawer().getHeader_layout().getImage())
                            .into(navHeaderImage);
                    navHeaderText.setText(response.body().getResults().getNavDrawer().getHeader_layout().getText());
                    //menu.add(R.id.group1,R.id.item1,Menu.NONE,response.body().getResults().getNavDrawer().getMenu_items().get())
                }
            }

            @Override
            public void onFailure(Call<TestResults> call, Throwable t) {
                Log.e("URL error",t.getLocalizedMessage());

            }
        });
    }
}

//    NavigationView view = findViewById(R.id.nav_view);
//                        view.setNavigationItemSelectedListener(ScrollingActivity.this);
//                                View head = view.getHeaderView(0);
//                                AppCompatImageView image = head.findViewById(R.id.nav_header_image);

