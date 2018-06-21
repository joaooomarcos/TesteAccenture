package br.com.accenture.projetoaurora.features.contactForm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.accenture.projetoaurora.R
import br.com.accenture.projetoaurora.features.contactForm.components.*
import br.com.accenture.projetoaurora.features.contactForm.model.Cell
import kotlinx.android.synthetic.main.fragment_contact_form.*

class ContactFormFragment: Fragment(), ContactFormView{

    private val presenter: ContactFormPresenter by lazy { ContactFormPresenterImpl(this) }

    private val components = arrayListOf<Component>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreate()
    }

    override fun configureClickListeners(){
        btn_retry_message.setOnClickListener {
            presenter.onRetryMessageClicked()
        }
    }

    override fun configureEditTextCells(cell: Cell){
        val viewHolder = EditTextComponent(activity!!, cell)
        viewHolder.injectToView(form_container)
        components.add(viewHolder)
    }

    override fun configureTextView(cell: Cell){
        val viewHolder = TextViewComponent(activity!!, cell)
        viewHolder.injectToView(form_container)
        components.add(viewHolder)
    }

    override fun configureCheckboxCells(cell: Cell){
        val viewHolder = CheckboxComponent(activity!!, cell)
        viewHolder.injectToView(form_container)
        components.add(viewHolder)
    }

    override fun configureButtonCells(cell: Cell){
        val viewHolder = ButtonComponent(activity!!, cell)
        viewHolder.injectToView(form_container)
        components.add(viewHolder)
        viewHolder.onClickListener {
            presenter.onSendClicked()
        }
    }

    override fun validateFields(): Boolean{
        return components.map { it.validate() }.reduce({component1, component2 -> component1 && component2})
    }

    override fun resetForm(){
        components.forEach { it.reset() }
    }

    override fun showSuccessView(){
        success_group.visibility = View.VISIBLE
        form_container.visibility = View.GONE
    }

    override fun hideSuccessView(){
        success_group.visibility = View.GONE
        form_container.visibility = View.VISIBLE
    }

    override fun showLoading(){
        form_container.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading(){
        form_container.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
    }

    companion object {
        fun newInstance() = ContactFormFragment()
    }
}