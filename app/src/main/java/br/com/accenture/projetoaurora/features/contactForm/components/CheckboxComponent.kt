package br.com.accenture.projetoaurora.features.contactForm.components

import android.content.Context
import android.support.v7.widget.LinearLayoutCompat
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import br.com.accenture.projetoaurora.R
import br.com.accenture.projetoaurora.features.contactForm.model.Cell


class CheckboxComponent(context: Context, cell: Cell): Component {

    val checkBox = LayoutInflater.from(context).inflate(R.layout.view_checkbox_component, null, false) as CheckBox

    init {
        checkBox.text = cell.message
        val params = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, cell.topSpacing, 0, 0)
        checkBox.layoutParams = params
    }

    override fun reset() {
        checkBox.isChecked = false
    }

    override fun injectToView(viewGroup: ViewGroup){
        viewGroup.addView(checkBox)
    }

    override fun validate() = true
}