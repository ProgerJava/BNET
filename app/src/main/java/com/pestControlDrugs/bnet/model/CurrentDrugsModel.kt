package com.pestControlDrugs.bnet.model

import android.util.Log
import com.pestControlDrugs.bnet.model.dataclass.AllDrugs
import com.pestControlDrugs.bnet.model.dataclass.AllDrugsItem
import com.pestControlDrugs.bnet.retrofitObjects.MyRetrofit
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CurrentDrugsModel @Inject constructor() {

    suspend fun getCurrentDrugs(drugsName: String): AllDrugs = coroutineScope {
        var allDrugs: AllDrugs? = null
        val call: Call<AllDrugs> = MyRetrofit.reference.getCurrentDrugs(drugsName)
        call.enqueue(object: Callback<AllDrugs> {
            override fun onResponse(call: Call<AllDrugs>, response: Response<AllDrugs>) {
                allDrugs = response.body()
            }

            override fun onFailure(call: Call<AllDrugs>, t: Throwable) {
                Log.println(Log.ERROR, "getCurrentDrugs", t.message.toString())
            }
        })
        while (allDrugs == null) {
            delay(100)
        }


        return@coroutineScope allDrugs!!
    }

}