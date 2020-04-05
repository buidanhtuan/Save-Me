package com.buidanhtuan.saveme.view_model.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Query(ctx : Context) : SQLiteOpenHelper(ctx,DataBase.DATABASE_NAME,null,DataBase.DATABASE_VERSION) {
    companion object {
        private lateinit var INSTANCE: Query
        private lateinit var database: SQLiteDatabase
        private var databaseOpen: Boolean = false
        fun initDatabaseInstance(ctx: Context): Query {
            INSTANCE = Query(ctx)
            return INSTANCE
        }

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DataBase.QUERY_CREATE_USER)
        db?.execSQL(DataBase.QUERY_CREATE_NOTE)
        Log.i("DATABASE", "DATABASE CREATED")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DataBase.QUERY_UPGRADE_USER)
        db?.execSQL(DataBase.QUERY_UPGRADE_NOTE)
        Log.i("DATABASE", "DATABASE UPDATED")
    }
}