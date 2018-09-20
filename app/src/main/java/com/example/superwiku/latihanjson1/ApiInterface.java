package com.example.superwiku.latihanjson1;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("bins/xbg70")
    Call<Biodata> getBiodata();
}
