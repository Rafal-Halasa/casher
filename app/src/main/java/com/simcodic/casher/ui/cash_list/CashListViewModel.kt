package com.simcodic.casher.ui.cash_list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simcodic.casher.data.models.Cash
import com.simcodic.casher.logic.interecptors.CashInterceptorI
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import java.time.LocalDate

class CashListViewModel : ViewModel(), CashListViewModelI, OnClick, OnGetNewCash, KoinComponent {
    private val interceptor = get<CashInterceptorI>()
    private var startDay = LocalDate.now()

    private var cashListLocal = MutableLiveData<MutableList<Cash?>>().apply {
        viewModelScope.launch {
            value = mutableStateListOf(interceptor.getCash(startDay.toString()))
        }
    }

    override var cashList: LiveData<MutableList<Cash?>> = cashListLocal

    override fun showViewCashDetail(day: String, exchangeRates: Pair<String, Double?>) {
        TODO("Not yet implemented")
    }

    override fun getNewCash() {
        viewModelScope.launch {
            cashListLocal.value?.let {
                startDay = startDay.minusDays(1)
                val cashListAndAddToList = interceptor.getCash(startDay.toString())
                it.add(cashListAndAddToList)
                cashListLocal.value = it
            }

        }
    }
}

interface CashListViewModelI {
    var cashList: LiveData<MutableList<Cash?>>
}

interface OnClick {
    fun showViewCashDetail(day: String, exchangeRates: Pair<String, Double?>)
}

interface OnGetNewCash {
    fun getNewCash()
}