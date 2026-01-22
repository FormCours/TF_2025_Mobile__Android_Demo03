package be.tftic.devmobile.demodatabase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import be.tftic.devmobile.demodatabase.R
import be.tftic.devmobile.demodatabase.db.dao.ProductDao

class ProductDetailFragment : Fragment() {

    companion object {
        const val ARG_PRODUCT_ID = "product-id"
    }

    private var productId: Long = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Récuperation de l'id du produit depuis les arguments recu par le fragment
        productId = arguments?.getLong(ARG_PRODUCT_ID) ?: throw RuntimeException("ProductId is required !");
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)

        // Récuperation des données depuis la DB
        val productDao = ProductDao(requireContext())
        productDao.openReadable()
        val product = productDao.getById(productId)
        productDao.close()

        // Modifie la view du fragment
        val tvContent = view.findViewById<TextView>(R.id.tv_frag_detail_content)
        if(product != null) {
            tvContent.text = "${product.name} ${if(product.inStock) "En stock" else "Epuisé"}"
        }
        else {
            tvContent.text = "Produit non trouvé !"
        }

        return view
    }
}