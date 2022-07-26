package ru.zar1official.exchangerates.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class FavouriteCurrency(
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String
)