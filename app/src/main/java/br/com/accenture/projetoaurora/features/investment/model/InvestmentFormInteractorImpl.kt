package br.com.accenture.projetoaurora.features.investment.model

import br.com.accenture.projetoaurora.BuildConfig
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class InvestmentFormInteractorImpl: InvestmentFormInteractor{

    private val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)

    var api = Retrofit.Builder().baseUrl(BuildConfig.SANTANDER_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiInvestmentForm::class.java)

    override fun getScreen(): Observable<InvestmentScreen> {
        return api.screens()
    }
}