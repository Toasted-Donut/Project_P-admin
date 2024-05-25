package com.example.admin.data.models

import androidx.room.ColumnInfo

data class ProductCategory (
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "cat_id") val cat_id: Int,
    @ColumnInfo(name = "cat_name") val cat_name: String
)