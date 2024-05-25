package com.example.admin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.admin.data.models.Category
import com.example.admin.data.models.Product
import com.example.admin.data.models.ProductCategory
import com.example.admin.data.repo.ProductRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(
    val repository: ProductRepository
) : ViewModel(){
    val allProducts: LiveData<List<ProductCategory>> = repository.getProductsWithCategory()
    val allCategories: LiveData<List<Category>> = repository.getAllCategories()
    fun updateProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            repository.putProduct(product)
        }
    }
    fun fetchData(){
        viewModelScope.launch {
            repository.fetchCategories()
            repository.fetchProducts()
        }
    }
}
