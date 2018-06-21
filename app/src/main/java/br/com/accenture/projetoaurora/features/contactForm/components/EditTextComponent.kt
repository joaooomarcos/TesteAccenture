package br.com.accenture.projetoaurora.features.contactForm.components

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutCompat
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import br.com.accenture.projetoaurora.R
import br.com.accenture.projetoaurora.extensions.isValidEmail
import br.com.accenture.projetoaurora.features.contactForm.model.Cell
import br.com.accenture.projetoaurora.features.contactForm.model.TypeField
import br.com.accenture.projetoaurora.helper.PhoneMask
import com.rengwuxian.materialedittext.MaterialEditText


class EditTextComponent(val context: Context, val cell: Cell): Component {

    val editText = LayoutInflater.from(context).inflate(R.layout.view_edittext_component, null, false) as MaterialEditText

    init {
        editText.errorColor = ContextCompat.getColor(context, R.color.red)
        editText.floatingLabelText = cell.message
        editText.hint = cell.message
        configureInputType()

        val params = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, cell.topSpacing, 0, 0)
        editText.layoutParams = params
    }

    override fun injectToView(viewGroup: ViewGroup){
        viewGroup.addView(editText)
    }

    override fun reset() {
        editText.setText("")
    }

    override fun validate() = when(cell.typefield){
        TypeField.TEXT -> {
            val isValid = editText.text.toString().isNotEmpty() || !cell.required
            configureError(isValid)
            isValid
        }
        TypeField.TEL_NUMBER -> {
            val isValid = editText.text.toString().length >= 14 || !cell.required
            configureError(isValid)
            isValid
        }
        TypeField.EMAIL -> {
            val isValid = editText.text.toString().isValidEmail() || !cell.required
            configureError(isValid)
            isValid
        }
    }

    private fun configureInputType(){
        when(cell.typefield){
            TypeField.EMAIL -> editText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            TypeField.TEL_NUMBER -> {
                PhoneMask.apply(editText)
                editText.inputType = InputType.TYPE_CLASS_PHONE
                editText.keyListener = DigitsKeyListener.getInstance("0123456789")
            }
            else -> {}
        }
    }

    private fun configureError(isValid: Boolean) {
        if (!isValid) {
            editText.error = ""
        } else {
            editText.error = null
        }
    }
}