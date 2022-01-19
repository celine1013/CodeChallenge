package com.example.codechallenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class CurrencyInfo(
    @PrimaryKey
    var id: String = "",
    var name: String = "",
    var symbol: String = ""
)
