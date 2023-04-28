package com.cryptocapital.data.remote.settings

class Setting {

    companion object {
        var BASE_URL: String = ""
        var IS_HEADER_REQUIRED: Boolean = false
        var SETOFFLINEDATAACCESS: Boolean = false
        var HEADER: HashMap<String, String>? = null
    }

}