package com.example.nero.ufcfighters.Card_Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nero.ufcfighters.Interactor.RecyclerViewTitleHolderInteractor;
import com.example.nero.ufcfighters.R;
import com.example.nero.ufcfighters.Models.Title_holders;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Nero on 09/07/2017.
 */

public class Card_Fighter_Adapter extends RecyclerView.Adapter<Card_Fighter_Adapter.FighterViewHolder> {

    ArrayList<Title_holders> titleHoldersList;
    Context context;
    RecyclerViewTitleHolderInteractor interactor;

    public Card_Fighter_Adapter(Context context, ArrayList<Title_holders> fighters){
        this.context = context;
        this.titleHoldersList = fighters;
    }


    @Override
    public FighterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_fighters, parent, false);
        return new FighterViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(FighterViewHolder holder, int position) {

        final Title_holders title_holders = titleHoldersList.get(position);
        title_holders.setPostion(position);
        holder.fName.setText(title_holders.getFirst_name());
        if(title_holders.getLast_name() == null){
            holder.lName.setText("");
        }else{
            holder.lName.setText(" " + title_holders.getLast_name());
        }
        if(title_holders.getNickname() == null){
            holder.nName.setText("No Nickname");
        }else{
            holder.nName.setText(title_holders.getNickname());
        }
        if(title_holders.getWeight_class().contains("_")){
            String newWeightClass = title_holders.getWeight_class().replaceAll("_", " ");
            holder.wClass.setText(newWeightClass);
        }
        else if(title_holders.getWeight_class() == null){
            holder.wClass.setText("Not Available");
        }
        else {
            holder.wClass.setText(title_holders.getWeight_class());
        }
        Picasso.with(context).load(title_holders.getBelt_thumbnail()).into(holder.beltImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interactor.openDescription(title_holders);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titleHoldersList.size();
    }

    public void setInteractor(RecyclerViewTitleHolderInteractor interactor){
        this.interactor = interactor;
    }

    class FighterViewHolder extends RecyclerView.ViewHolder {

        private TextView fName, lName, nName, wClass;
        private ImageView beltImage;


        public FighterViewHolder(View itemView) {
            super(itemView);

            fName = (TextView)itemView.findViewById(R.id.card_fighter_fName);
            lName = (TextView)itemView.findViewById(R.id.card_fighter_lName);
            nName = (TextView)itemView.findViewById(R.id.card_fighter_nName);
            wClass = (TextView)itemView.findViewById(R.id.card_fighter_wClass);
            beltImage = (ImageView)itemView.findViewById(R.id.card_fighter_pic);

        }
    }
}
