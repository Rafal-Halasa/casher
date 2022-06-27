package com.simcodic.casher.ui.cash_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.simcodic.casher.databinding.ViewDetailstBinding
import com.simcodic.casher.logic.navigation.setNavigation
import com.simcodic.casher.ui.cash_list.views.ViewDataHolder.CashPair.Companion.CASH_NAME
import com.simcodic.casher.ui.cash_list.views.ViewDataHolder.CashPair.Companion.CASH_PRICE
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
class CashDetailView : Fragment(), KoinComponent {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = get<ViewDetailstBinding>(parameters = { parametersOf(inflater, container, this) })
        binding.cashName.text = arguments?.getString(CASH_NAME)
        binding.cashPrice.text = arguments?.getDouble(CASH_PRICE).toString()
        return binding.root
    }
}

