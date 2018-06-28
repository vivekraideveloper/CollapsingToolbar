package com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.Menu_items;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.OnClickSet;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.R;

import java.util.ArrayList;

public class NavDrawerCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
   private ArrayList<Menu_items> items;
   private Context context;

    public NavDrawerCardAdapter(ArrayList<Menu_items> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_drawer_card,parent,false);
        return new ViewHolder(v);
        }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder)holder).itemText.setText(items.get(position).getItem());
        ((ViewHolder)holder).itemText.setTextColor(Color.parseColor(items.get(position).getText_color()));

        Glide.with(context)
                .load(items.get(position).getIcon())
                .into(((ViewHolder)holder).iconImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickSetListener != null)
                    onClickSetListener.onClickFunction(items.get(position).getUrl());
                Toast.makeText(context, items.get(position).getUrl(), Toast.LENGTH_SHORT).show();
//                Log.i("URL in adapter", items.get(position).getUrl());
            }
        });
    }

    private OnClickSet onClickSetListener;
    public void setClickListener(OnClickSet onClickSet){
        this.onClickSetListener = onClickSet;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public AppCompatImageView iconImage;
        public TextView itemText;

        public ViewHolder(View itemView) {
            super(itemView);

            iconImage = itemView.findViewById(R.id.nav_icon_image);
            itemText = itemView.findViewById(R.id.nav_item);
        }
    }
}