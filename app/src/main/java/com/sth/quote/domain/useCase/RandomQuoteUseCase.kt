package com.sth.quote.domain.useCase

import com.sth.quote.data.response.QuoteResponse
import com.sth.quote.domain.repository.RandomQuoteRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RandomQuoteUseCase(private val repository: RandomQuoteRepository) {
    fun getRandomQuote(): Single<QuoteResponse> {
        return Single.create {
            repository.getRandomQuote().subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io()).subscribe(object : SingleObserver<QuoteResponse> {
                    override fun onSuccess(t: QuoteResponse) {
                        it.onSuccess(t)
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                        it.onError(e)
                    }

                })
        }
    }

}