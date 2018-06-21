package br.com.accenture.projetoaurora.features.investment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.accenture.projetoaurora.R
import br.com.accenture.projetoaurora.features.investment.model.Info
import br.com.accenture.projetoaurora.features.investment.model.MoreInfo
import br.com.accenture.projetoaurora.features.investment.model.Screen
import kotlinx.android.synthetic.main.fragment_investment.*


class InvestmentFragment: Fragment(), InvestmentView{

    private val presenter: InvestmentPresenter by lazy { InvestmentPresenterImpl(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_investment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreate()
    }

    override fun configureScreen(screen: Screen) {
        tv_subtitle.text = screen.title
        tv_fund_name.text = screen.fundName
        tv_what_is.text = screen.whatIs
        tv_definition.text = screen.definition
        tv_risk_title.text = screen.riskTitle
        tv_info_title.text = screen.infoTitle
        risk_component.configureRisk(screen.risk)
    }

    override fun configureInfoRecyclerview(infos: List<Info>){
        info_recycler_view.layoutManager = LinearLayoutManager(context)
        info_recycler_view.adapter = InfoAdapter(infos)
        info_recycler_view.isNestedScrollingEnabled = false
    }

    override fun configureMoreInfoRecyclerview(moreInfo: MoreInfo){
        val moreInfos = arrayListOf(Pair(getString(R.string.this_month), moreInfo.month),
                Pair(getString(R.string.this_year), moreInfo.year),
                Pair(getString(R.string.twelve_months), moreInfo.twelveMonth))

        more_info_recycler_view.layoutManager = LinearLayoutManager(context)
        more_info_recycler_view.adapter = MoreInfoAdapter(moreInfos)
        more_info_recycler_view.isNestedScrollingEnabled = false
    }

    override fun configureDownInfoRecyclerview(infos: List<Info>){
        down_info_recycler_view.layoutManager = LinearLayoutManager(context)
        down_info_recycler_view.adapter = DownInfoAdapter(infos)
        down_info_recycler_view.isNestedScrollingEnabled = false
    }

    override fun showLoading(){
        form_group.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading(){
        form_group.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
    }

    companion object {
        fun newInstance() = InvestmentFragment()
    }
}