package com.buidanhtuan.saveme.view_model.data_base

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
        const val ROW_NOTE_TIME     = "note_time"
        const val ROW_NOTE_TAG      = "note_tag"
        const val ROW_NOTE_THEME    = "note_theme"
        const val QUERY_CREATE_NOTE = "CREATE TABLE IF NOT EXISTS $NOTE_TABLE " +
                "($ROW_NOTE_ID      INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$ROW_NOTE_USER_ID  INTEGER," +
                "$ROW_NOTE_TYPE     TEXT," +
                "$ROW_NOTE_TITLE    TEXT," +
                "$ROW_NOTE_CONTENT  TEXT," +
                "$ROW_NOTE_DATA     TEXT," +
                "$ROW_NOTE_STATUS   TEXT," +
                "$ROW_NOTE_TIME     TEXT," +
                "$ROW_NOTE_TAG      TEXT," +
                "$ROW_NOTE_THEME    TEXT)"
        const val QUERY_UPGRADE_NOTE = "DROP TABLE IF EXISTS $NOTE_TABLE"
        //TEAM
        const val TEAM_TABLE        = "TEAM"
        const val ROW_TEAM_ID       = "team_id"
        const val ROW_TEAM_NAME     = "team_name"
        const val ROW_TEAM_PASSWORD = "team_password"
        const val QUERY_CREATE_TEAM = "CREATE TABLE IF NOT EXISTS $TEAM_TABLE " +
                "($ROW_TEAM_ID          INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $ROW_TEAM_NAME         TEXT," +
                " $ROW_TEAM_PASSWORD     TEXT)"
        const val QUERY_UPGRADE_TEAM = "DROP TABLE IF EXISTS $TEAM_TABLE"
        //NOTE TEAM
        const val DETAIL_TABLE        = "DETAIL"
        const val ROW_DETAIL_ID       = "detail_id"
        const val ROW_DETAIL_USER_ID  = "detail_user_id"
        const val ROW_DETAIL_TEAM_ID  = "detail_team_id"
        const val ROW_DETAIL_TYPE     = "detail_type"
        const val ROW_DETAIL_TITLE    = "detail_title"
        const val ROW_DETAIL_CONTENT  = "detail_content"
        const val ROW_DETAIL_DATA     = "detail_data"
        const val ROW_DETAIL_STATUS   = "detail_status"
        const val ROW_DETAIL_TIME     = "detail_time"
        const val QUERY_CREATE_DETAIL = "CREATE TABLE IF NOT EXISTS $DETAIL_TABLE " +
                "($ROW_DETAIL_ID      INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $ROW_DETAIL_USER_ID  INTEGER," +
                " $ROW_DETAIL_TEAM_ID  INTEGER," +
                " $ROW_DETAIL_TYPE     TEXT," +
                " $ROW_DETAIL_TITLE    TEXT," +
                " $ROW_DETAIL_CONTENT  TEXT," +
                " $ROW_DETAIL_DATA     TEXT," +
                " $ROW_DETAIL_STATUS   TEXT," +
                " $ROW_DETAIL_TIME     TEXT)"
        const val QUERY_UPGRADE_DETAIL = "DROP TABLE IF EXISTS $DETAIL_TABLE"
        //LEADER
        const val LEADER_TABLE          = "LEADER"
        const val ROW_LEADER_ID         = "leader_id"
        const val ROW_LEADER_USER_ID    = "leader_user_id"
        const val ROW_LEADER_TEAM_ID    = "leader_team_id"
        const val QUERY_CREATE_LEADER = "CREATE TABLE IF NOT EXISTS $LEADER_TABLE " +
                "($ROW_LEADER_ID          INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $ROW_LEADER_USER_ID     TEXT," +
                " $ROW_LEADER_TEAM_ID     TEXT)"
        const val QUERY_UPGRADE_LEADER = "DROP TABLE IF EXISTS $USER_TABLE"
    }
}