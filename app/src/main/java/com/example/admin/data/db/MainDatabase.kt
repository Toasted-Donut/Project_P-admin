package com.example.admin.data.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.admin.data.inetrfaces.ProductDao
import com.example.admin.data.models.Category
import com.example.admin.data.models.Product


@Database(entities = [Product::class, Category::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {

    abstract fun getDao(): ProductDao

    companion object{
        @Volatile
        private var INSTANCE: MainDatabase? = null

        private const val DB_NAME = "mail_database"

        fun getDatabase(context: Context): MainDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}