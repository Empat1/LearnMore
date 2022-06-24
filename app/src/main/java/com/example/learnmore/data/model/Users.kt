package com.example.learnmore.data.model

import java.sql.Date

data class Users(var sub_id:Int?,
    var user_name: String? = null,
    var user_language: String? = null,
    var user_login: String? = null,
    var user_password: String? = null,
    var sub_end: Date? = null){


    var languages: List<Language>? = null
    var user_id:Int? = null;

}
