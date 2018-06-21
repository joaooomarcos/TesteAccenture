package br.com.accenture.projetoaurora.features.investment

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.MediumTest
import android.support.test.runner.AndroidJUnit4
import br.com.accenture.projetoaurora.features.home.MainActivity

import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.rule.ActivityTestRule
import br.com.accenture.projetoaurora.CustomMatchers
import br.com.accenture.projetoaurora.R
import br.com.accenture.projetoaurora.features.contactForm.PHONE_FIELD
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins
import org.hamcrest.Matchers.not

import org.junit.Before
import org.junit.Rule
import org.junit.After

@RunWith(AndroidJUnit4::class)
@MediumTest
class InvestmentTest {

    @get:Rule
    var rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, false)

    private var context: Context? = null
    private val webServer = InvestmentWebServer()

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getTargetContext()
        webServer.configure()

        RxJavaPlugins.setInitComputationSchedulerHandler(Rx2Idler.create("RxJava 2.x Computation Scheduler"))
        RxJavaPlugins.setInitIoSchedulerHandler(Rx2Idler.create("RxJava 2.x IO Scheduler"))
    }

    @After
    fun tearDown() {
        webServer.shutDown()
    }

    @Test
    fun testIfDataIsFilled() {
        configureInvestmentScreen()

        checkViewIsVisible("Fundos de investimento")
        checkViewIsVisible("Vinci Valorem FI Multimercado")
        checkViewIsVisible("O que Ã©?")
        checkViewIsVisible("O Fundo tem por objetivo proporcionar aos seus cotistas rentabilidade no longo prazo atravÃ©s de investimentos.")
        checkViewIsVisible("Grau de risco do investimento")

        checkViewIsVisible("Mais informaÃ§Ãµes sobre o investimento")
        checkViewIsVisible("Taxa de administraÃ§Ã£o")
        checkViewIsVisible("AplicaÃ§Ã£o inicial")
        checkViewIsVisible("MovimentaÃ§Ã£o mÃ\u00ADnima")
        checkViewIsVisible("Saldo mÃ\u00ADnimo")
        checkViewIsVisible("Resgate (valor bruto)")
        checkViewIsVisible("Cota (valor bruto)")
        checkViewIsVisible("Pagamento (valor bruto)")
        checkViewIsVisible("D+2")
        checkViewIsVisible("D+1")
        checkViewIsVisible("D+0")
        checkViewIsVisible("R\$ 5.000,00")
        checkViewIsVisible("R\$ 1.000,00")
        checkViewIsVisible("R\$ 10.000,00")
        checkViewIsVisible("0,50%")

    }

    private fun checkViewIsVisible(text: String) {
        onView(withText(text)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

    }

    private fun configureInvestmentScreen() {
        webServer.createSuccessRequest()
        rule.launchActivity(Intent(context, MainActivity::class.java))
    }
}
