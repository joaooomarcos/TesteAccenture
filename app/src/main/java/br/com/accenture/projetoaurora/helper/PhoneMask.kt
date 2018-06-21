package br.com.accenture.projetoaurora.helper

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText

import com.jakewharton.rxbinding.widget.RxTextView

import org.reactivestreams.Subscription

import java.util.concurrent.TimeUnit

import io.reactivex.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import timber.log.Timber

object PhoneMask {

    private val mask11_plain = "(##) #####-####"
    private val mask10_plain = "(##) ####-####"

    fun unmask(s: String): String {
        return s.replace("[^0-9]*".toRegex(), "")
    }

    fun apply(editText: EditText): rx.Subscription {
        return RxTextView.textChanges(editText)
                .subscribeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .debounce(1, TimeUnit.MILLISECONDS, rx.android.schedulers.AndroidSchedulers.mainThread())
                .subscribe(object : Action1<CharSequence> {
                    internal var isUpdating: Boolean = false
                    internal var old = ""

                    override fun call(charSequence: CharSequence) {
                        val str = PhoneMask.unmask(charSequence.toString())
                        val mask: String
                        when (str.length) {
                            11 -> mask = mask11_plain
                            10 -> mask = mask10_plain
                            9 -> mask = mask10_plain
                            else -> mask = mask10_plain
                        }

                        var mascara = ""
                        if (isUpdating) {
                            old = str
                            isUpdating = false
                            return
                        }
                        var i = 0
                        for (m in mask.toCharArray()) {
                            if (m != '#' && str.length > old.length || m != '#' && str.length < old.length && str.length != i) {
                                mascara += m
                                continue
                            }

                            try {
                                mascara += str[i]
                            } catch (e: Exception) {
                                break
                            }

                            i++
                        }
                        isUpdating = true
                        editText.setText(mascara)
                        editText.setSelection(mascara.length)
                    }
                }, Action1 { throwable -> Timber.e(throwable) })
    }

}