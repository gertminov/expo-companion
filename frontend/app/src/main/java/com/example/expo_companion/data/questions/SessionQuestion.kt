package com.example.expo_companion.data.questions

import android.graphics.Bitmap
import android.util.Log
import com.example.expo_companion.data.Answer
import com.example.expo_companion.data.Category
import com.example.expo_companion.data.NoteSurface
import com.example.expo_companion.data.Teacher

class SessionQuestion
    : Question, NoteSurface {
    val teacher: Teacher
    val answer: Answer

    constructor(
        text: String,
        id: String,
        category: Category,
        answer: Answer,
        teacher: Teacher,
    ) : super(text, id, category) {
        this.teacher = teacher
        this.answer = answer
    }

    constructor(question: Question) : super(question.text, question.id, question.category) {
        this.teacher = Teacher
        this.answer = Answer(mutableListOf())
    }

    override fun saveBmpAndUpdate(bmp: Bitmap) {
        val sessionQuestion = UsedQuestions.usedQuestions.value[category]
        //only save the bitmap if the question is still in used questions (happens if question gets deleted
        sessionQuestion?.let {
            this.answer.content = mutableListOf(bmp)
            UsedQuestions.replaceQuestion(this)
            Log.i("Drawing", "saved Question")

        }
    }

    override fun getBmp(): Bitmap? {
        return  answer.content.getOrNull(0)
    }
}