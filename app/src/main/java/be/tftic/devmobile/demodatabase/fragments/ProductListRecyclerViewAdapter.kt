package be.tftic.devmobile.demodatabase.fragments

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import be.tftic.devmobile.demodatabase.R

import be.tftic.devmobile.demodatabase.fragments.placeholder.PlaceholderContent.PlaceholderItem
import be.tftic.devmobile.demodatabase.databinding.FragmentProductItemBinding
import be.tftic.devmobile.demodatabase.models.Product

class ProductListRecyclerViewAdapter(
    private val onProductClick : (Long) -> Unit
) : RecyclerView.Adapter<ProductListRecyclerViewAdapter.ViewHolder>() {

    private var values: List<Product> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.tvName.text = item.name
        holder.tvEan13.text = item.ean13
        holder.tvPrice.text = "${item.price} €"

        holder.layout.setOnClickListener {
            onProductClick(item.id)
        }
    }

    override fun getItemCount(): Int = values.size

    fun updateData(products : List<Product>) {
        values = products
        notifyDataSetChanged() // Méthode simple -> D'autre méthode existe pour optimiser la modification
    }

    inner class ViewHolder(binding: FragmentProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val layout : LinearLayout = binding.productItem
        val tvName: TextView = binding.productItemName
        val tvEan13: TextView = binding.productItemEan13
        val tvPrice: TextView = binding.productItemPrice

        override fun toString(): String {
            return super.toString() + " '" + tvName.text + "'"
        }
    }

}