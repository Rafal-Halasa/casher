package com.simcodic.casher.logic.interecptors

import com.simcodic.casher.data.models.Cash
import com.simcodic.casher.data.services.CashServiceI
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import retrofit2.awaitResponse

class CashInterceptor : CashInterceptorI, KoinComponent {
    private val service = get<CashServiceI>()
    override suspend fun getCash(date: String): Cash? {
        val response = service.cashes(date = date).awaitResponse()
        return response.body()
    }
}


interface CashInterceptorI {
    suspend fun getCash(date: String): Cash?
}
