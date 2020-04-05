package com.buidanhtuan.saveme.view_model.database

class DataBase {
    companion object {
        const val DATABASE_NAME = "DB_NAME"
        const val DATABASE_VERSION = 1
        //USER
        const val USER_TABLE        = "USER"
        const val ROW_USER_ID       = "user_id"
        const val ROW_USER_NAME     = "user_name"
        const val ROW_USER_PASSWORD = "user_password"
        const val QUERY_CREATE_USER = "CREATE TABLE IF NOT EXISTS $USER_TABLE " +
                "($ROW_USER_ID          INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$ROW_USER_NAME         TEXT," +
                "$ROW_USER_PASSWORD     TEXT)"
        const val QUERY_UPGRADE_USER = "DROP TABLE IF EXISTS $USER_TABLE"
        //NOTE USER
        const val NOTE_TABLE        = "NOTE"
        const val ROW_NOTE_ID       = "note_id"
        const val ROW_NOTE_USER_ID  = "note_user_id"
        const val ROW_NOTE_TYPE     = "note_type"
        const val ROW_NOTE_TITLE    = "note_title"
        const val ROW_NOTE_CONTENT  = "note_content"
        const val ROW_NOTE_DATA     = "note_data"
        const val ROW_NOTE_STATUS   = "note_status"
        const val QUERY_CREATE_NOTE = "CREATE TABLE IF NOT EXISTS $NOTE_TABLE " +
                "($ROW_NOTE_ID      INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$ROW_NOTE_USER_ID  INTEGER," +
                "$ROW_NOTE_TYPE     TEXT," +
                "$ROW_NOTE_TITLE    TEXT," +
                "$ROW_NOTE_CONTENT  TEXT)," +
                "$ROW_NOTE_DATA     TEXT," +
                "$ROW_NOTE_STATUS        TEXT"
        const val QUERY_UPGRADE_NOTE = "DROP TABLE IF EXISTS $NOTE_TABLE"
        //TEAM
        const val TEAM_TABLE        = "USER"
        const val ROW_TEAM_ID       = "team_id"
        const val ROW_TEAM_NAME     = "team_name"
        const val ROW_TEAM_PASSWORD = "team_password"
        const val QUERY_CREATE_TEAM = "CREATE TABLE IF NOT EXISTS $TEAM_TABLE " +
                "($ROW_TEAM_ID          INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $ROW_TEAM_NAME         TEXT," +
                " $ROW_TEAM_PASSWORD     TEXT)"
        const val QUERY_UPGRADE_TEAM = "DROP TABLE IF EXISTS $USER_TABLE"
        //NOTE TEAM
        const val DETAIL_TABLE        = "NOTE"
        const val ROW_DETAIL_ID       = "detail_id"
        const val ROW_DETAIL_USER_ID  = "detail_user_id"
        const val ROW_DETAIL_TEAM_ID  = "detail_team_id"
        const val ROW_DETAIL_TYPE     = "detail_type"
        const val ROW_DETAIL_TITLE    = "detail_title"
        const val ROW_DETAIL_CONTENT  = "detail_content"
        const val ROW_DETAIL_DATA     = "detail_data"
        const val ROW_DETAIL_STATUS   = "detail_status"
        const val QUERY_CREATE_DETAIL = "CREATE TABLE IF NOT EXISTS $NOTE_TABLE " +
                "($ROW_DETAIL_ID      INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $ROW_DETAIL_USER_ID  INTEGER," +
                " $ROW_DETAIL_TEAM_ID  INTEGER" +
                " $ROW_DETAIL_TYPE     TEXT," +
                " $ROW_DETAIL_TITLE    TEXT," +
                " $ROW_DETAIL_CONTENT  TEXT)," +
                " $ROW_DETAIL_DATA     TEXT," +
                " $ROW_DETAIL_STATUS   TEXT"
        const val QUERY_UPGRADE_DETAIL = "DROP TABLE IF EXISTS $NOTE_TABLE"
    }
}