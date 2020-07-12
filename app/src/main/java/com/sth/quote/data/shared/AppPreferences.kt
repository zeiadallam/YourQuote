package com.sth.quote.data.shared

import android.content.Context

object AppPreferences {
    private const val PREFERENCES_NAME = "app_preferences"

    fun setLastQuote(context: Context, quote: String) {
        val editor = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
        editor.putString("QUOTE", quote)
        editor.apply()
    }

    fun getLastQuote(context: Context): String {
        val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        return preferences.getString("QUOTE", "")!!
    }

    fun setQuoteAuth(context: Context, auth: String) {
        val editor = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
        editor.putString("AUTH", auth)
        editor.apply()
    }

    fun getQuoteAuth(context: Context): String {
        val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        return preferences.getString("AUTH", "")!!
    }
}