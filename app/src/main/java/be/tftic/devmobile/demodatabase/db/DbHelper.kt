package be.tftic.devmobile.demodatabase.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(context, DbContract.NAME, null, DbContract.VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        // Création de la base de donnée dans le smartphone
        db?.execSQL(DbContract.Product.SCRIPT_CREATE)
    }

    override fun onUpgrade( db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Mise à jours de la base de donnée dans le smartphone (Uniquement quand la version  du smartphone et de l'app sont différente !)

        // Solution 1 : Migration des données
        // → Executer des scripts pour passer de la version X à la version Y
        // → Objectif, mettre à jours la DB sans perte de donnée !

        // Solution 2 : On réinitialise la base de donnée
        // → Executer des scripts pour DROP toutes la db et ensuite, la re-crée
        // → Ne pas faire ça en production, c'est plutot pour le dev !
        db?.execSQL(DbContract.Product.SCRIPT_DROP);

        onCreate(db)
    }

}