package com.vijayjaidewan01vivekrai.collapsingtoolbar_github;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Adapters.CardAdapter;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Adapters.NavDrawerCardAdapter;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.Data;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.Menu_items;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.NavDrawer;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.TestResults;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Okhttpclient.ApiService;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Okhttpclient.ApiUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetNavDrawer {

    LinearLayout navigationView;
    AppCompatImageView navHeaderImage;
    TextView navHeaderText;
    Context context;
    NavDrawer navDrawer;
    Menu menu;
    RecyclerView recyclerView;
    Bitmap bitmap;
    DatabaseHelper db;
//    LruCache<String,Bitmap> mMemoryCache;
    String url = "http://bydegreestest.agnitioworld.com/test/menu.php";

    public void setUrl(String url) {
        this.url = url;
    }

    public SetNavDrawer(LinearLayout view, Context context, DatabaseHelper db) {
        navigationView = view;
        this.context = context;
        this.db = db;
//        this.mMemoryCache = mMemoryCache;
    }

    public void getJSON() {
        recyclerView = navigationView.findViewById(R.id.recycler_view_nav);
        navHeaderImage = navigationView.findViewById(R.id.nav_header_image);
        navHeaderText = navigationView.findViewById(R.id.nav_header_text);
//        menu = navigationView.getMenu();
//        menu.clear();

        //navigationView = view.findViewById(R.id.nav_view);
//        Log.d("Header Count", "" + navigationView.getHeaderCount());
        ApiService apiService = ApiUtils.getAPIService();
        apiService.results(url).enqueue(new Callback<TestResults>() {
            @Override
            public void onResponse(Call<TestResults> call, Response<TestResults> response) {
                if (response.isSuccessful()) {
                    navDrawer = response.body().getResults().getNavDrawer();
//                    new DownloadImage().execute();
//                    DatabaseHelper databaseHelper = new DatabaseHelper(context,"Toolbar",null,1);
                    db.saveMenu(navDrawer.getMenu_items());
                    db.saveHeaderTitle(navDrawer.getHeader_layout().getText());
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.grey);
                    Glide.with(context)
                            .load(navDrawer.getHeader_layout().getImage())
                            .asBitmap()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(new SimpleTarget<Bitmap>(200, 200) {
                                @Override
                                public void onLoadStarted(Drawable placeholder) {
                                    super.onLoadStarted(placeholder);
                                }

                                @Override
                                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                    super.onLoadFailed(e, errorDrawable);
                                }

                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    navHeaderImage.setImageBitmap(resource);
                                }
                            });
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    navHeaderText.setText(navDrawer.getHeader_layout().getText());
                    NavDrawerCardAdapter cardAdapter = new NavDrawerCardAdapter(navDrawer.getMenu_items(),context);
                    recyclerView.setAdapter(cardAdapter);
                    cardAdapter.notifyDataSetChanged();
                    cardAdapter.setClickListener((OnClickSet) context);

//                      menu.add("Menu");

//                    Log.d("Menu item 1",""+menu.getItem(0));

//                      ArrayList<Bitmap> bm = downloadImage();


                   /* navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            Log.d("Clicked item", "" + (item.getItemId()));
//                            item.setCheckable(true);
                            item.setChecked(true);
//                            item.setEnabled(true);
                            if (onClickSetListener != null)
                                onClickSetListener.onClickFunction(navDrawer.getMenu_items().get(item.getItemId() - 1).getUrl());
                            //context.startActivity(new Intent(context,ScrollingActivity.class));
                            //Toast.makeText(context,item.getTitle() + " : " + navDrawer.getMenu_items().get(item.getItemId()).getUrl(),Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });
*/
                    navigationView.setBackgroundColor(Color.parseColor(navDrawer.getNav_drawer_bg_color()));
//                    NavigationView.OnNavigationItemSelectedListener clickListener = new ScrollingActivity();
//                    clickListener.onNavigationItemSelected(navigationView.getMenu().getItem());
                    //menu.add(R.id.group1,R.id.item1,Menu.NONE,response.body().getResults().getNavDrawer().getMenu_items().get())
                }
            }

            @Override
            public void onFailure(Call<TestResults> call, Throwable t) {
                Log.e("URL error", t.getLocalizedMessage());

            }
        });
    }

    private OnClickSet onClickSetListener;

    public void setClickListener(OnClickSet onClickSet) {
        this.onClickSetListener = onClickSet;
    }

}

/*  public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
*/

    /*public ArrayList<Bitmap> downloadImage()
    {
        final ArrayList <Bitmap> bm= new ArrayList<>();
        for(Menu_items items : navDrawer.getMenu_items()) {
            Glide.with(context)
                    .load(items.getIcon())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>(50, 50) {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                           bm.add(resource);
                        }
                    });
        }
        return bm;
    }*/

    /*class DownloadImage extends AsyncTask<Void, Void, Void> {
        ArrayList<Bitmap> bm = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... voids) {
            for (Menu_items items : navDrawer.getMenu_items()) {
                try {
                    URL url = new URL(items.getIcon());
                    HttpURLConnection connection = null;
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    bitmap = BitmapFactory.decodeStream(input);

//                    Matrix matrix = new Matrix();
//                    matrix.postScale(30,30);
//                    bitmap = Bitmap.createBitmap(bitmap,0,0,50,50);
                    bm.add(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                        *//*Glide.with(context)
                                .load(items.getIcon())
                                .asBitmap()
                                .into(new SimpleTarget<Bitmap>(50,50) {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                     bitmap = resource;
                                     setbitmap();
                                    }
                                });*//*
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            i = Menu.FIRST - 1;
            while (i < navDrawer.getMenu_items().size()) {
                items = navDrawer.getMenu_items().get(i);
                Log.d("Nav Drawer", "Yes");

                Drawable drawable = new BitmapDrawable(context.getResources(), bm.get(i));

                menu.add(0, (Menu.FIRST + i), Menu.NONE, items.getItem()).setIcon(drawable);
                i++;
            }
        }
    }
}*/

//                            .asBitmap()
//                            .listener(new RequestListener<String, Bitmap>() {
//                                @Override
//                                public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
//                                    return false;
//                                }
//
//                                @Override
//                                public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                                    Log.d("in listener",model);
//                                    bitmap = resource;
//                                    return true;
//                                }
//


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


//                        Bitmap bm = null;
//                        ApiService apiService1 = ApiUtils.getAPIService();
//                        Call call1 = apiService1.results(url);
//                        try {
//                            Response<ResponseBody> response1 = call1.execute();
//                            bm = BitmapFactory.decodeStream(response1.body().byteStream());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

                        /*InputStream iStream = null;
                        try {
                            iStream = (InputStream) new URL(url).getContent();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Drawable drawable = Drawable.createFromStream(iStream,"icon");*/
//                            Drawable drawable = BitmapFactory.decodeStream((InputStream) u.getContent());


//    NavigationView view = findViewById(R.id.nav_view);
//                        view.setNavigationItemSelectedListener(ScrollingActivity.this);
//                                View head = view.getHeaderView(0);
//                                AppCompatImageView image = head.findViewById(R.id.nav_header_image);

