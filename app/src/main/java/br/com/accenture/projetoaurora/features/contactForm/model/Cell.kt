package br.com.accenture.projetoaurora.features.contactForm.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

enum class TypeField{
    @SerializedName("1")
    TEXT(),

    @SerializedName("telnumber")
    TEL_NUMBER(),

    @SerializedName("3")
    EMAIL()
}

enum class Type{
    @SerializedName("1")
    FIELD(),

    @SerializedName("2")
    TEXT(),

    @SerializedName("4")
    CHECKBOX(),

    @SerializedName("5")
    SEND()
}


data class Cell(val id: Int,
                val type: Type,
                val message: String,
                val typefield: TypeField,
                val hidden: Boolean,
                val topSpacing: Int,
                val show: Int,
                val required: Boolean): Serializable{


}

