package com.simcodic.casher.data.services

import com.simcodic.casher.data.models.Cash
import com.simcodic.casher.data.models.CashList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


val KEY = "GhYgoo5m5STO2MxwxcVOS14yV4lh75wi"

interface CashServiceI {

    @GET("{date}")
    fun cashes(
        @Header("apikey") key: String = KEY,
        @Path("date") date: String
    ): Call<Cash>
}