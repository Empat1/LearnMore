package com.example.learnmore.data.model

import java.util.*

data class Appeal(val appeal_id : Int,
                  val support_id:Int,
                  val user_id:Int,
                   var appeal_type:Int,
                  var appeal_date:Date,
                var appeal_description:String,
                var appeal_answer: String)
