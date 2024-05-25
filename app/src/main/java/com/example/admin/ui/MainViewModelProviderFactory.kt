package com.example.admin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.admin.data.inetrfaces.ProductCategoryApi
import com.example.admin.data.repo.ProductRepository

class MainViewModelProviderFactory(val app: MyApp) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = MyApp.mailDB.getDao()
        val api = MyApp.retrofit.create(ProductCategoryApi::class.java)
        val productRepository = ProductRepository(
            dao = dao,
            api = api
        )
        val viewModel = MainViewModel(productRepository)
        return viewModel as T
    }
}