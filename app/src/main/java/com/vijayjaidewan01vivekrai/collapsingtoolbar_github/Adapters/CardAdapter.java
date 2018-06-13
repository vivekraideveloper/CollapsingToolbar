package com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.CardData;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.Data;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.R;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Data> cardData;
    private Context context;
    private int pos;
    private boolean flag = true; // to implement background of relative layout
    private int ONE = 1;
    private int TWO = 2;
    private int THREE = 3;
    private int FOUR = 4;

    public CardAdapter(List<Data> cardData, Context context, int position) {
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
//        int layout = 0;
//        switch (pos) {
//            case 1:
//                layout = R.layout.card1; // Simple Text
//                flag = false;
//                break;
//            case 3:
//                layout = R.layout.card3;
//                flag = true;
//                break;
//            case 4:
//                layout = R.layout.card4;
//                flag = true;
//                break;
//        }
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(layout, parent, false);
//        return new ViewHolder(v);
        switch (viewType){
            case 1:{
                View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.card1,parent,false);
                return new ViewHolder1(v);
            }
            case 2:{
                View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.card2,parent,false);
                return new ViewHolder2(v);
            }
            case 3:{
                View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.card3,parent,false);
                return new ViewHolder3(v);
            }
            case 4:{
                View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.card4,parent,false);
                return new ViewHolder4(v);
            }
            default:return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolder1){
            ((ViewHolder1) holder).head.setText(cardData.get(position).getText1());
            ((ViewHolder1) holder).sub_head.setText(cardData.get(position).getText2());
            ((ViewHolder1) holder).desc.setText(cardData.get(position).getText3());
        }if(holder instanceof ViewHolder2){
            ((ViewHolder2) holder).head.setText(cardData.get(position).getText1());
            ((ViewHolder2) holder).sub_head.setText(cardData.get(position).getText2());
            ((ViewHolder2) holder).desc.setText(cardData.get(position).getText3());
        }if(holder instanceof ViewHolder3){
            ((ViewHolder3) holder).head.setText(cardData.get(position).getText1());
            ((ViewHolder3) holder).sub_head.setText(cardData.get(position).getText2());
            ((ViewHolder3) holder).desc.setText(cardData.get(position).getText3());
        }
        if(holder instanceof ViewHolder4){
            ((ViewHolder4) holder).head.setText(cardData.get(position).getText1());
        ((ViewHolder4) holder).sub_head.setText(cardData.get(position).getText2());
        ((ViewHolder4) holder).desc.setText(cardData.get(position).getText3());
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
    public class ViewHolder1 extends RecyclerView.ViewHolder
    {

        public TextView head, sub_head, desc;

        public ViewHolder1(View itemView) {
            super(itemView);
            head = (TextView) itemView.findViewById(R.id.tv_recycler_item_1);
            sub_head = (TextView) itemView.findViewById(R.id.tv_recycler_item_2);
            desc = (TextView) itemView.findViewById(R.id.tv_recycler_item_3);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder{

        public TextView head, sub_head, desc;
        //public RelativeLayout relativeLayout;

        public ViewHolder2(View itemView) {
            super(itemView);

            head = (TextView) itemView.findViewById(R.id.tv_recycler_item_1);
            sub_head = (TextView) itemView.findViewById(R.id.tv_recycler_item_2);
            desc = (TextView) itemView.findViewById(R.id.tv_recycler_item_3);
            //relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rela_round);
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder{

        public TextView head, sub_head, desc;
        //public RelativeLayout relativeLayout;

        public ViewHolder3(View itemView) {
            super(itemView);

            head = (TextView) itemView.findViewById(R.id.tv_recycler_item_1);
            sub_head = (TextView) itemView.findViewById(R.id.tv_recycler_item_2);
            desc = (TextView) itemView.findViewById(R.id.tv_recycler_item_3);
            //relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rela_round);
        }
    }

    public class ViewHolder4 extends RecyclerView.ViewHolder {

        public TextView head, sub_head, desc;
        //public RelativeLayout relativeLayout;

        public ViewHolder4(View itemView) {
            super(itemView);

            head = (TextView) itemView.findViewById(R.id.tv_recycler_item_1);
            sub_head = (TextView) itemView.findViewById(R.id.tv_recycler_item_2);
            desc = (TextView) itemView.findViewById(R.id.tv_recycler_item_3);
            //relativeLayout = (RelativeLayout)itemView.findViewById(R.id.rela_round);
        }
    }

}

