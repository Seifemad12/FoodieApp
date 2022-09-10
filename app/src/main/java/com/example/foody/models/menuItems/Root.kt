package com.example.foody.models.menuItems

class Root {
    var type: String? = null
    var menuItems: ArrayList<MenuItem>? = null
    var offset = 0
    var number = 0
    var totalMenuItems = 0
    var processingTimeMs = 0
    var expires: Long = 0
    var isStale = false
}