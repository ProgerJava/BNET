package com.pestControlDrugs.bnet.model

import android.util.Log
import com.pestControlDrugs.bnet.model.dataclass.AllDrugs
import com.pestControlDrugs.bnet.retrofitObjects.MyRetrofit
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class DrugsListModel @Inject constructor() {


    suspend fun getAllDrugs(): AllDrugs = coroutineScope {
        val call: Call<AllDrugs> = MyRetrofit.reference.getAllData()
        var allDrugs: AllDrugs? = null
        call.enqueue(object: Callback<AllDrugs> {
            override fun onResponse(call: Call<AllDrugs>, response: Response<AllDrugs>) {
                allDrugs = response.body()?.let {it}
            }

            override fun onFailure(call: Call<AllDrugs>, t: Throwable) {
                Log.println(Log.ERROR, "getAllDrugs", t.message.toString())
            }
        })
        while (allDrugs == null) {
            delay(100)
        }
        return@coroutineScope allDrugs!!
    }

}