package com.example.admin.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.admin.data.models.Product
import com.example.admin.data.models.ProductCategory
import com.example.admin.databinding.DialogSpinnerBinding
import com.example.admin.databinding.ProductItemBinding

class ProductsRecyclerAdapter(val spinnerAdapter: SpinnerAdapter, val mainViewModel: MainViewModel) : RecyclerView.Adapter<ProductsRecyclerAdapter.ProductViewHolder>() {

    private val allProducts = ArrayList<ProductCategory>()
    inner class ProductViewHolder(private val itemBinding: ProductItemBinding) : RecyclerView.ViewHolder(itemBinding.root){

        fun bindItem(product: ProductCategory, position: Int){
            itemBinding.productTxt.text = product.name
            itemBinding.categoryTxt.text = product.cat_name
            itemBinding.categoryTxt.paintFlags = (itemBinding.categoryTxt.paintFlags + Paint.UNDERLINE_TEXT_FLAG )
            itemBinding.categoryTxt.setOnLongClickListener {
                val builder = AlertDialog.Builder(this.itemView.context)
                val alertBinding = DialogSpinnerBinding.inflate(LayoutInflater.from(this.itemView.context))
                alertBinding.spinner.adapter = spinnerAdapter
                alertBinding.spinner.setSelection(product.cat_id,false)
//                alertBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                        mainViewModel.updateProduct(Product(product.name,position))
//                    }
//
//                    override fun onNothingSelected(p0: AdapterView<*>?) {
//                        TODO("Not yet implemented")
//                    }
//                }
                builder.setTitle(product.name)
                builder.setView(alertBinding.root)
                builder.setPositiveButton("Сохранить", DialogInterface.OnClickListener { dialogInterface, i ->
                    mainViewModel.updateProduct(Product(product.name,alertBinding.spinner.selectedItemPosition))
                    notifyItemChanged(position)
                })
                builder.setNegativeButton("Отмена",null)
                builder.show()
                return@setOnLongClickListener true
            }

        }
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product: ProductCategory = allProducts[position]
        holder.bindItem(product, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemBinding = ProductItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return allProducts.size
    }
    fun updateList(newList: List<ProductCategory>){
        this.allProducts.clear()
        this.allProducts.addAll(newList)
        this.notifyDataSetChanged()
    }
}