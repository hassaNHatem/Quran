package com.example.quranproject.quran.API;

import com.example.quranproject.quran.API.Model.RadiosResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mohamed Nabil Mohamed (Nobel) on 2/7/2019.
 * byte code SA
 * m.nabil.fci2015@gmail.com
 */

public interface ApiCalls {

    @GET("radio/radio_ar.json")
    Call<RadiosResponse> getAllRadioChannels();
}
