package com.example.nero.ufcfighters;

import com.example.nero.ufcfighters.Models.Fighter;
import com.example.nero.ufcfighters.Models.Title_holders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Nero on 09/07/2017.
 */

public interface FromAPI {

    @Headers({"Cache-Control: max-age=640000", "User-Agent: UFC"})
    @GET("api/v3/iphone/fighters/title_holders")
    Call<List<Title_holders>> getFighters();

    @Headers({"Cache-Control: max-age=640000", "User-Agent: UFC"})
    @GET("api/v3/iphone/fighters")
    Call<List<Fighter>> getAllFighters();
}
