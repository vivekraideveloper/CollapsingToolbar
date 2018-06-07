package com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.CardData;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.R;

import java.util.List;

public class Card1Adapter extends RecyclerView.Adapter<Card1Adapter.ViewHolder> {

    private List<CardData> cardData;
    private Context context;
    //private int position;
    //private boolean flag = true; // to implement background of relative layout

    public Card1Adapter(List<CardData> cardData, Context context) {
        this.cardData = cardData;
        this.context = context;
        /*this.position = position;*/
    }

    @Override
    public void onBindViewHolder(@NonNull Card1Adapter.ViewHolder holder, int position) {

        final CardData data = cardData.get(position);

        holder.head.setText(data.getHeading());
        holder.sub_head.setText(data.getSub());
        holder.desc.setText(data.getDesc());
//        Picasso.with(context)
//                .load(data.getImage())
//                .into(holder.iconImage);
    }

    @Override
    public int getItemCount() {
        return cardData.size();
    }

    @NonNull
    @Override
    public Card1Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout=R.layout.card2;
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layout,parent,false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView head,sub_head,desc;
        //public AppCompatImageView iconImage;

        public ViewHolder(View itemView)
        {
            super(itemView);

            head = (TextView)itemView.findViewById(R.id.tv_recycler_item_1);
            sub_head = (TextView)itemView.findViewById(R.id.tv_recycler_item_2);
            desc = (TextView)itemView.findViewById(R.id.tv_recycler_item_3);
            //iconImage = (AppCompatImageView) itemView.findViewById(R.id.iconImage);
        }
    }

}

