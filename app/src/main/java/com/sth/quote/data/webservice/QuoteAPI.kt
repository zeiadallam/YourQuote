package com.sth.quote.data.webservice

import com.sth.quote.data.response.QuoteResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface QuoteAPI {
    @GET("quotes/random")
    fun getRandomQuote(): Single<QuoteResponse>
}