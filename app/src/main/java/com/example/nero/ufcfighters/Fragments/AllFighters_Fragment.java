package com.example.nero.ufcfighters.Fragments;


import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.constraint.solver.Cache;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nero.ufcfighters.Card_Adapters.Card_Allfighter_Adapter;
import com.example.nero.ufcfighters.FighterDescription;
import com.example.nero.ufcfighters.FromAPI;
import com.example.nero.ufcfighters.Interactor.RecyclerViewFightersInteractor;
import com.example.nero.ufcfighters.Models.Fighter;
import com.example.nero.ufcfighters.R;
import com.squareup.okhttp.Interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllFighters_Fragment extends Fragment implements RecyclerViewFightersInteractor{

    private RecyclerView recyclerView;
    private ArrayList<Fighter> fighters;

    public AllFighters_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_all_fighters_, container, false);

        recyclerView = (RecyclerView)v.findViewById(R.id.All_fighters_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getAllFighter();

        return v;
    }

    public void getAllFighter(){
        fighters = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://ufc-data-api.ufc.com/")
                .build();

        FromAPI fromAPI = retrofit.create(FromAPI.class);
        final Call<List<Fighter>> fighterCall = fromAPI.getAllFighters();
        fighterCall.enqueue(new Callback<List<Fighter>>() {
            @Override
            public void onResponse(Call<List<Fighter>> call, Response<List<Fighter>> response) {
                if(response.body() != null){
                    for(int i = 0; i < response.body().size(); i++){
                        Fighter fighter = response.body().get(i);
                        fighters.add(fighter);
                    }
                    Card_Allfighter_Adapter adapter = new Card_Allfighter_Adapter(getContext(), fighters);
                    adapter.setInteractor(AllFighters_Fragment.this);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<Fighter>> call, Throwable t) {

            }
        });
    }

    @Override
    public void openDescription(Fighter fighter) {
        Intent intent = new Intent(getActivity(), FighterDescription.class);
        intent.putExtra("nickname", fighter.getNickname());
        intent.putExtra("firstname", fighter.getFirst_name());
        intent.putExtra("lastname", fighter.getLast_name());
        intent.putExtra("winCount", fighter.getWins());
        intent.putExtra("loseCount", fighter.getLosses());
        intent.putExtra("drawCount", fighter.getDraws());
        intent.putExtra("picture", fighter.getProfile_image());
        intent.putExtra("weightclass", fighter.getWeight_class());
        intent.putExtra("rank", fighter.getRank());
        intent.putExtra("link", fighter.getLink());
        startActivity(intent);
    }


}
