package com.example.expo_companion.data

import com.example.expo_companion.R

interface TabItem {
    val dbName: String
    val icon: Int

    companion object{
        object Notes: TabItem{
            override val dbName = "Notes"
            override val icon = R.drawable.notes
        }

        object Finish: TabItem{
            override val dbName = "Finish"
            override val icon = R.drawable.beenden
        }
    }
}