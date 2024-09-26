package com.pestControlDrugs.bnet.retrofitObjects

import com.pestControlDrugs.bnet.model.dataclass.AllDrugs
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("api/ppp/index/")
    fun getAllData(): Call<AllDrugs>

    @GET("api/ppp/index/?")
    fun getCurrentDrugs(@Query("search")searchText: String): Call<AllDrugs>

}