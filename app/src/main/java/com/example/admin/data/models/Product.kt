package com.example.admin.data.models
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(defaultValue = "product")
    @SerializedName("name")
    val name: String,
    @ColumnInfo(name = "categoryId")
    @SerializedName("id")
    val categoryId: Int
)