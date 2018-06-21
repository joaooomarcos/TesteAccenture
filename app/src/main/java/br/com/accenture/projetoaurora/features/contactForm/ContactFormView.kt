package br.com.accenture.projetoaurora.features.contactForm

import br.com.accenture.projetoaurora.features.contactForm.model.Cell


interface ContactFormView {
    fun hideLoading()
    fun showLoading()
    fun configureEditTextCells(cell: Cell)
    fun configureCheckboxCells(cell: Cell)
    fun configureTextView(cell: Cell)
    fun configureButtonCells(cell: Cell)
    fun hideSuccessView()
    fun showSuccessView()
    fun configureClickListeners()
    fun resetForm()
    fun validateFields():Boolean
}