package com.example.admin.data.inetrfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.admin.data.models.Category
import com.example.admin.data.models.Product
import com.example.admin.data.models.ProductCategory

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg  product: Product)

    @Query("SELECT * FROM 'product'")
    fun getAllProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM 'category'")
    fun getAllCategories(): LiveData<List<Category>>



    @Transaction
    @Query("""
        SELECT product.name as name, product.categoryId as cat_id, category.category_name as cat_name
        FROM product
        JOIN category ON product.categoryId = category.category_id
    """)
    fun getProductsWithCategory():LiveData<List<ProductCategory>>
}