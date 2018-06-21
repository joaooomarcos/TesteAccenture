package br.com.accenture.projetoaurora.features.contactForm.model

import io.reactivex.Observable
import retrofit2.http.GET


interface ApiContactForm{

    @GET("cells.json")
    fun cells(): Observable<FormCells>

}