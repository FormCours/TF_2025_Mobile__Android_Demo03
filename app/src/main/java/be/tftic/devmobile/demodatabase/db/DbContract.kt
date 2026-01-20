package be.tftic.devmobile.demodatabase.db

object DbContract {

    // Info de la base de données
    val NAME: String = "example.db"
    val VERSION: Int = 1

    // Info des tables
    object Product {
        // Nom de la table
        val TABLE_NAME = "product"

        // Nom des colonnes
        val ID = "_id"  // → Ne pas oublier le underscore
        val NAME = "name"
        val EAN13 = "ean13"
        val PRICE = "price"
        val DESC = "desc"
        val RELEASE_DATE = "release_date"
        val IN_STOCK = "stock"

        // Scripts
        val SCRIPT_CREATE =
            "CREATE TABLE $TABLE_NAME ( " +
                    "$ID INT PRIMARY KEY AUTOINCREMENT, " +
                    "$NAME VARCHAR(50), " +
                    "$EAN13 VARCHAR(13), " +
                    "$PRICE REAL, " +
                    "$DESC VARCHAR(255), " +
                    "$RELEASE_DATE TEXT, " +
                    "$IN_STOCK BOOLEAN" +
                    ");"

        val SCRIPT_DROP =
            "DROP TABLE $TABLE_NAME;"
    }


}