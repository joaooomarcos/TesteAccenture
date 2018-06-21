package br.com.accenture.projetoaurora.features.investment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import br.com.accenture.projetoaurora.R
import br.com.accenture.projetoaurora.features.investment.model.Info
import br.com.accenture.projetoaurora.features.investment.model.MoreInfo
import kotlinx.android.synthetic.main.view_down_info_item.view.*


class DownInfoAdapter(val infos: List<Info>): RecyclerView.Adapter<DownInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_down_info_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = infos[position]
        holder.name.text = info.name
    }

    override fun getItemCount() = infos.size

     inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.name
    }
}