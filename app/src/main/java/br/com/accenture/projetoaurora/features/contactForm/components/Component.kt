package br.com.accenture.projetoaurora.features.contactForm.components

import android.view.ViewGroup


interface Component{

    fun validate(): Boolean
    fun reset()

    fun injectToView(viewGroup: ViewGroup)
}

