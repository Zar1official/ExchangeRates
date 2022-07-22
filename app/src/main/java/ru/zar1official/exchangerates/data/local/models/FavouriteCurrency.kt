package ru.zar1official.exchangerates.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavouriteCurrency(
    @PrimaryKey val cid: Int,
    @ColumnInfo(name = "name") val name: String,
)