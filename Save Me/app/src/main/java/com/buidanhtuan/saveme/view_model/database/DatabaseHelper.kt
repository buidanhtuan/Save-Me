package com.buidanhtuan.saveme.view_model.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.buidanhtuan.saveme.model.Note

class DatabaseHelper(ctx: Context) : SQLiteOpenHelper(ctx, DatabaseConstant.DATABASE_NAME, null, DatabaseConstant.DATABASE_VERSION) {
    companion object {
        private lateinit var INSTANCE: DatabaseHelper
        private lateinit var database: SQLiteDatabase
        private var databaseOpen: Boolean = false
        fun initDatabaseInstance(ctx: Context): DatabaseHelper {
            INSTANCE = DatabaseHelper(ctx)
            return INSTANCE
        }

        fun insertData(note: Note): Long {
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true
                Log.i("Database", "Database Open")
            }
            val values = ContentValues()
            values.put(DatabaseConstant.ROW_TITLE, note.title)
            values.put(DatabaseConstant.ROW_CONTENT, note.content)
            return database.insert(DatabaseConstant.DATABASE_TABEL, null, values)
        }

        fun update(note: Note,st : String): Int {
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true

                Log.i("Database", "Database Open")
            }

            val values = ContentValues()
            if (st.contains("title")) values.put(DatabaseConstant.ROW_TITLE, note.title)
            if (st.contains("content")) values.put(DatabaseConstant.ROW_CONTENT, note.content)
            return database.update(
                DatabaseConstant.DATABASE_TABEL,
                values,
                "${DatabaseConstant.ROW_ID} = ${note.id}",
                null
            )
        }

        fun getAllData(): MutableList<Note> {
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true

                Log.i("Database", "Database Open")
            }

            val data: MutableList<Note> = ArrayList()
            val cursor = database.rawQuery("SELECT * FROM ${DatabaseConstant.DATABASE_TABEL}", null)
            cursor.use { cur ->
                if (cursor.moveToFirst()) {
                    do {

                        val note = Note(0,"","")
                        note.id = cur.getInt(cur.getColumnIndex(DatabaseConstant.ROW_ID))
                        note.title = cur.getString(cur.getColumnIndex(DatabaseConstant.ROW_TITLE))
                        note.content = cur.getString(cur.getColumnIndex(DatabaseConstant.ROW_CONTENT))
                        data.add(note)

                    } while (cursor.moveToNext())
                }
            }
            return data
        }
        fun getData(id: Int) : Note{
            var note = Note(0,"","")
            var data: MutableList<Note> = ArrayList()
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true
                Log.i("Database", "Database Open")
            }
            data = getAllData()
            for(i in 0 until data.size){
                if(data[i].id == id){
                    note = data[i]
                }
            }
            return note
        }
    }
    fun closeDatabase() {
        if (database.isOpen && databaseOpen) {
            database.close()
            databaseOpen = false
            Log.i("Database", "Database close")
        }
    }
    fun deleteData(id: Int): Int {
        if (!databaseOpen) {
            database = INSTANCE.writableDatabase
            databaseOpen = true

            Log.i("Database", "Database Open")
        }
        return database.delete(
            DatabaseConstant.DATABASE_TABEL,
            "${DatabaseConstant.ROW_ID} = $id",
            null
        )
    }
    fun deleteAllData() {
        if (!databaseOpen) {
            database = INSTANCE.writableDatabase
            databaseOpen = true

            Log.i("Database", "Database Open")
        }
        database.delete(DatabaseConstant.DATABASE_TABEL,null,null)
    }
    fun updateData(note: Note): Int {
        if (!databaseOpen) {
            database = INSTANCE.writableDatabase
            databaseOpen = true

            Log.i("Database", "Database Open")
        }

        val values = ContentValues()
        values.put(DatabaseConstant.ROW_TITLE, note.title)
        values.put(DatabaseConstant.ROW_CONTENT, note.content)
        return database.update(
            DatabaseConstant.DATABASE_TABEL,
            values,
            "${DatabaseConstant.ROW_ID} = ${note.id}",
            null
        )
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DatabaseConstant.QUERY_CREATE)
        Log.i("DATABASE", "DATABASE CREATED")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DatabaseConstant.QUERY_UPGRADE)
        Log.i("DATABASE", "DATABASE UPDATED")
    }
}