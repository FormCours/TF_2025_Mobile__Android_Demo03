package be.tftic.devmobile.demodatabase.models

import java.time.LocalDate

data class Product(
    val id: Long = 0,
    val name: String,
    val ean13: String,
    val price: Double,
    val desc: String?,
    val releaseDate: LocalDate,
    val inStock: Boolean
)
