package br.com.accenture.projetoaurora.features.contactForm.components

import android.content.Context
import android.os.Build
import android.support.annotation.IdRes
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import br.com.accenture.projetoaurora.R
import br.com.accenture.projetoaurora.extensions.convertPixelToDp
import br.com.accenture.projetoaurora.features.contactForm.model.Cell
import kotlinx.android.synthetic.main.view_risk_component.view.*
import android.support.constraint.ConstraintSet



const val RISK_SELECTED = 8
const val RISK_UNSELECTED = 5

class RiskComponent: LinearLayout {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_risk_component, this, true)
    }

    constructor(context: Context): super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    fun configureRisk(risk: Int){
        risk_1.layoutParams.height = convertPixelToDp(context, RISK_UNSELECTED)
        risk_2.layoutParams.height = convertPixelToDp(context, RISK_UNSELECTED)
        risk_3.layoutParams.height = convertPixelToDp(context, RISK_UNSELECTED)
        risk_4.layoutParams.height = convertPixelToDp(context, RISK_UNSELECTED)
        risk_5.layoutParams.height = convertPixelToDp(context, RISK_UNSELECTED)

        when(risk){
            1 ->{
                risk_1.layoutParams.height = convertPixelToDp(context, RISK_SELECTED)
                configureArrow(R.id.risk_1)
            }
            2 ->{
                risk_2.layoutParams.height = convertPixelToDp(context, RISK_SELECTED)
                configureArrow(R.id.risk_2)
            }
            3 ->{
                risk_3.layoutParams.height = convertPixelToDp(context, RISK_SELECTED)
                configureArrow(R.id.risk_3)
            }
            4 ->{
                risk_4.layoutParams.height = convertPixelToDp(context, RISK_SELECTED)
                configureArrow(R.id.risk_4)
            }
            5 ->{
                risk_5.layoutParams.height = convertPixelToDp(context, RISK_SELECTED)
                configureArrow(R.id.risk_5)
            }
        }
    }

    private fun configureArrow(@IdRes risk_layout: Int) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(contraint_layout)
        constraintSet.connect(R.id.iv_arrow, ConstraintSet.BOTTOM, risk_layout, ConstraintSet.TOP, 0)
        constraintSet.connect(R.id.iv_arrow, ConstraintSet.END, risk_layout, ConstraintSet.END, 0)
        constraintSet.connect(R.id.iv_arrow, ConstraintSet.START, risk_layout, ConstraintSet.START, 0)
        constraintSet.applyTo(contraint_layout)
    }
}