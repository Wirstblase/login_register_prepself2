package com.wirstblase.login_register_prepself2

data class Recipe(
    val description: String,
    val favourites: Int,
    val id: Int,
    val name: String,
    val nutritionalInfo: NutritionalInfo,
    val thumbnail: String
)