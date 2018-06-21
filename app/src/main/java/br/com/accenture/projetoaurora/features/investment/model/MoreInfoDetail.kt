package br.com.accenture.projetoaurora.features.investment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MoreInfoDetail( @SerializedName("fund") val found: String,
                          @SerializedName("CDI") val cdi: String): Serializable{


}

