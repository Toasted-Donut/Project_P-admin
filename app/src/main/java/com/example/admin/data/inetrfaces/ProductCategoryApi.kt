package com.example.admin.data.inetrfaces

import com.example.admin.data.models.Category
import com.example.admin.data.models.Product
import retrofit2.http.Field
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part

interface ProductCategoryApi {

    @GET("product")
    suspend fun getProducts(): List<Product>

    @FormUrlEncoded
    @PUT("product")
    suspend fun putProduct(@Field("id") productName: String, @Field("categoryId") categoryId: Int): Product

    @GET("category")
    suspend fun getCategories(): List<Category>

}