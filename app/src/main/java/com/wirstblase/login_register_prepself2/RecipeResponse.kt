package com.wirstblase.login_register_prepself2

data class RecipeResponse(
    val body: List<Recipe>,
    val errorMessage: String?,
    val success: Boolean
)