package br.com.accenture.projetoaurora.features.contactForm

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
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins
import org.hamcrest.Matchers.not

import org.junit.Before
import org.junit.Rule
import org.junit.After

const val NAME_FIELD = "Nome completo"
const val EMAIL_FIELD = "Email"
const val SEND_FIELD = "Enviar"
const val PHONE_FIELD = "Telefone"
const val LABEL_FIELD = "OlÃ¡, primeiro se apresente com o seu nome:"
const val CHECKBOX_FIELD = "Gostaria de cadastrar meu email"
const val ERROR_MESSAGE = ""

@RunWith(AndroidJUnit4::class)
@MediumTest
class ContactFormTest {

    @get:Rule
    var rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, false)

    private var context: Context? = null
    private val contactFormWebServer = ContactFormWebServer()

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getTargetContext()
        contactFormWebServer.configure()

        RxJavaPlugins.setInitComputationSchedulerHandler(Rx2Idler.create("RxJava 2.x Computation Scheduler"))
        RxJavaPlugins.setInitIoSchedulerHandler(Rx2Idler.create("RxJava 2.x IO Scheduler"))
    }

    @After
    fun tearDown() {
        contactFormWebServer.shutDown()
    }

    @Test
    fun testRetryButton() {
        configureContactForm()

        Espresso.onView(ViewMatchers.withHint(PHONE_FIELD)).perform(typeText("81995614241"))
        Espresso.onView(ViewMatchers.withHint(NAME_FIELD)).perform(typeText("Felipe Joffer"))
        Espresso.onView(ViewMatchers.withHint(EMAIL_FIELD)).perform(typeText("felipejmartins@gmail.com"), closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withText(SEND_FIELD)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.message_success)).check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.title_success)).check(matches(isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.btn_retry_message)).perform(click())

        Espresso.onView(ViewMatchers.withId(R.id.message_success)).check(matches(not(isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.title_success)).check(matches(not(isDisplayed())))

        Espresso.onView(ViewMatchers.withHint(PHONE_FIELD)).check(matches(withText("")))
        Espresso.onView(ViewMatchers.withHint(NAME_FIELD)).check(matches(withText("")))
        Espresso.onView(ViewMatchers.withHint(EMAIL_FIELD)).check(matches(withText("")))
    }

    @Test
    fun testSuccessMessage() {
        configureContactForm()

        Espresso.onView(ViewMatchers.withHint(PHONE_FIELD)).perform(typeText("81995614241"))
        Espresso.onView(ViewMatchers.withHint(NAME_FIELD)).perform(typeText("Felipe Joffer"))
        Espresso.onView(ViewMatchers.withHint(EMAIL_FIELD)).perform(typeText("felipejmartins@gmail.com"))
        Espresso.onView(ViewMatchers.withText(SEND_FIELD)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.message_success)).check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.title_success)).check(matches(isDisplayed()))
    }

    @Test
    fun testIsPhoneFieldValid() {
        configureContactForm()

        Espresso.onView(ViewMatchers.withHint(PHONE_FIELD)).perform(typeText("81995614241"))
        Espresso.onView(ViewMatchers.withText(SEND_FIELD)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withHint(PHONE_FIELD)).check(matches(CustomMatchers.hasEditTextError(null)))
    }

    @Test
    fun testIsPhoneFieldInvalid() {
        configureContactForm()

        Espresso.onView(ViewMatchers.withHint(PHONE_FIELD)).perform(typeText("9956-14"))
        Espresso.onView(ViewMatchers.withText(SEND_FIELD)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withHint(PHONE_FIELD)).check(matches(CustomMatchers.hasEditTextError(ERROR_MESSAGE)))
    }

    @Test
    fun testIsPhoneFieldRequired() {
        configureContactForm()

        Espresso.onView(ViewMatchers.withHint(PHONE_FIELD)).check(matches(CustomMatchers.hasEditTextError(null)))
        Espresso.onView(ViewMatchers.withText(SEND_FIELD)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withHint(PHONE_FIELD)).check(matches(CustomMatchers.hasEditTextError(ERROR_MESSAGE)))
    }

    @Test
    fun testIsNameFieldValid() {
        configureContactForm()

        Espresso.onView(ViewMatchers.withHint(NAME_FIELD)).perform(typeText("Felipe Joffer Martins"))
        Espresso.onView(ViewMatchers.withText(SEND_FIELD)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withHint(NAME_FIELD)).check(matches(CustomMatchers.hasEditTextError(null)))
    }

    @Test
    fun testIsNameFieldRequired() {
        configureContactForm()

        Espresso.onView(ViewMatchers.withHint(NAME_FIELD)).check(matches(CustomMatchers.hasEditTextError(null)))
        Espresso.onView(ViewMatchers.withText(SEND_FIELD)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withHint(EMAIL_FIELD)).check(matches(CustomMatchers.hasEditTextError(ERROR_MESSAGE)))
    }

    @Test
    fun testIsEmailFieldRequired() {
        configureContactForm()

        Espresso.onView(ViewMatchers.withHint(EMAIL_FIELD)).check(matches(CustomMatchers.hasEditTextError(null)))
        Espresso.onView(ViewMatchers.withText(SEND_FIELD)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withHint(EMAIL_FIELD)).check(matches(CustomMatchers.hasEditTextError(ERROR_MESSAGE)))
    }

    @Test
    fun testIsEmailFieldValid() {
        configureContactForm()

        Espresso.onView(ViewMatchers.withHint(EMAIL_FIELD)).perform(typeText("felipejmartins@gmail.com"))
        Espresso.onView(ViewMatchers.withText(SEND_FIELD)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withHint(EMAIL_FIELD)).check(matches(CustomMatchers.hasEditTextError(null)))
    }

    @Test
    fun testIsEmailFieldInvalid() {
        configureContactForm()

        Espresso.onView(ViewMatchers.withHint(EMAIL_FIELD)).perform(typeText("invalidEmail.com"))
        Espresso.onView(ViewMatchers.withText(SEND_FIELD)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withHint(EMAIL_FIELD)).check(matches(CustomMatchers.hasEditTextError(ERROR_MESSAGE)))
    }

    @Test
    fun testHasPhoneField() {
        configureContactForm()
        Espresso.onView(ViewMatchers.withHint(PHONE_FIELD)).check(matches(isDisplayed()))
    }

    @Test
    fun testHasLabelField() {
        configureContactForm()
        Espresso.onView(ViewMatchers.withText(LABEL_FIELD)).check(matches(isDisplayed()))
    }

    @Test
    fun testHasNameField() {
        configureContactForm()
        Espresso.onView(ViewMatchers.withHint(NAME_FIELD)).check(matches(isDisplayed()))
    }

    @Test
    fun testHasEmailField() {
        configureContactForm()
        Espresso.onView(ViewMatchers.withHint(EMAIL_FIELD)).check(matches(isDisplayed()))
    }

    @Test
    fun testHasCheckBoxEmailField() {
        configureContactForm()
        Espresso.onView(ViewMatchers.withText(CHECKBOX_FIELD)).check(matches(isDisplayed()))
    }

    @Test
    fun testHasSendField() {
        configureContactForm()
        Espresso.onView(ViewMatchers.withText(SEND_FIELD)).check(matches(isDisplayed()))
    }

    private fun configureContactForm() {
        contactFormWebServer.createSuccessRequest()
        rule.launchActivity(Intent(context, MainActivity::class.java))
        onView(withId(R.id.btn_contact)).perform(click())
    }
}
