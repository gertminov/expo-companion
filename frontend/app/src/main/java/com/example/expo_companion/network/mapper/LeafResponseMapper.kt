package com.example.expo_companion.network.mapper

import com.example.expo_companion.data.Category
import com.example.expo_companion.data.Leaf

object LeafResponseMapper {
    /**
     * Maps the parsed JSON Response from collections/leaf/records?expand=category
     * list of [Leaf]
     */
    fun fromApi(response: LeafApiResponse): List<Leaf> {
        return response.items.map { apiLeaf ->
            Leaf(
                apiLeaf.id,
                apiLeaf.nfcId,
                apiLeaf.expand.category,
                apiLeaf.leafNr
            )
        }
    }

    fun fromApiSorted(response: LeafApiResponse): List<Leaf> {
        val leafList = fromApi(response)
        return leafList.sortedBy { it.leafNr }
    }

    data class LeafApiResponse(val items: List<ApiLeaf>)

    data class ApiLeaf(
        val id: String,
        val nfcId: String,
        val leafNr: Int,
        val expand: CategoryExpand
    )
    data class CategoryExpand(
        val category: Category
    )
}