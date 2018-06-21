package br.com.accenture.projetoaurora.features.investment

import br.com.accenture.projetoaurora.features.investment.model.Info
import br.com.accenture.projetoaurora.features.investment.model.MoreInfo
import br.com.accenture.projetoaurora.features.investment.model.Screen

interface InvestmentView {
    fun showLoading()
    fun hideLoading()
    fun configureScreen(screen: Screen)
    fun configureDownInfoRecyclerview(infos: List<Info>)
    fun configureInfoRecyclerview(infos: List<Info>)
    fun configureMoreInfoRecyclerview(moreInfo: MoreInfo)
}
