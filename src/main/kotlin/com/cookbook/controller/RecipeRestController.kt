package com.cookbook.controller

import com.cookbook.dto.RecipeDto
import com.cookbook.dto.RecipeCreateDto
import com.cookbook.dto.RecipeUpdateDto
import com.cookbook.mapper.mapToRecipeDto
import com.cookbook.service.RecipeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/recipes")
@RestController
class RecipeRestController (private val recipeService: RecipeService) {

    @GetMapping
    fun getRecipes() = recipeService.getRecipes().map { mapToRecipeDto(it) }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addRecipe(@RequestBody recipeCreateDto: RecipeCreateDto):
    RecipeDto {
        val recipeName = recipeCreateDto.name!!
        val recipe = recipeService.addRecipe(recipeName)

        return mapToRecipeDto(recipe)
    }

    @GetMapping("/{id}")
    fun getRecipe(@PathVariable id: Int): ResponseEntity<RecipeDto> {
        val recipe = recipeService.getRecipe(id)

        return if (recipe != null) {
            ResponseEntity.ok(mapToRecipeDto(recipe))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{id}")
    fun updateRecipe(
        @PathVariable id: Int,
        @RequestBody recipeUpdateDto: RecipeUpdateDto
    ): ResponseEntity<RecipeDto> =
        recipeService.updateRecipe(id, recipeUpdateDto.name!!)
            ?.let { ResponseEntity.ok(mapToRecipeDto(it)) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun removeRecipe(@PathVariable id: Int): ResponseEntity<RecipeDto> {
        val removedRecipe = recipeService.removeRecipe(id)

        return if (removedRecipe != null) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
