package br.com.accenture.projetoaurora.features.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import br.com.accenture.projetoaurora.helper.FragmentTransactionHelper
import br.com.accenture.projetoaurora.R
import br.com.accenture.projetoaurora.extensions.convertPixelToDp
import br.com.accenture.projetoaurora.features.contactForm.ContactFormFragment
import br.com.accenture.projetoaurora.features.investment.InvestmentFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity: AppCompatActivity(), MainView{

    private val presenter: MainPresenter by lazy { MainPresenterImpl(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onCreate()
    }

    override fun configureClickListener(){
        btn_invest.setOnClickListener {
            presenter.onClickBtnInvest()
        }

        btn_contact.setOnClickListener {
            presenter.onClickBtnContact()
        }
    }

    override fun configureContactActive() {
        btn_invest.layoutParams.height = convertPixelToDp(this, 70)
        btn_contact.layoutParams.height = convertPixelToDp(this, 72)

        btn_invest.background = ContextCompat.getDrawable(this, R.color.red)
        btn_contact.background = ContextCompat.getDrawable(this, R.color.guardsma_red)
    }

    override fun configureBtnInvestActive() {
        btn_invest.layoutParams.height = convertPixelToDp(this, 72)
        btn_contact.layoutParams.height = convertPixelToDp(this, 70)

        btn_invest.background = ContextCompat.getDrawable(this, R.color.guardsma_red)
        btn_contact.background = ContextCompat.getDrawable(this, R.color.red)
    }

    override fun inflateContactForm(){
        FragmentTransactionHelper.replaceFragment(supportFragmentManager, R.id.container, ContactFormFragment.newInstance())
    }

    override fun inflateInvestmentScreen(){
        FragmentTransactionHelper.replaceFragment(supportFragmentManager, R.id.container, InvestmentFragment.newInstance())
    }

    companion object {
        fun start(context: Context){
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

}