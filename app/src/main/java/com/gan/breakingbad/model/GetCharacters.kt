package com.gan.breakingbad.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetCharacters(
    @field:SerializedName("char_id") var char_id: Int,
    @field:SerializedName("name") var name: String,
    @field:SerializedName("birthday") var birthday: String,
    @field:SerializedName("img") var img: String,
    @field:SerializedName("status") var status: String,
    @SerializedName("occupation")
    @Expose
    var occupation: Any? = null,
    @SerializedName("appearance")
    @Expose
    var apperance: Any? = null,
    @field:SerializedName("nickname") var nickname: String,
    @field:SerializedName("portrayed") var portrayed: String
)