package br.com.accenture.projetoaurora.features.contactForm.components

import android.content.Context
import android.support.v7.widget.LinearLayoutCompat
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import br.com.accenture.projetoaurora.R
import br.com.accenture.projetoaurora.features.contactForm.model.Cell


class ButtonComponent(context: Context, cell: Cell): Component {

    val button = LayoutInflater.from(context).inflate(R.layout.view_button_component, null, false) as Button

    init {
        button.text = cell.message
        val params = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, cell.topSpacing, 0, 0)
        button.layoutParams = params
    }

    override fun injectToView(viewGroup: ViewGroup){
        viewGroup.addView(button)
    }

    override fun reset() {

    }

    fun onClickListener(listener: () -> Unit){
        button.setOnClickListener { listener() }
    }

    override fun validate() = true
}