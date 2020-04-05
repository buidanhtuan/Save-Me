package com.buidanhtuan.saveme.model

data class Detail(
    var id      : Int = 0,
    var id_user : Int = 0,
    var id_team : Int = 0,
    var type    : String = "",
    var title   : String = "",
    var content : String = "",
    var data    : String = "",
    var status  : String = "",
    var time    : String = ""
)