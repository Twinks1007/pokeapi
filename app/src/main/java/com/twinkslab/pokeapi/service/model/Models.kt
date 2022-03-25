package com.twinkslab.pokeapi.service.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ApiModelPokemon(
    @SerializedName("name")
    val name: String?,
    @SerializedName("url")
    val url: String?,
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApiModelPokemon> {
        override fun createFromParcel(parcel: Parcel): ApiModelPokemon {
            return ApiModelPokemon(parcel)
        }

        override fun newArray(size: Int): Array<ApiModelPokemon?> {
            return arrayOfNulls(size)
        }
    }
}


data class Pokemon(
    @SerializedName("base_happiness")
    val base_happiness:Int?,
    @SerializedName("capture_rate")
    val capture_rate:Int?,
    @SerializedName("color")
    val color:ApiModelPokemon?,
    @SerializedName("egg_groups")
    val egg_groups:ArrayList<ApiModelPokemon?>,
    @SerializedName("evolution_chain")
    val evolution_chain:ApiModelPokemon?,

)

data class ApiChain(
    @SerializedName("chain")
    val chain: Chain?
)

data class Chain(
    @SerializedName("evolves_to")
    val evolves_to:ArrayList<Chain?>?,
    @SerializedName("species")
    val species:ApiModelPokemon?
)

data class SkillsApi(
    @SerializedName("abilities")
    val abilities:ArrayList<Ability?>?,
)
data class Ability(
    @SerializedName("ability")
    val ability:ApiModelPokemon
)


data class ApiResult<T>(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val result: T?
)