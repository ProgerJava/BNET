package com.pestControlDrugs.bnet.retrofitObjects

import com.pestControlDrugs.bnet.constant.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofit {

    val reference: APIService by lazy {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .build()
            .create(APIService::class.java)
    }
}