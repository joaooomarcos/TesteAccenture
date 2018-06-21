package br.com.accenture.projetoaurora.helper

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

import br.com.accenture.projetoaurora.R

object FragmentTransactionHelper {

    fun replaceFragment(fragmentManager: FragmentManager, @IdRes container: Int, fragment: Fragment) {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(container, fragment)
                .addToBackStack(fragment.javaClass.simpleName)
                .commitAllowingStateLoss()
    }
}
