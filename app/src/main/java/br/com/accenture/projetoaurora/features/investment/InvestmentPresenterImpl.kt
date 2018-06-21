package br.com.accenture.projetoaurora.features.investment

import br.com.accenture.projetoaurora.extensions.ioThread
import br.com.accenture.projetoaurora.features.contactForm.model.ContactFormInteractor
import br.com.accenture.projetoaurora.features.contactForm.model.Type
import br.com.accenture.projetoaurora.features.investment.model.InvestmentFormInteractor
import br.com.accenture.projetoaurora.features.investment.model.InvestmentFormInteractorImpl
import timber.log.Timber


class InvestmentPresenterImpl(val view: InvestmentView): InvestmentPresenter{

    private val interactor: InvestmentFormInteractor by lazy { InvestmentFormInteractor.instance }

    override fun onCreate(){
        loadInvestmentScreen()
    }

    private fun loadInvestmentScreen(){
        interactor.getScreen()
                .ioThread()
                .doOnTerminate { view.hideLoading() }
                .doOnSubscribe { view.showLoading() }
                .subscribe({ investmentScreen ->
                    view.configureInfoRecyclerview(investmentScreen.screen.info)
                    view.configureMoreInfoRecyclerview(investmentScreen.screen.moreInfo)
                    view.configureDownInfoRecyclerview(investmentScreen.screen.downInfo)
                    view.configureScreen(investmentScreen.screen)

                }, { t -> Timber.e(t, "InvestmentPresenter") })
    }
}