package br.com.accenture.projetoaurora.features.investment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MoreInfo(@SerializedName("month") val month: MoreInfoDetail,
                    @SerializedName("year") val year: MoreInfoDetail,
                    @SerializedName("12months") val twelveMonth: MoreInfoDetail): Serializable{


}

