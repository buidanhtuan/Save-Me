package com.buidanhtuan.saveme.view_model.database

class DatabaseConstant {
    companion object {
        const val DATABASE_NAME = "DB_NAME"
        const val DATABASE_VERSION = 1
        const val DATABASE_TABEL = "DB_TABEL"
        const val ROW_ID = "id"
        const val ROW_TITLE = "title"
        const val ROW_CONTENT = "content"
        const val QUERY_CREATE = "CREATE TABLE IF NOT EXISTS $DATABASE_TABEL ($ROW_ID INTEGER PRIMARY KEY AUTOINCREMENT, $ROW_TITLE TEXT, $ROW_CONTENT TEXT)"
        const val QUERY_UPGRADE = "DROP TABLE IF EXISTS $DATABASE_TABEL"
    }
}