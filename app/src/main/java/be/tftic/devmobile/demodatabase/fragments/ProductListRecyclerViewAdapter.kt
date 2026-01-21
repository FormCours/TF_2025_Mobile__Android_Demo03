package be.tftic.devmobile.demodatabase.fragments

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import be.tftic.devmobile.demodatabase.R

import be.tftic.devmobile.demodatabase.fragments.placeholder.PlaceholderContent.PlaceholderItem
import be.tftic.devmobile.demodatabase.databinding.FragmentProductItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ProductListRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<ProductListRecyclerViewAdapter.ViewHolder>() {

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
        holder.idView.text = item.id
        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}