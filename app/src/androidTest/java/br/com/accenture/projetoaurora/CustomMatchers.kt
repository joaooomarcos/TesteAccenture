package br.com.accenture.projetoaurora

import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.EditText

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object CustomMatchers {

    fun hasEditTextError(expectedErrorText: String?): Matcher<View> {
        return object : TypeSafeMatcher<View>() {

            public override fun matchesSafely(view: View): Boolean {
                if (view !is EditText) {
                    return false
                }

                val error = view.error
                return expectedErrorText == error
            }

            override fun describeTo(description: Description) {}
        }
    }

}
