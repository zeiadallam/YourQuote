package com.sth.quote.presentation.view

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sth.quote.data.response.QuoteResponse
import com.sth.quote.data.shared.AppPreferences
import com.sth.quote.domain.useCase.RandomQuoteUseCase
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class RandomQuoteViewModel(val state: SavedStateHandle) : ViewModel(), KoinComponent {

    private val randomQuoteUseCase by inject<RandomQuoteUseCase>()

    private val errorMessage = MutableLiveData<String>()
    private var quote = MutableLiveData<QuoteResponse>()

    fun observeRandomQuote(): MutableLiveData<QuoteResponse> {
        return quote
    }
        fun observeErrorMessage(): MutableLiveData<String> {
        return errorMessage
    }

    fun getRandomQuote(context: Context) {
        randomQuoteUseCase.getRandomQuote().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : SingleObserver<QuoteResponse> {
                    override fun onSuccess(t: QuoteResponse) {
                        quote.postValue(t)
                        setLastQuote(context,t.en)
                        setQuoteAuth(context,t.author)
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                        errorMessage.postValue(e.localizedMessage)
                    }
                }
            )
    }

    fun getLastQuote(context: Context)=AppPreferences.getLastQuote(context)
    fun getAuth(context: Context)=AppPreferences.getQuoteAuth(context)
    private fun setLastQuote(context: Context, quote: String) = AppPreferences.setLastQuote(context, quote)
    private fun setQuoteAuth(context: Context, author: String) =
        AppPreferences.setQuoteAuth(context, author)

}