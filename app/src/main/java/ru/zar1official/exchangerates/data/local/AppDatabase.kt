package ru.zar1official.exchangerates.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.zar1official.exchangerates.data.local.models.FavouriteCurrency

@Database(entities = [FavouriteCurrency::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): RoomDao
}