package com.buidanhtuan.saveme.model

data class Note(
    var id      : Int = 0,
    var id_user : Int = 0,
    var type    : String = "",
    var title   : String = "",
    var content : String = "",
    var data    : String = "",
    var status  : String = "",
    var time    : String = "",
    var tag     : String = ""
)