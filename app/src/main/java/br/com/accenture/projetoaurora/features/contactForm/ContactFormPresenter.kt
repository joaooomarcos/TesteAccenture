package br.com.accenture.projetoaurora.features.contactForm


interface ContactFormPresenter {
    fun onCreate()
    fun onSendClicked()
    fun onRetryMessageClicked()
}