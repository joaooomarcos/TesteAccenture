package br.com.accenture.projetoaurora.features.contactForm

import br.com.accenture.projetoaurora.features.contactForm.model.ApiContactForm
import br.com.accenture.projetoaurora.features.contactForm.model.ContactFormInteractor
import br.com.accenture.projetoaurora.features.contactForm.model.ContactFormInteractorImpl
import br.com.accenture.projetoaurora.features.investment.model.ApiInvestmentForm
import br.com.accenture.projetoaurora.features.investment.model.InvestmentFormInteractor
import br.com.accenture.projetoaurora.features.investment.model.InvestmentFormInteractorImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ContactFormWebServer{

    private val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)

    private val json = "{ \"cells\": [ { \"id\": 1, \"type\": 2, \"message\": \"OlÃ¡, primeiro se apresente com o seu nome:\", \"typefield\": null, \"hidden\": false, \"topSpacing\": 60.0, \"show\": null, \"required\": false }, { \"id\": 2, \"type\": 1, \"message\": \"Nome completo\", \"typefield\": 1, \"hidden\": false, \"topSpacing\": 35.0, \"show\": null, \"required\": true }, { \"id\": 4, \"type\": 1, \"message\": \"Email\", \"typefield\": 3, \"hidden\": false, \"topSpacing\": 35.0, \"show\": null, \"required\": true }, { \"id\": 6, \"type\": 1, \"message\": \"Telefone\", \"typefield\": \"telnumber\", \"hidden\": false, \"topSpacing\": 10.0, \"show\": null, \"required\": true }, { \"id\": 3, \"type\": 4, \"message\": \"Gostaria de cadastrar meu email\", \"typefield\": null, \"hidden\": false, \"topSpacing\": 35.0, \"show\": 4, \"required\": false }, { \"id\": 7, \"type\": 5, \"message\": \"Enviar\", \"typefield\": null, \"hidden\": false, \"topSpacing\": 10.0, \"show\": null, \"required\": true } ] }"
    private val json_found = "{ \"screen\": { \"title\": \"Fundos de investimento\", \"fundName\": \"Vinci Valorem FI Multimercado\", \"whatIs\": \"O que Ã©?\", \"definition\": \"O Fundo tem por objetivo proporcionar aos seus cotistas rentabilidade no longo prazo atravÃ©s de investimentos.\", \"riskTitle\": \"Grau de risco do investimento\", \"risk\": 4, \"infoTitle\": \"Mais informaÃ§Ãµes sobre o investimento\", \"moreInfo\": { \"month\": { \"fund\": 0.3, \"CDI\": 0.3 }, \"year\": { \"fund\": 13.01, \"CDI\": 12.08 }, \"12months\": { \"fund\": 17.9, \"CDI\": 17.6 } }, \"info\": [ { \"name\": \"Taxa de administraÃ§Ã£o\", \"data\": \"0,50%\" }, { \"name\": \"AplicaÃ§Ã£o inicial\", \"data\": \"R\$ 10.000,00\" }, { \"name\": \"MovimentaÃ§Ã£o mÃ\u00ADnima\", \"data\": \"R\$ 1.000,00\" }, { \"name\": \"Saldo mÃ\u00ADnimo\", \"data\": \"R\$ 5.000,00\" }, { \"name\": \"Resgate (valor bruto)\", \"data\": \"D+0\" }, { \"name\": \"Cota (valor bruto)\", \"data\": \"D+1\" }, { \"name\": \"Pagamento (valor bruto)\", \"data\": \"D+2\" } ], \"downInfo\": [ { \"name\": \"Essenciais\", \"data\": null }, { \"name\": \"Desempenho\", \"data\": null }, { \"name\": \"Complementares\", \"data\": null }, { \"name\": \"Regulamento\", \"data\": null }, { \"name\": \"AdesÃ£o\", \"data\": null } ] } }"

    private var mockWebServer = MockWebServer()

    fun configure(){
        val interactor = ContactFormInteractor.instance as ContactFormInteractorImpl
        interactor.api =  Retrofit.Builder().baseUrl(mockWebServer.url("/"))
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiContactForm::class.java)

        val investmentFormInteractor = InvestmentFormInteractor.instance as InvestmentFormInteractorImpl
        investmentFormInteractor.api =  Retrofit.Builder().baseUrl(mockWebServer.url("/"))
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiInvestmentForm::class.java)
    }

    fun createSuccessRequest(){
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(json_found))
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(json))
    }

    fun shutDown(){
        mockWebServer.shutdown()
    }

}