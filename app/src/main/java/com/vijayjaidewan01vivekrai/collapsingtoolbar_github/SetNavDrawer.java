package com.vijayjaidewan01vivekrai.collapsingtoolbar_github;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.Menu_items;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.NavDrawer;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.TestResults;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Okhttpclient.ApiService;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Okhttpclient.ApiUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class SetNavDrawer {

    NavigationView navigationView;
    AppCompatImageView navHeaderImage;
    TextView navHeaderText;
    Context context;
    NavDrawer navDrawer;
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
                    navDrawer=response.body().getResults().getNavDrawer();
                    Glide.with(context)
                            .load(navDrawer.getHeader_layout().getImage())
                            .into(navHeaderImage);

                    navHeaderText.setText(navDrawer.getHeader_layout().getText());
                    int i =Menu.FIRST-1;
//                    menu.add("Menu");
//                    Log.d("Menu item 1",""+menu.getItem(0));
                    while(i<navDrawer.getMenu_items().size())
                    {
                        Menu_items items = navDrawer.getMenu_items().get(i);
//                        try {
//                            Bitmap bitmap = Glide.with(context)
//                                                            .load(items.getUrl())
//                                                            .asBitmap()
//                                                            .into(50,50)
//                                                            .get();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        } catch (ExecutionException e) {
//                            e.printStackTrace();
//                        }
                        String url = items.getIcon();
                        Log.e("Nav Drawer","Yes");
//                            URL u = new URL(url);
//                        InputStream iStream = null;
//                        try {
//                            iStream = (InputStream) new URL(url).getContent();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Drawable drawable = Drawable.createFromStream(iStream,"icon");

//                            Drawable drawable = BitmapFactory.decodeStream((InputStream) u.getContent());
//                            menu.add(0,(Menu.FIRST+i),Menu.NONE,items.getItem()).setIcon(R.drawable.ic_account_circle_black_24dp);
                        i++;
                    }
                    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            Log.d("Clicked item",""+(item.getItemId()));
//                            item.setCheckable(true);
                            item.setChecked(true);
//                            item.setEnabled(true);
                            if(onClickSetListener != null)
                                onClickSetListener.onClickFunction(navDrawer.getMenu_items().get(item.getItemId()-1).getUrl());
                            //context.startActivity(new Intent(context,ScrollingActivity.class));
                            //Toast.makeText(context,item.getTitle() + " : " + navDrawer.getMenu_items().get(item.getItemId()).getUrl(),Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });

                    navigationView.setBackgroundColor(Color.parseColor(navDrawer.getNav_drawer_bg_color()));
//                    NavigationView.OnNavigationItemSelectedListener clickListener = new ScrollingActivity();
//                    clickListener.onNavigationItemSelected(navigationView.getMenu().getItem());
                    //menu.add(R.id.group1,R.id.item1,Menu.NONE,response.body().getResults().getNavDrawer().getMenu_items().get())
                }
            }

            @Override
            public void onFailure(Call<TestResults> call, Throwable t) {
                Log.e("URL error",t.getLocalizedMessage());

            }
        });
    }

    private OnClickSet onClickSetListener;
    public void setClickListener(OnClickSet onClickSet){
        this.onClickSetListener = onClickSet;
    }

}

//    NavigationView view = findViewById(R.id.nav_view);
//                        view.setNavigationItemSelectedListener(ScrollingActivity.this);
//                                View head = view.getHeaderView(0);
//                                AppCompatImageView image = head.findViewById(R.id.nav_header_image);

