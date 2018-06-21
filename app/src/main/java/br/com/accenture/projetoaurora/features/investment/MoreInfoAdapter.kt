package br.com.accenture.projetoaurora.features.investment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import br.com.accenture.projetoaurora.R
import br.com.accenture.projetoaurora.features.investment.model.MoreInfoDetail
import kotlinx.android.synthetic.main.view_more_info_item.view.*


class MoreInfoAdapter(val moreInfo: List<Pair<String, MoreInfoDetail>>) : RecyclerView.Adapter<MoreInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_more_info_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moreInfo = moreInfo[position]
        holder.title.text = moreInfo.first
        holder.found.text = "${moreInfo.second.found}%"
        holder.cdi.text = "${moreInfo.second.cdi}%"
    }

    override fun getItemCount() = moreInfo.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.title
        var found: TextView = itemView.found
        var cdi: TextView = itemView.cdi
    }
}