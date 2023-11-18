package com.example.expo_companion.data.questions

import com.example.expo_companion.data.Category

open class Question(
    val text: String,
    val id: String,
    val category: Category,
) {
}