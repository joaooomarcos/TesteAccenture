package br.com.accenture.projetoaurora.features.contactForm

import br.com.accenture.projetoaurora.extensions.ioThread
import br.com.accenture.projetoaurora.features.contactForm.model.ContactFormInteractor
import br.com.accenture.projetoaurora.features.contactForm.model.Type
import timber.log.Timber


class ContactFormPresenterImpl(val view: ContactFormView) : ContactFormPresenter {

    private val interactor: ContactFormInteractor by lazy { ContactFormInteractor.instance }

    override fun onCreate() {
        view.configureClickListeners()
        loadContactFormCells()
    }

    override fun onSendClicked() {
        val isFieldValids = view.validateFields()
        if (isFieldValids) {
            view.showSuccessView()
        }
    }

    override fun onRetryMessageClicked() {
        view.hideSuccessView()
        view.resetForm()
    }

    private fun loadContactFormCells() {
        interactor.getCells()
                .ioThread()
                .flatMapIterable { it.cells }
                .filter { !it.hidden }
                .doOnTerminate { view.hideLoading() }
                .doOnSubscribe { view.showLoading() }
                .subscribe({ cell ->
                    when (cell.type) {
                        Type.TEXT -> {
                            view.configureTextView(cell)
                        }
                        Type.CHECKBOX -> {
                            view.configureCheckboxCells(cell)
                        }
                        Type.FIELD -> {
                            view.configureEditTextCells(cell)
                        }
                        Type.SEND -> {
                            view.configureButtonCells(cell)
                        }
                    }


                }, { t -> Timber.e(t, "ContactFormPresenter") })
    }
}