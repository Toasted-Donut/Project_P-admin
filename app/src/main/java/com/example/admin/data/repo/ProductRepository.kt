package com.example.admin.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.admin.data.inetrfaces.ProductCategoryApi
import com.example.admin.data.inetrfaces.ProductDao
import com.example.admin.data.models.Category
import com.example.admin.data.models.Product
import com.example.admin.data.models.ProductCategory
import com.google.gson.Gson

class ProductRepository(
    private val dao: ProductDao,
    private val api: ProductCategoryApi) {


    suspend fun save(vararg category: Category){
        dao.save(*category)
    }
    suspend fun save(vararg product: Product){
        dao.save(*product)
    }
    fun getAllProducts(): LiveData<List<Product>> = dao.getAllProducts()

    fun getAllCategories(): LiveData<List<Category>> = dao.getAllCategories()
    suspend fun fetchCategories(){
        dao.save(*api.getCategories().map { it }.toTypedArray())
    }

    suspend fun fetchProducts(){
        dao.save(*api.getProducts().map { it }.toTypedArray())
    }

    suspend fun putProduct(product: Product){
        save(api.putProduct(product.name,product.categoryId))
    }

   fun getProductsWithCategory(): LiveData<List<ProductCategory>>{
        return dao.getProductsWithCategory()
    }
}