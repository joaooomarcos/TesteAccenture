package br.com.accenture.projetoaurora.features.home


class MainPresenterImpl(val view: MainView): MainPresenter{

    override fun onCreate(){
        view.configureClickListener()
        view.inflateInvestmentScreen()
        view.configureBtnInvestActive()
    }

    override fun onClickBtnContact() {
        view.inflateContactForm()
        view.configureContactActive()
    }

    override fun onClickBtnInvest() {
        view.inflateInvestmentScreen()
        view.configureBtnInvestActive()
    }
}