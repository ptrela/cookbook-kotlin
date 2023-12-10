package com.cookbook.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Immutable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Immutable
@Table("recipes")
data class Recipe (
    @Id
    @Column("id")
    val id: Int? = null,

    @Column("name")
    val name: String? = null
)