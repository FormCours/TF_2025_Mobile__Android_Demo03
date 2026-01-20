package be.tftic.devmobile.demodatabase.db.dao

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.core.database.getStringOrNull
import be.tftic.devmobile.demodatabase.db.DbContract
import be.tftic.devmobile.demodatabase.db.DbHelper
import be.tftic.devmobile.demodatabase.models.Product
import java.time.LocalDate

class ProductDao(context: Context) {

    private val dbHelper = DbHelper(context)
    private var db : SQLiteDatabase? = null;

    //region Méthode d'acces à la base de donnée
    fun openWritable() {
        db = dbHelper.writableDatabase
    }

    fun openReadable() {
        db = dbHelper.readableDatabase
    }

    fun close() {
        db?.close()
        db = null
    }

    private fun requireDb() : SQLiteDatabase {
        return db ?:  throw RuntimeException("Database not open ! (╯°□°）╯︵ ┻━┻")
    }
    //endregion

    //region Méthode d'interaction avec les données (get, insert, delete, update)
    fun getAll() : List<Product> {
        val db = requireDb();

        // Requete pour un récuperer les données
        val cursor = db.query(
            DbContract.Product.TABLE_NAME,
            null, // Liste des colonnes (null → SELECT * FROM ...)
            null, // Clause WHERE
            null, // Argument de la clause WHERE
            null, // Clause GROUP BY
            null, // Clause HAVING
            "${DbContract.Product.NAME} ASC"
        )

        // Si aucun resultat dans le cursor, envoi d'une liste vide
        if(!cursor.moveToFirst()) {
            return emptyList()
        }

        // Parcours du cursor
        val result = mutableListOf<Product>()

        do {
            // Récuperer les données dans le format du modele (classe Product)
            val product: Product = cursorToProduct(cursor)

            // Ajout à la liste des resultats
            result.add(product)

        } while(cursor.moveToNext())

        // Cloture de la requete
        cursor.close()
        return result
    }

    private fun cursorToProduct(cursor: Cursor): Product {
        val product = Product(
            cursor.getLong(cursor.getColumnIndexOrThrow(DbContract.Product.ID)),
            cursor.getString(cursor.getColumnIndexOrThrow(DbContract.Product.NAME)),
            cursor.getString(cursor.getColumnIndexOrThrow(DbContract.Product.EAN13)),
            cursor.getDouble(cursor.getColumnIndexOrThrow(DbContract.Product.PRICE)),
            cursor.getStringOrNull(cursor.getColumnIndexOrThrow(DbContract.Product.DESC)),
            LocalDate.parse(cursor.getString(cursor.getColumnIndexOrThrow(DbContract.Product.RELEASE_DATE))),
            cursor.getDouble(cursor.getColumnIndexOrThrow(DbContract.Product.IN_STOCK)) == 1.0
        )

        return product
    }

    //endregion
}