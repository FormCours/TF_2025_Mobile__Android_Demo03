package be.tftic.devmobile.demodatabase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import be.tftic.devmobile.demodatabase.R
import be.tftic.devmobile.demodatabase.db.dao.ProductDao
import be.tftic.devmobile.demodatabase.fragments.placeholder.PlaceholderContent
import be.tftic.devmobile.demodatabase.models.Product

/**
 * A fragment representing a list of Items.
 */
class ProductListFragment : Fragment() {

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }

    private var columnCount = 1
    private lateinit var productDao : ProductDao;
    private lateinit var productAdapter : ProductListRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }

        productDao = ProductDao(requireContext())
    }

    private fun getProduct() : List<Product> {
        productDao.openReadable()
        val products = productDao.getAll()
        productDao.close()

        return  products
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)

        // Config RecyclerView
        if (view is RecyclerView) {

            with(view) {
                // Gestion du layout de la liste
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                // Définition de l'adapteur
                productAdapter = ProductListRecyclerViewAdapter { productId ->
                    val bundle = bundleOf(ProductDetailFragment.ARG_PRODUCT_ID to productId)
                    findNavController().navigate(
                        R.id.action_ProductList_to_ProductDetail,
                        bundle
                    )
                }
                adapter = productAdapter
            }
        }
        return view
    }

    override fun onResume() {
        super.onResume()

        // Récuperation des produit
        val products = getProduct()

        // Modifcation des données de Adapter de la RecyclerView
        productAdapter.updateData(products)
    }

}