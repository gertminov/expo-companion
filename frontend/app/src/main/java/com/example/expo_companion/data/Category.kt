package com.example.expo_companion.data

import com.example.expo_companion.R

/**
 * This enum represents a question category.
 * It has a custom moshi adapter to create an instance of this Enum from a json string.
 * The "dbName" attribute has to match the "name" field in pocketbase.
 * You can get the name of the category (instead of the id) by calling "/api/collections/question/records?expand=category"
 */
enum class Category(override val dbName: String, override var icon: Int) : TabItem {
    NOTES("Notes", R.drawable.notes),
    REFLEXION("observation", R.drawable.oak),
    OBSERVATION("reflexion", R.drawable.leaf),
    EXPERIENCE("experience", R.drawable.maple),
    HIDDEN("hidden", R.drawable.lostquestion);

    /**
     * Singleton object, that returns a Category Enum from String
     */
    companion object {
        /**
         * Returns either an Enum which [Category.dbName] matches the input String or null
         * @param value dbName of the [Category] Enum
         */
        fun fromValueOrNull(value: String): Category? {
            return values().firstOrNull() { it.dbName == value }
        }
    }
}