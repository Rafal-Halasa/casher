package com.simcodic.casher.di

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.simcodic.casher.data.services.CashServiceI
import com.simcodic.casher.databinding.ViewCashListBinding
import com.simcodic.casher.databinding.ViewCashListItemBinding
import com.simcodic.casher.databinding.ViewCashListItemCashPairsBinding
import com.simcodic.casher.logic.interecptors.CashInterceptor
import com.simcodic.casher.logic.interecptors.CashInterceptorI
import com.simcodic.casher.ui.cash_detail.CashDetailViewModel
import com.simcodic.casher.ui.cash_list.CashListViewModel
import com.simcodic.casher.ui.cash_list.views.ListViewAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val viewModules = module {
    single { CashDetailViewModel() }
    single { CashListViewModel() }
}

val viewBindings = module {
    single {
        val inflate = ViewCashListBinding.inflate(get())
        inflate.viewModel = get<CashListViewModel>()
        inflate.getNewCash = get<CashListViewModel>()
        inflate.lifecycleOwner = get()
        return@single inflate
    }
    factory {
        ViewCashListItemBinding.inflate(LayoutInflater.from(get()), get(), false)
    }
    factory {
        ViewCashListItemCashPairsBinding.inflate(LayoutInflater.from(get()), null, false)
    }
}

val interceptors = module {
    single<CashInterceptorI> {
        CashInterceptor()
    }
}

val retrofit = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.apilayer.com/fixer/")
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }
}

val moshi = module {
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}
val services = module {
    single {
        get<Retrofit>().create(CashServiceI::class.java)
    }
}
val adapters = module {
    single {
        ListViewAdapter()
    }
}


