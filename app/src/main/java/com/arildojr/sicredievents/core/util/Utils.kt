package com.arildojr.sicredievents.core.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.arildojr.sicredievents.R

fun shareContent(title: String?, description: String?, context: Context) {
    val shareIntent = Intent(Intent.ACTION_SEND)

    shareIntent.type = "text/plain"

    shareIntent.putExtra(
        Intent.EXTRA_SUBJECT,
        title
    )

    shareIntent.putExtra(
        Intent.EXTRA_TEXT,
        description
    )

    context.startActivity(
        Intent.createChooser(
            shareIntent,
            context.getString(R.string.share_with)
        )
    )
}

fun hideKeyboard(view: View) {
    val inputMethodManager =
        view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun hasInternet(context: Context?): Boolean {
    val connectivityManager =
        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
        val network = connectivityManager?.activeNetwork
        val connection = connectivityManager?.getNetworkCapabilities(network)
        return connection != null && (
                connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    } else {
        val activeNetwork = connectivityManager?.activeNetworkInfo
        if (activeNetwork != null) {
            return (activeNetwork.type == ConnectivityManager.TYPE_WIFI ||
                    activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
        }
        return false
    }
}