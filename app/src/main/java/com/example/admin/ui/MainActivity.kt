package com.example.admin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.R
import com.example.admin.data.models.Category
import com.example.admin.data.models.Product
import com.example.admin.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var spinnerAdapter: SpinnerAdapter
    val mainViewModel: MainViewModel by lazy {
        val app = application as MyApp
        val viewModelProviderFactory = MainViewModelProviderFactory(app)
        ViewModelProvider(
            this,
            viewModelProviderFactory
        )[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.fetchData()

        spinnerAdapter = SpinnerAdapter(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, ArrayList<Category>())
        val productsRecyclerAdapter = ProductsRecyclerAdapter(spinnerAdapter, mainViewModel)

        binding.recView.layoutManager = LinearLayoutManager(this)
        binding.recView.adapter = productsRecyclerAdapter
        mainViewModel.allProducts.observe(this) { list ->
            list?.let {
                productsRecyclerAdapter.updateList(it)
            }
        }
        mainViewModel.allCategories.observe(this){list ->
            list?.let {
                spinnerAdapter.updateList(it)
            }
        }

    }
}