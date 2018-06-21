package com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.Data;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.R;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.ScrollingActivity;

import java.util.ArrayList;
public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<Data> cardData;
    private Context context;
    private int pos;
    //private static Bitmap image;
    //private boolean flag = true; // to implement background of relative layout
    private int ONE = 1;
    private int TWO = 2;
    private int THREE = 3;
    private int FOUR = 4;
    ScrollingActivity scrollingActivity = new ScrollingActivity();

    public CardAdapter(ArrayList<Data> cardData, Context context, int position) {
        this.cardData = cardData;
        this.context = context;
        this.pos = position;
    }

//    @Override
//    public void onBindViewHolder(@NonNull final CardAdapter.ViewHolder holder, int position) {
//
//        final CardData data = cardData.get(position);
//
//        holder.head.setText(data.getHeading());
//        holder.sub_head.setText(data.getSub());
//        holder.desc.setText(data.getDesc());
//
//    }

    @Override
    public int getItemCount() {
        return cardData.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1: {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card1, parent, false);
                return new ViewHolder1(v);
            }
            case 2: {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card2, parent, false);
                return new ViewHolder2(v);
            }
            case 3: {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card3, parent, false);
                return new ViewHolder3(v);
            }
            case 4: {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card4, parent, false);
                return new ViewHolder4(v);
            }
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder1) {
            ((ViewHolder1) holder).head.setText(cardData.get(position).getText1());
//            ((ViewHolder1) holder).head.setTextColor(Color.parseColor(cardData.get(position).getText_header_color()));

            ((ViewHolder1) holder).sub_head.setText(cardData.get(position).getText2());
//            ((ViewHolder1) holder).sub_head.setTextColor(Color.parseColor(cardData.get(position).getText_subheader_color()));

            ((ViewHolder1) holder).desc.setText(cardData.get(position).getText3());
//            ((ViewHolder1) holder).desc.setTextColor(Color.parseColor(cardData.get(position).getText_description_color()));

//            ((ViewHolder1) holder).card.setCardBackgroundColor(Color.parseColor(cardData.get(position).getBg_color()));
        }
        if (holder instanceof ViewHolder2) {
            ((ViewHolder2) holder).head.setText(cardData.get(position).getText1());
//            ((ViewHolder2) holder).head.setTextColor(Color.parseColor(cardData.get(position).getText_header_color()));

            ((ViewHolder2) holder).sub_head.setText(cardData.get(position).getText2());
//            ((ViewHolder2) holder).sub_head.setTextColor(Color.parseColor(cardData.get(position).getText_subheader_color()));

            ((ViewHolder2) holder).desc.setText(cardData.get(position).getText3());
//            ((ViewHolder2) holder).desc.setTextColor(Color.parseColor(cardData.get(position).getText_description_color()));

//            ((ViewHolder2) holder).card.setCardBackgroundColor(Color.parseColor(cardData.get(position).getBg_color()));

            Glide.with(context)
                    .load(cardData.get(position).getImage())
                    .into(((ViewHolder2) holder).iconImage);

        }
        if (holder instanceof ViewHolder3) {
            ((ViewHolder3) holder).head.setText(cardData.get(position).getText1());
//            ((ViewHolder3) holder).head.setTextColor(Color.parseColor(cardData.get(position).getText_header_color()));

            ((ViewHolder3) holder).sub_head.setText(cardData.get(position).getText2());
//            ((ViewHolder3) holder).sub_head.setTextColor(Color.parseColor(cardData.get(position).getText_subheader_color()));

            ((ViewHolder3) holder).desc.setText(cardData.get(position).getText3());
//            ((ViewHolder3) holder).desc.setTextColor(Color.parseColor(cardData.get(position).getText_description_color()));

//            ((ViewHolder3) holder).card.setCardBackgroundColor(Color.parseColor(cardData.get(position).getBg_color()));

            Glide.with(context)
                    .load(cardData.get(position).getImage())
                    .into(((ViewHolder3) holder).iconImage);


            //((ViewHolder3)holder).iconImage.setImageBitmap(CardAdapter.getImage(cardData.get(position).getImage()));
        }
        if (holder instanceof ViewHolder4) {
            ((ViewHolder4) holder).head.setText(cardData.get(position).getText1());
//            ((ViewHolder4) holder).head.setTextColor(Color.parseColor(cardData.get(position).getText_header_color()));

            ((ViewHolder4) holder).sub_head.setText(cardData.get(position).getText2());
//            ((ViewHolder4) holder).sub_head.setTextColor(Color.parseColor(cardData.get(position).getText_subheader_color()));

            ((ViewHolder4) holder).desc.setText(cardData.get(position).getText3());
//            ((ViewHolder4) holder).desc.setTextColor(Color.parseColor(cardData.get(position).getText_description_color()));

//            ((ViewHolder4) holder).card.setCardBackgroundColor(Color.parseColor(cardData.get(position).getBg_color()));
            Glide.with(context)
                    .load(cardData.get(position).getImage())
                    .into(((ViewHolder4) holder).background);
            //((ViewHolder4)holder).iconImage.setImageBitmap(RetrofitClient.getImage(cardData.get(position).getImage()));
        }

    }

    @Override
    public int getItemViewType(int position) {
        switch (pos) {
            case 1: {
                return ONE;
            }
            case 2: {
                return TWO;
            }
            case 3: {
                return THREE;
            }
            case 4: {
                return FOUR;
            }
            default:
                return Integer.parseInt(null);
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        public TextView head, sub_head, desc;
        CardView card;

        public ViewHolder1(View itemView) {
            super(itemView);

            head = itemView.findViewById(R.id.tv_recycler_item_1);
            sub_head = itemView.findViewById(R.id.tv_recycler_item_2);
            desc = itemView.findViewById(R.id.tv_recycler_item_3);
            card = itemView.findViewById(R.id.card_1);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {

        public TextView head, sub_head, desc;
        public AppCompatImageView iconImage;
        public CardView card;

        public ViewHolder2(View itemView) {
            super(itemView);

            head = itemView.findViewById(R.id.tv_recycler_item_1);
            sub_head = itemView.findViewById(R.id.tv_recycler_item_2);
            desc = itemView.findViewById(R.id.tv_recycler_item_3);
            iconImage = itemView.findViewById(R.id.icon_image);
            card = itemView.findViewById(R.id.card_2);
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {

        public TextView head, sub_head, desc;
        public AppCompatImageView iconImage;
        public CardView card;
        public Resources r;

        public ViewHolder3(View itemView) {
            super(itemView);

            head = itemView.findViewById(R.id.tv_recycler_item_1);
            sub_head = itemView.findViewById(R.id.tv_recycler_item_2);
            desc = itemView.findViewById(R.id.tv_recycler_item_3);
            iconImage = itemView.findViewById(R.id.icon_image);
            card = itemView.findViewById(R.id.card_3);
        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder {

        public TextView head, sub_head, desc;
        public AppCompatImageView background;
        public CardView card;

        public ViewHolder4(View itemView) {
            super(itemView);

            head = itemView.findViewById(R.id.tv_recycler_item_1);
            sub_head = itemView.findViewById(R.id.tv_recycler_item_2);
            desc = itemView.findViewById(R.id.tv_recycler_item_3);
            background = itemView.findViewById(R.id.background);
            card = itemView.findViewById(R.id.card_4);
        }
    }
}