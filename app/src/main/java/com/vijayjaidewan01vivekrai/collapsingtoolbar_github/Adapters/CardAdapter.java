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

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<CardData> cardData;
    private Context context;
    private int position;
    private boolean flag = true; // to implement background of relative layout

    public CardAdapter(List<CardData> cardData, Context context, int position) {
        this.cardData = cardData;
        this.context = context;
        this.position = position;
    }

    @Override
    public void onBindViewHolder(@NonNull final CardAdapter.ViewHolder holder, int position) {

        final CardData data = cardData.get(position);

        holder.head.setText(data.getHeading());
        holder.sub_head.setText(data.getSub());
        holder.desc.setText(data.getDesc());

//        if(flag)
//            Picasso.with(context)
//                    .load(data.getImage())
//                    .into(new Target() {
//                        @Override
//                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                            holder.relativeLayout.setBackground(new BitmapDrawable(bitmap));
//                        }
//
//                        @Override
//                        public void onBitmapFailed(Drawable errorDrawable) {
//
//                        }
//
//                        @Override
//                        public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                        }
//                    });
    }

    @Override
    public int getItemCount() {
        return cardData.size();
    }

    @NonNull
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout=0;
        switch (position)
        {
            case 1: layout = R.layout.card1; // Simple Text
                flag = false;
                break;
            case 3: layout = R.layout.card3;
                flag = true;
                break;
            case 4: layout = R.layout.card4;
                flag = true;
                break;
        }
        View v = LayoutInflater.from(parent.getContext())
                .inflate(layout,parent,false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView head,sub_head,desc;
        //public RelativeLayout relativeLayout;

        public ViewHolder(View itemView)
        {
            super(itemView);

            head = (TextView)itemView.findViewById(R.id.tv_recycler_item_1);
            sub_head = (TextView)itemView.findViewById(R.id.tv_recycler_item_2);
            desc = (TextView)itemView.findViewById(R.id.tv_recycler_item_3);
            //relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rela_round);
        }
    }

}

