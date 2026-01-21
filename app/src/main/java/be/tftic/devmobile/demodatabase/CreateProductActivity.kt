package be.tftic.devmobile.demodatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import be.tftic.devmobile.demodatabase.databinding.ActivityCreateProductBinding
import be.tftic.devmobile.demodatabase.db.dao.ProductDao
import be.tftic.devmobile.demodatabase.models.Product
import java.time.LocalDate

class CreateProductActivity : AppCompatActivity() {

    lateinit var binding : ActivityCreateProductBinding
    private lateinit var productDao : ProductDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Init Db
        productDao = ProductDao(this)

        // View
        binding = ActivityCreateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Listener
        binding.btnCreateProductAdd.setOnClickListener {
            addNewProduct()
        }
    }

    private fun addNewProduct() {

        // Récuperation des données de l'activité
        val product = Product(
            0,
            binding.etCreateProductName.text.toString(),
            binding.etCreateProductEan.text.toString(),
            binding.etCreateProductPrice.text.toString().toDouble(),
            if(binding.etCreateProductDesc.text.isEmpty()) null else binding.etCreateProductDesc.text.toString(),
            LocalDate.parse(binding.etCreateProductReleaseDate.text.toString()),
            true
        )

        // TODO Valider les données de l'utilisateur

        // Utilisation du DAO pour ajouter les données en DB
        productDao.openWritable()
        productDao.insert(product)
        productDao.close()

        // On cloture l'activité
        finish()
    }
}