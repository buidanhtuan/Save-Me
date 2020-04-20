package com.buidanhtuan.saveme.view_model.data_base

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.buidanhtuan.saveme.model.Detail
import com.buidanhtuan.saveme.model.Note
import com.buidanhtuan.saveme.model.Team
import com.buidanhtuan.saveme.model.User

class Query(ctx : Context) : SQLiteOpenHelper(ctx,
    DataBase.DATABASE_NAME,null,
    DataBase.DATABASE_VERSION) {
    companion object {
        private lateinit var INSTANCE: Query
        private lateinit var database: SQLiteDatabase
        private var databaseOpen: Boolean = false

        fun initDatabaseInstance(ctx: Context): Query {
            INSTANCE = Query(ctx)
            return INSTANCE
        }
        //Insert
        fun insertUser(user : User): Long{
            checkData()
            val values = ContentValues()
            values.put(DataBase.ROW_USER_NAME, user.name)
            values.put(DataBase.ROW_USER_PASSWORD, user.password)
            return database.insert(DataBase.USER_TABLE, null, values)
        }
        fun insertTeam(team : Team): Long{
            checkData()
            val values = ContentValues()
            values.put(DataBase.ROW_TEAM_NAME,      team.name)
            values.put(DataBase.ROW_TEAM_PASSWORD,  team.password)
            return database.insert(DataBase.TEAM_TABLE, null, values)
        }
        fun insertNote(note : Note): Long{
            checkData()
            val values = ContentValues()
            values.put(DataBase.ROW_NOTE_USER_ID,   note.id_user)
            values.put(DataBase.ROW_NOTE_TYPE,      note.type)
            values.put(DataBase.ROW_NOTE_TITLE,     note.title)
            values.put(DataBase.ROW_NOTE_CONTENT,   note.content)
            values.put(DataBase.ROW_NOTE_DATA,      note.data)
            values.put(DataBase.ROW_NOTE_STATUS,    note.status)
            values.put(DataBase.ROW_NOTE_TIME,      note.time)
            values.put(DataBase.ROW_NOTE_TAG,       note.tag)
            values.put(DataBase.ROW_NOTE_THEME,     note.theme)
            return database.insert(DataBase.NOTE_TABLE, null, values)
        }
        fun insertDetail(detail: Detail): Long{
            checkData()
            val values = ContentValues()
            values.put(DataBase.ROW_DETAIL_USER_ID,   detail.id_user)
            values.put(DataBase.ROW_DETAIL_TEAM_ID,   detail.id_team)
            values.put(DataBase.ROW_DETAIL_TYPE,      detail.type)
            values.put(DataBase.ROW_DETAIL_TITLE,     detail.title)
            values.put(DataBase.ROW_DETAIL_CONTENT,   detail.content)
            values.put(DataBase.ROW_DETAIL_DATA,      detail.data)
            values.put(DataBase.ROW_DETAIL_STATUS,    detail.status)
            values.put(DataBase.ROW_DETAIL_TIME,      detail.time)
            return database.insert(DataBase.DETAIL_TABLE, null, values)
        }
        //Get data
        fun getUser(id : Int) : User {
            checkData()
            var user = User(0, "", "")
            val query = "SELECT * FROM USER where user_id = $id"
            val cursor = database.rawQuery(query,null)
            cursor.use {cur ->
                if(cursor.moveToFirst()){
                    do{
                        user.id         = cur.getInt    (cur.getColumnIndex(DataBase.ROW_USER_ID))
                        user.name       = cur.getString (cur.getColumnIndex(DataBase.ROW_USER_NAME))
                        user.password   = cur.getString (cur.getColumnIndex(DataBase.ROW_USER_PASSWORD))
                    } while (cursor.moveToNext())
                }
            }
            return user
        }
        fun getTeam(id : Int) : Team {
            checkData()
            var team = Team(0, "", "")
            val query = "SELECT * FROM TEAM where team_id = $id"
            val cursor = database.rawQuery(query,null)
            cursor.use {cur ->
                if(cursor.moveToFirst()){
                    do{
                        team.id         = cur.getInt    (cur.getColumnIndex(DataBase.ROW_TEAM_ID))
                        team.name       = cur.getString (cur.getColumnIndex(DataBase.ROW_TEAM_NAME))
                        team.password   = cur.getString (cur.getColumnIndex(DataBase.ROW_TEAM_PASSWORD))
                    } while (cursor.moveToNext())
                }
            }
            return team
        }
        fun getNote(id : Int) : Note {
            checkData()
            var note = Note(0, 0, "", "", "", "", "", "","")
            val query = "SELECT * FROM NOTE where note_id = $id"
            val cursor = database.rawQuery(query,null)
            cursor.use {cur ->
                if(cursor.moveToFirst()){
                    do{
                        note.id         = cur.getInt   (cur.getColumnIndex(DataBase.ROW_NOTE_ID))
                        note.id_user    = cur.getInt   (cur.getColumnIndex(DataBase.ROW_NOTE_USER_ID))
                        note.type       = cur.getString(cur.getColumnIndex(DataBase.ROW_NOTE_TYPE))
                        note.title      = cur.getString(cur.getColumnIndex(DataBase.ROW_NOTE_TITLE))
                        note.content    = cur.getString(cur.getColumnIndex(DataBase.ROW_NOTE_CONTENT))
                        note.data       = cur.getString(cur.getColumnIndex(DataBase.ROW_NOTE_DATA))
                        note.status     = cur.getString(cur.getColumnIndex(DataBase.ROW_NOTE_STATUS))
                        note.time       = cur.getString(cur.getColumnIndex(DataBase.ROW_NOTE_TIME))
                        note.tag        = cur.getString(cur.getColumnIndex(DataBase.ROW_NOTE_TAG))
                    } while (cursor.moveToNext())
                }
            }
            return note
        }
        fun getDetail(id : Int) : Detail {
            checkData()
            var detail = Detail(
                0,
                0,
                0,
                "",
                "",
                "",
                "",
                "",
                ""
            )
            val query = "SELECT * FROM DETAIL where detail_id = $id"
            val cursor = database.rawQuery(query,null)
            cursor.use {cur ->
                if(cursor.moveToFirst()){
                    do{
                        detail.id         = cur.getInt   (cur.getColumnIndex(DataBase.ROW_DETAIL_ID))
                        detail.id_user    = cur.getInt   (cur.getColumnIndex(DataBase.ROW_DETAIL_USER_ID))
                        detail.id_team    = cur.getInt   (cur.getColumnIndex(DataBase.ROW_DETAIL_TEAM_ID))
                        detail.type       = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_TYPE))
                        detail.title      = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_TITLE))
                        detail.content    = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_CONTENT))
                        detail.data       = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_DATA))
                        detail.status     = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_STATUS))
                        detail.time       = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_TIME))
                    } while (cursor.moveToNext())
                }
            }
            return detail
        }
        //Get all data
        fun getAllUser(): MutableList<User> {
            checkData()
            val data: MutableList<User> = ArrayList()
            val query = "SELECT * FROM USER"
            val cursor = database.rawQuery(query, null)
            cursor.use { cur ->
                if (cursor.moveToFirst()) {
                    do {

                        val user = User(0, "","")
                        user.id         = cur.getInt    (cur.getColumnIndex(DataBase.ROW_USER_ID))
                        user.name       = cur.getString (cur.getColumnIndex(DataBase.ROW_USER_NAME))
                        user.password   = cur.getString (cur.getColumnIndex(DataBase.ROW_USER_PASSWORD))
                        data.add(user)

                    } while (cursor.moveToNext())
                }
            }
            return data
        }
        fun getAllNoteOfUser(id : Int): MutableList<Note> {
            checkData()
            val data: MutableList<Note> = ArrayList()
            val query = "SELECT * FROM USER u JOIN NOTE n ON u.user_id = n.note_user_id where n.note_user_id = $id "
            val cursor = database.rawQuery(query, null)
            cursor.use { cur ->
                if (cursor.moveToFirst()) {
                    do {
                        val note = Note(0,0,"","","","","","","",0)
                        note.id         = cur.getInt   (cur.getColumnIndex(DataBase.ROW_NOTE_ID))
                        note.id_user    = cur.getInt   (cur.getColumnIndex(DataBase.ROW_NOTE_USER_ID))
                        note.type       = cur.getString(cur.getColumnIndex(DataBase.ROW_NOTE_TYPE))
                        note.title      = cur.getString(cur.getColumnIndex(DataBase.ROW_NOTE_TITLE))
                        note.content    = cur.getString(cur.getColumnIndex(DataBase.ROW_NOTE_CONTENT))
                        note.data       = cur.getString(cur.getColumnIndex(DataBase.ROW_NOTE_DATA))
                        note.status     = cur.getString(cur.getColumnIndex(DataBase.ROW_NOTE_STATUS))
                        note.time       = cur.getString(cur.getColumnIndex(DataBase.ROW_NOTE_TIME))
                        note.tag        = cur.getString(cur.getColumnIndex(DataBase.ROW_NOTE_TAG))
                        note.theme      = cur.getInt   (cur.getColumnIndex(DataBase.ROW_NOTE_THEME))
                        data.add(note)
                    } while (cursor.moveToNext())
                }
            }
            return data
        }
        fun getAllDetailOfUser(id : Int): MutableList<Detail> {
            checkData()
            val data: MutableList<Detail> = ArrayList()
            val query = "SELECT * FROM DETAIL d JOIN USER u ON  u.user_id = d.detail_user_id where u.user_id = $id"
            val cursor = database.rawQuery(query, null)
            cursor.use { cur ->
                if (cursor.moveToFirst()) {
                    do {
                        val detail = Detail(0,0,0,"","","","","","")
                        detail.id       = cur.getInt   (cur.getColumnIndex(DataBase.ROW_DETAIL_ID))
                        detail.id_user  = cur.getInt   (cur.getColumnIndex(DataBase.ROW_DETAIL_USER_ID))
                        detail.id_team  = cur.getInt   (cur.getColumnIndex(DataBase.ROW_DETAIL_TEAM_ID))
                        detail.type     = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_TYPE))
                        detail.title    = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_TITLE))
                        detail.content  = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_CONTENT))
                        detail.data     = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_DATA))
                        detail.status   = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_STATUS))
                        detail.time     = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_TIME))
                        data.add(detail)
                    } while (cursor.moveToNext())
                }
            }
            return data
        }
        fun getAllDetailOfTeam(id : Int): MutableList<Detail> {
            checkData()
            val data: MutableList<Detail> = ArrayList()
            val query = "SELECT * FROM DETAIL d JOIN TEAM t ON  d.detail_team_id = t.team_id where t.team_id = $id"
            val cursor = database.rawQuery(query, null)
            cursor.use { cur ->
                if (cursor.moveToFirst()) {
                    do {
                        val detail = Detail(0,0,0,"","","","","","")
                        detail.id       = cur.getInt   (cur.getColumnIndex(DataBase.ROW_DETAIL_ID))
                        detail.id_user  = cur.getInt   (cur.getColumnIndex(DataBase.ROW_DETAIL_USER_ID))
                        detail.id_team  = cur.getInt   (cur.getColumnIndex(DataBase.ROW_DETAIL_TEAM_ID))
                        detail.type     = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_TYPE))
                        detail.title    = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_TITLE))
                        detail.content  = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_CONTENT))
                        detail.data     = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_DATA))
                        detail.status   = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_STATUS))
                        detail.time     = cur.getString(cur.getColumnIndex(DataBase.ROW_DETAIL_TIME))
                        data.add(detail)
                    } while (cursor.moveToNext())
                }
            }
            return data
        }
        //Delete data
        fun deleteNote(id : Int) : Int{
            checkData()
            return database.delete(DataBase.NOTE_TABLE, "${DataBase.ROW_NOTE_ID} = $id", null)
        }
        //check data
        private fun checkData(){
            if (!databaseOpen) {
                database = INSTANCE.writableDatabase
                databaseOpen = true
                Log.i("Database", "Database Open")
            }
        }

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DataBase.QUERY_CREATE_USER)
        db?.execSQL(DataBase.QUERY_CREATE_NOTE)
        db?.execSQL(DataBase.QUERY_CREATE_TEAM)
        db?.execSQL(DataBase.QUERY_CREATE_DETAIL)
        db?.execSQL(DataBase.QUERY_CREATE_LEADER)
        Log.i("DATABASE", "DATABASE CREATED")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DataBase.QUERY_UPGRADE_USER)
        db?.execSQL(DataBase.QUERY_UPGRADE_NOTE)
        db?.execSQL(DataBase.QUERY_CREATE_TEAM)
        db?.execSQL(DataBase.QUERY_UPGRADE_DETAIL)
        db?.execSQL(DataBase.QUERY_UPGRADE_LEADER)
        Log.i("DATABASE", "DATABASE UPDATED")
    }

}