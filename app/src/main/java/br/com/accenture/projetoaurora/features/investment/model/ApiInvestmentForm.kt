package br.com.accenture.projetoaurora.features.investment.model

import io.reactivex.Observable
import retrofit2.http.GET


interface ApiInvestmentForm{

    @GET("fund.json")
    fun screens(): Observable<InvestmentScreen>
}