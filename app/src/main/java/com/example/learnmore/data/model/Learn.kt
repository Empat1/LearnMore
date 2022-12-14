package com.example.learnmore.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Date

data class Learn(
    @SerializedName("learn_id") @Expose var learn_id : Int,
    var user_id : Int,
    var word_id : Int,
    var learn_goodRepetition: Int,
    var learn_last_repetition: Date)
