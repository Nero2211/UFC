package com.example.nero.ufcfighters.Card_Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nero.ufcfighters.Interactor.RecyclerViewFightersInteractor;
import com.example.nero.ufcfighters.Models.Fighter;
import com.example.nero.ufcfighters.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Nero on 09/07/2017.
 */

public class Card_Allfighter_Adapter extends RecyclerView.Adapter<Card_Allfighter_Adapter.FighterViewHolder>{

    ArrayList<Fighter> fighters;
    Context context;
    RecyclerViewFightersInteractor interactor;

    public Card_Allfighter_Adapter(Context context, ArrayList<Fighter> myFighters){
        this.fighters = myFighters;
        this.context = context;
    }

    @Override
    public FighterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_allfighters, parent, false);
        return new FighterViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(FighterViewHolder holder, int position) {
        final Fighter fighter = fighters.get(position);
        fighter.setPosition(position);

        holder.fName.setText(fighter.getFirst_name());
        if(fighter.getLast_name() == null){
            holder.lName.setText("");
        }else{
            holder.lName.setText(" " + fighter.getLast_name());
        }
        if(fighter.getNickname() == null){
            holder.nName.setText("No Nickname");
        }else{
            holder.nName.setText(fighter.getNickname());
        }

        if(fighter.getWeight_class() == null){
            holder.wClass.setText("Not Available");
        }
        else if(fighter.getWeight_class().contains("_")){
            String newWeightClass = fighter.getWeight_class().replaceAll("_", " ");
            holder.wClass.setText(newWeightClass);
        }
         else{
            holder.wClass.setText(fighter.getWeight_class());
        }

        Picasso.with(context).load(fighter.getProfile_image()).into(holder.profilePic);

        if(fighter.getLast_name() != "To be announced" || fighter.getLast_name() != "To Be Determined") {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    interactor.openDescription(fighter);
                }
            });
        }
        else{
            Toast.makeText(context, "No information available on this list", Toast.LENGTH_SHORT).show();
        }
    }

    public void setInteractor(RecyclerViewFightersInteractor interactor){
        this.interactor = interactor;
    }

    @Override
    public int getItemCount() {
        return fighters.size();
    }

    class FighterViewHolder extends RecyclerView.ViewHolder {
        private TextView fName, lName, nName, wClass;
        private ImageView profilePic;

        public FighterViewHolder(View itemView){
            super(itemView);

            fName = (TextView)itemView.findViewById(R.id.card_allFighter_fName);
            lName = (TextView)itemView.findViewById(R.id.card_allFighter_lName);
            nName = (TextView)itemView.findViewById(R.id.card_allFighter_nName);
            wClass = (TextView)itemView.findViewById(R.id.card_allFighter_wClass);
            profilePic = (ImageView)itemView.findViewById(R.id.card_allFighter_pic);


        }

    }
}
