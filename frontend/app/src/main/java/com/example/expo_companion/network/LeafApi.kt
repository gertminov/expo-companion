package com.example.expo_companion.network

import com.example.expo_companion.data.Category
import com.example.expo_companion.data.Leaf
import com.example.expo_companion.network.client.RetroFitClient
import com.example.expo_companion.network.mapper.LeafResponseMapper
import retrofit2.Response
import retrofit2.http.*

private interface LeafApiService {
    @GET("collections/leaf/records?expand=category")
    suspend fun getLeafs(): LeafResponseMapper.LeafApiResponse

    @DELETE
    suspend fun deleteLeaf(@Url url: String): Response<Unit>

    @PATCH
    suspend fun updateLeaf(@Url url: String, @Body leaf: Map<String, String>)


}

object LeafApi {
    private const val leafUrl = "collections/leaf/records/"
    private fun getRetroFit(): LeafApiService {
        val retrofitService: LeafApiService by lazy {
            RetroFitClient.getRetroFit().create(LeafApiService::class.java)
        }
        return retrofitService
    }

    suspend fun getLeafs(): List<Leaf> {
        //TODO comment in for real use
        val leafApiResponse = getRetroFit().getLeafs()
        return LeafResponseMapper.fromApiSorted(leafApiResponse)

//        return offlineLeafs
    }

    suspend fun deleteLeaf(leaf: Leaf) {
        val url = leafUrl + leaf.id
        getRetroFit().deleteLeaf(url)
    }

    suspend fun updateLeaf(leaf: Leaf) {
        val url = leafUrl + leaf.id
        val requestBody  = mapOf(
                "nfcId" to leaf.nfcId,
                "leafNr" to leaf.leafNr.toString()
        )
        getRetroFit().updateLeaf(url, requestBody)
    }
}


private val offlineLeafs = listOf(
        Leaf("123445jasdkfkjas", "123445", Category.OBSERVATION, 1),
        Leaf("aldkdfjaksaldsdf", "234567", Category.HIDDEN, 2),
        Leaf("ü18j4jfkalsdkfjf", "344556", Category.REFLEXION, 3),
        Leaf("alskdjfjfalsdkfd", "444444", Category.EXPERIENCE, 4),
        Leaf("1askdfjaldfjfjdl", "555555", Category.HIDDEN, 5),
        Leaf("alskdfjaksdflall", "666666", Category.OBSERVATION, 6),
        Leaf("45383jasdfljwell", "777777", Category.REFLEXION, 7),
        Leaf("2jdk5j6lk3j4k3l4", "888888", Category.EXPERIENCE, 8),
        Leaf("l4i5uaosidivjwod", "999999", Category.EXPERIENCE, 9),
        Leaf("4ldiarudjalsejfl", "101010", Category.OBSERVATION, 10),
        Leaf("23l4kdjfk4laksdj", "121212", Category.OBSERVATION, 11),
)