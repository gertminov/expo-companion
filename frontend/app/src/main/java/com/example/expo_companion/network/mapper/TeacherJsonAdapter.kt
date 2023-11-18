package com.example.expo_companion.network.mapper

import com.example.expo_companion.data.Teacher
import com.squareup.moshi.*

class TeacherJsonAdapter: JsonAdapter<Teacher>() {

    @FromJson
    override fun fromJson(reader: JsonReader): Teacher {
        var id = ""
        var email = ""
        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "id" -> id = reader.nextString()
                "email" -> email = reader.nextString()
            }
        }
        reader.endObject()
        Teacher.id = id
        Teacher.email = email
        return Teacher
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Teacher?) {
        writer.beginObject()
        writer.name("id").value(value!!.id)
        writer.name("email").value(value.email)
        writer.endObject()
    }
}