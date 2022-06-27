package com.simcodic.casher.ui.cash_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.simcodic.casher.databinding.ViewCashListBinding
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class CashListView : Fragment(), KoinComponent {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val cashListBinding = get<ViewCashListBinding>(parameters = { parametersOf(inflater, container, this) })
        cashListBinding.lifecycleOwner = this
        return cashListBinding.root
    }

}