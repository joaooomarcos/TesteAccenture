package br.com.accenture.projetoaurora.features.home


interface MainView {
    fun inflateContactForm()
    fun inflateInvestmentScreen()
    fun configureClickListener()
    fun configureContactActive()
    fun configureBtnInvestActive()
}