package com.example.expo_companion.network.mapper

import com.example.expo_companion.data.Category
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

/**
 * parses Category enum from Category JSON object looking like this:
 * { "id": "somestring" // not important
 *  "name": "categoryName" // important
 *  }
 */
class CategoryEnumJsonAdapter : JsonAdapter<Category>() {
    @FromJson
    override fun fromJson(reader: JsonReader): Category? {

        return if (reader.peek() != JsonReader.Token.NULL) {
            var category: Category? = null

            reader.readObject {
                when (reader.nextName()) {
                    "name" -> category = Category.fromValueOrNull(reader.nextString())
                    else -> {
                        reader.skipValue()
                    }
                }
            }

            return category

        } else {
            reader.nextNull<Category>()
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Category?) {
        writer.value(value?.dbName)
    }

    private inline fun JsonReader.readObject(body: () -> Unit) {
        beginObject()
        while (hasNext()) {
            body()
        }
        endObject()
    }
}