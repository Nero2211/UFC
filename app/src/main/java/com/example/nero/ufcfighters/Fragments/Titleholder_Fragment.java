package com.example.nero.ufcfighters.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nero.ufcfighters.Card_Adapters.Card_Fighter_Adapter;
import com.example.nero.ufcfighters.FighterDescription;
import com.example.nero.ufcfighters.FromAPI;
import com.example.nero.ufcfighters.Interactor.RecyclerViewTitleHolderInteractor;
import com.example.nero.ufcfighters.R;
import com.example.nero.ufcfighters.Models.Title_holders;
import com.kosalgeek.android.caching.FileCacher;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class Titleholder_Fragment extends Fragment implements RecyclerViewTitleHolderInteractor {


    ArrayList<Title_holders> title_Fighters;
    RecyclerView recyclerView;
    private static LruCache<String, Title_holders> titleHoldersMemoryCache;

    public Titleholder_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_titleholder_, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.titleHolder_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getTitleHolders();

        return v;
    }

    public void getTitleHolders(){
        title_Fighters = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://ufc-data-api.ufc.com/")
                .build();

        FromAPI fromAPI = retrofit.create(FromAPI.class);

        final Call<List<Title_holders>> fighterCall = fromAPI.getFighters();
        fighterCall.enqueue(new Callback<List<Title_holders>>() {
            @Override
            public void onResponse(Call<List<Title_holders>> call, Response<List<Title_holders>> response) {
                if(response.body() != null){
                    for(int i = 0; i < response.body().size(); i++) {
                        Title_holders title_holder = response.body().get(i);
                        title_Fighters.add(title_holder);
                    }
                    Card_Fighter_Adapter adapter = new Card_Fighter_Adapter(getContext(), title_Fighters);
                    adapter.setInteractor(Titleholder_Fragment.this);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Title_holders>> call, Throwable t) {

            }
        });
    }

    @Override
    public void openDescription(Title_holders titleHolders) {
        Intent intent = new Intent(getActivity(), FighterDescription.class);
        intent.putExtra("nickname", titleHolders.getNickname());
        intent.putExtra("firstname", titleHolders.getFirst_name());
        intent.putExtra("lastname", titleHolders.getLast_name());
        intent.putExtra("winCount", titleHolders.getWins());
        intent.putExtra("loseCount", titleHolders.getLosses());
        intent.putExtra("drawCount", titleHolders.getDraws());
        intent.putExtra("picture", titleHolders.getProfile_image());
        intent.putExtra("weightclass", titleHolders.getWeight_class());
        intent.putExtra("rank", titleHolders.getRank());
        intent.putExtra("link", titleHolders.getLink());
        startActivity(intent);
    }
}
