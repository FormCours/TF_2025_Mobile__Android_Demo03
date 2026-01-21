package be.tftic.devmobile.demodatabase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.tftic.devmobile.demodatabase.R
import be.tftic.devmobile.demodatabase.db.dao.ProductDao
import be.tftic.devmobile.demodatabase.fragments.placeholder.PlaceholderContent
import be.tftic.devmobile.demodatabase.models.Product

/**
 * A fragment representing a list of Items.
 */
class ProductListFragment : Fragment() {

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
                productAdapter = ProductListRecyclerViewAdapter()
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

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ProductListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}