package com.sth.quote.data.response


import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    @SerializedName("author")
    val author: String,
    @SerializedName("en")
    val en: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("id")
    val authorId: String
)