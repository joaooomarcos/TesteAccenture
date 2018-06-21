package br.com.accenture.projetoaurora.features.investment.model

import io.reactivex.Observable


interface InvestmentFormInteractor{

    companion object {
        val instance : InvestmentFormInteractor by lazy { InvestmentFormInteractorImpl() }
    }

    fun getScreen(): Observable<InvestmentScreen>
}