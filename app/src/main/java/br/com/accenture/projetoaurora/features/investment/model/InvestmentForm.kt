package br.com.accenture.projetoaurora.features.investment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Screen(val title: String,
                  val fundName: String,
                  val definition: String,
                  val whatIs: String,
                  val risk: Int,
                  val riskTitle: String,
                  val infoTitle: String,
                  val moreInfo: MoreInfo,
                  val info: List<Info>,
                  val downInfo: List<Info>): Serializable{


}

