package com.cookbook.mapper

import com.cookbook.dto.RecipeDto
import com.cookbook.model.Recipe

fun mapToRecipeDto(recipe: Recipe) = RecipeDto(
    id = recipe.id!!,
    name = recipe.name
)