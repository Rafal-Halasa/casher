package com.simcodic.casher.logic.base.adapters

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simcodic.casher.data.models.Cash
import com.simcodic.casher.ui.cash_list.OnClick
import com.simcodic.casher.ui.cash_list.OnGetNewCash
import com.simcodic.casher.ui.cash_list.views.ListViewAdapter
import org.koin.java.KoinJavaComponent

@BindingAdapter(value = ["cash", "onGetNewCash", "onClickItemListener"], requireAll = false)
fun setCash(
    recyclerView: RecyclerView,
    cash: LiveData<List<Cash?>>,
    onGetNewCash: OnGetNewCash? = null,
    onClickItemListener: OnClick? = null
) {
    if (cash.value != null) {
        val adapter: ListViewAdapter by KoinJavaComponent.inject(ListViewAdapter::class.java)
        if (recyclerView.adapter == null) {
            cash.value?.let {
                adapter.cash = it
                adapter.onGetNewCash = onGetNewCash
                adapter.onClickListener = onClickItemListener
                recyclerView.adapter = adapter
                recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 1)
            }
        } else {
            cash.value?.let {
                adapter.cash = it
                adapter.notifyDataSetChanged()
            }
        }
    }
}
