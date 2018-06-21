package br.com.accenture.projetoaurora.features.contactForm.model

import io.reactivex.Observable


interface ContactFormInteractor{

    companion object {
        val instance : ContactFormInteractor by lazy { ContactFormInteractorImpl() }
    }

    fun getCells(): Observable<FormCells>
}