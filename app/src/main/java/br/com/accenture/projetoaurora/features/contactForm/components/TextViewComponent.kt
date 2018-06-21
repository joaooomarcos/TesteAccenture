package br.com.accenture.projetoaurora.features.contactForm.components

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import br.com.accenture.projetoaurora.R
import br.com.accenture.projetoaurora.features.contactForm.model.Cell


class TextViewComponent(context: Context, cell: Cell): Component {

    val textView = LayoutInflater.from(context).inflate(R.layout.view_textview_component, null, false) as TextView

    init {
        textView.text = cell.message
        textView.setPadding(0, cell.topSpacing,0,0)
    }

    override fun injectToView(viewGroup: ViewGroup){
        viewGroup.addView(textView)
    }

    override fun reset() {
    }

    override fun validate() = true
}