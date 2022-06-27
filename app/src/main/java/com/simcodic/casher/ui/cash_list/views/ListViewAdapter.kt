package com.simcodic.casher.ui.cash_list.views

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simcodic.casher.data.models.Cash
import com.simcodic.casher.databinding.ViewCashListItemBinding
import com.simcodic.casher.databinding.ViewCashListItemCashPairsBinding
import com.simcodic.casher.ui.cash_list.OnClick
import com.simcodic.casher.ui.cash_list.OnGetNewCash
import com.simcodic.casher.ui.cash_list.views.ViewDataHolder.CashDate
import com.simcodic.casher.ui.cash_list.views.ViewDataHolder.CashPair
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class ListViewAdapter(
    var onClickListener: OnClick? = null,
    var onGetNewCash: OnGetNewCash? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), KoinComponent {

    companion object {
        const val CASH_DATE_VIEW_TYPE_NUMBER = 1
        const val CASH_PAIR_VIEW_TYPE_NUMBER = 2
    }

    private var cashDataList = mutableListOf<ViewDataHolder>()
    var cash: List<Cash?> = mutableListOf()
        set(value) {
            cashDataList = mutableListOf()
            value.toList().forEach { cashElement ->
                cashDataList.add(CashDate(cashElement?.date ?: ""))
                cashDataList.addAll(cashElement?.rates?.toList()?.map { CashPair(it.first, it.second) } ?: listOf())
            }
            field = value
        }


    class DateViewHolder(
        private val viewBinding: ViewCashListItemBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(value: CashDate) {
            viewBinding.value.text = value.date
        }
    }

    class CashPairViewHolder(
        private val viewBinding: ViewCashListItemCashPairsBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(cashPair: CashPair, onClickListener: OnClick?) {
            viewBinding.cashPair = cashPair
            viewBinding.onClick = onClickListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            CASH_DATE_VIEW_TYPE_NUMBER -> DateViewHolder(get(parameters = { parametersOf(parent) }))
            CASH_PAIR_VIEW_TYPE_NUMBER -> CashPairViewHolder(get(parameters = { parametersOf(parent) }))
            else -> DateViewHolder(get(parameters = { parametersOf(parent) }))
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == itemCount - 3) {
            onGetNewCash?.getNewCash()
        }
        cashDataList[position].apply {
            when (this) {
                is CashDate -> if (holder is DateViewHolder) holder.bind(this)
                is CashPair -> if (holder is CashPairViewHolder) holder.bind(this, onClickListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = when (cashDataList[position]) {
        is CashDate -> CASH_DATE_VIEW_TYPE_NUMBER
        is CashPair -> CASH_PAIR_VIEW_TYPE_NUMBER
    }


    override fun getItemCount(): Int = cashDataList.size
}

sealed class ViewDataHolder {
    class CashDate(var date: String) : ViewDataHolder()
    class CashPair(var cashName: String, var price: Double) : ViewDataHolder() {
        companion object {
            const val CASH_NAME = "cashName"
            const val CASH_PRICE = "price"
        }

        fun getBundle(): Bundle = Bundle().apply {
            putString(CASH_NAME, cashName)
            putDouble(CASH_PRICE, price)
        }
    }
}
