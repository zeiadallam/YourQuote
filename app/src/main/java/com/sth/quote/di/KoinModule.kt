package com.sth.quote.di

import com.sth.quote.data.repository.RandomQuoteRepositoryImp
import com.sth.quote.domain.repository.RandomQuoteRepository
import com.sth.quote.domain.useCase.RandomQuoteUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val KoinModule = module {
    single { provideRetrofit() }
    single { RandomQuoteRepositoryImp(get()) as RandomQuoteRepository }
    single { RandomQuoteUseCase(get()) }
}

fun provideRetrofit(): Retrofit {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
    return Retrofit.Builder().baseUrl("https://programming-quotes-api.herokuapp.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(
            RxJava2CallAdapterFactory.create()
        ).build()
}