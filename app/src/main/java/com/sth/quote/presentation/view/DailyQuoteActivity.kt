package com.sth.quote.presentation.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProviders
import com.sth.quote.R
import kotlinx.android.synthetic.main.activity_main.*

class DailyQuoteActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var viewModel: RandomQuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, SavedStateViewModelFactory(application, this))
            .get(RandomQuoteViewModel::class.java)

        if (isNetworkAvailable()) {
            if (savedInstanceState == null) {
                simpleProgressBar.visibility = View.VISIBLE
                viewModel.getRandomQuote(this@DailyQuoteActivity)
            }
        } else {
            //Read from SP
            simpleProgressBar.visibility = View.GONE
            Toast.makeText(this, "Connection unavailable", Toast.LENGTH_SHORT).show()
            quote.text = " Quote : ${viewModel.getLastQuote(this@DailyQuoteActivity)}"
            author.text = " Author : ${viewModel.getAuth(this@DailyQuoteActivity)}"
        }

    }

    override fun onStart() {
        super.onStart()
        initObserver()
        refresh.setOnClickListener {
            if (isNetworkAvailable()) {
                simpleProgressBar.visibility = View.VISIBLE
                viewModel.getRandomQuote(this@DailyQuoteActivity)
            } else {
                simpleProgressBar.visibility = View.VISIBLE
                Toast.makeText(this, "Connection unavailable", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun isNetworkAvailable(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    private fun initObserver() {
        viewModel.observeRandomQuote().observe(this, Observer {
            simpleProgressBar.visibility = View.GONE
            quote.text = " Quote : ${it.en}"
            author.text = " Author : ${it.author}"

        })
        viewModel.observeErrorMessage().observe(this, Observer {
            simpleProgressBar.visibility = View.GONE
            Toast.makeText(this, "" + it, Toast.LENGTH_SHORT).show()
        })
    }
}