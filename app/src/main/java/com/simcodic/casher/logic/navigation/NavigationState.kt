package com.simcodic.casher.logic.navigation

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.simcodic.casher.R

class NavigationState {
    companion object {
        @JvmStatic
        var state = MutableLiveData<MenuStates>()
    }
}

enum class MenuStates(val resId: Int, var bundle: Bundle? = null) {
    MAIN(R.id.cashListView), CASH_DETAIL(R.id.cashDetailView)
}