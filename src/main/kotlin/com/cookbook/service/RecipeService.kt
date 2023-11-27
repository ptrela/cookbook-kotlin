package com.cookbook.service

import com.cookbook.dto.RecipeDto
import com.cookbook.model.Recipe
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicInteger

@Service
class RecipeService {

    companion object {
        val idSequence = AtomicInteger()
        val recipesMap = mutableMapOf<Int, Recipe>()
    }

    fun getRecipes(): List<Recipe> =
        recipesMap
            .entries
            .sortedBy { it.key }
            .map { it.value }

    fun addRecipe(recipeName: String): Recipe {
        val id = idSequence.incrementAndGet()
        val recipe = Recipe(id = id, name = recipeName)

        recipesMap[id] = recipe
        return recipe
    }

    fun getRecipe(id: Int): Recipe? = recipesMap[id]

    fun updateRecipe(id: Int, recipeName: String): Recipe? {
        val recipe = recipesMap[id]
        val updatedRecipe = recipe?.copy(name = recipeName)

        recipesMap[id] = updatedRecipe!!
        return updatedRecipe
    }

    fun removeRecipe(id: Int): Recipe? = recipesMap.remove(id)
}