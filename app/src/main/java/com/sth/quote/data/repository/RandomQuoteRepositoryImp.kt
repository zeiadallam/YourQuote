package com.sth.quote.data.repository

import com.sth.quote.data.response.QuoteResponse
import com.sth.quote.data.webservice.QuoteAPI
import com.sth.quote.domain.repository.RandomQuoteRepository
import io.reactivex.Single
import retrofit2.Retrofit

class RandomQuoteRepositoryImp(private val retrofit: Retrofit) : RandomQuoteRepository {
    private var api: QuoteAPI = retrofit.create(
        QuoteAPI::class.java
    )

    override fun getRandomQuote(): Single<QuoteResponse> {
        return api.getRandomQuote()
    }
}