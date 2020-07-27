package com.example.wisproapi.retrofit_models

import com.google.gson.annotations.SerializedName

open class BaseRequest {
    @SerializedName("status")  val status: Integer? = null
    @SerializedName("meta") val meta: Meta? = null
    @SerializedName("pagination") val pagination: Pagination? = null
}

class Meta {
    @SerializedName("object") val objectt: String? = null
    @SerializedName("pagination") val pagination_info: Pagination? = null
}
class Pagination {
    @SerializedName("total_records") val total_records: String? = null
    @SerializedName("total_pages") val total_pages: Integer? = null
    @SerializedName("per_page") val per_page: Integer? = null
    @SerializedName("current_page") val current_page: Integer? = null
}