package com.cookbook.repository

import com.cookbook.model.Recipe
import org.springframework.data.repository.ListCrudRepository
import org.springframework.data.repository.ListPagingAndSortingRepository
import org.springframework.data.repository.query.ListQueryByExampleExecutor
import org.springframework.stereotype.Repository

@Repository
interface RecipeRepository :
ListCrudRepository<Recipe, Int>,
ListPagingAndSortingRepository<Recipe, Int>,
ListQueryByExampleExecutor<Recipe>