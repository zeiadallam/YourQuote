package com.sth.quote.domain.repository

import com.sth.quote.data.response.QuoteResponse
import io.reactivex.Single

interface RandomQuoteRepository{
    fun getRandomQuote():Single<QuoteResponse>
}