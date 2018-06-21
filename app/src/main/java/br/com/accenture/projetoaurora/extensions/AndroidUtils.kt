package br.com.accenture.projetoaurora.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.TypedValue
import br.com.accenture.projetoaurora.App


inline fun convertPixelToDp(context: Context, pixel: Int): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixel.toFloat(), context.resources.displayMetrics).toInt()
}
