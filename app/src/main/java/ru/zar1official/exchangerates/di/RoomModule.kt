package ru.zar1official.exchangerates.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.zar1official.exchangerates.data.DataConstants
import ru.zar1official.exchangerates.data.local.AppDatabase
import ru.zar1official.exchangerates.data.local.RoomDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RoomDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DataConstants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRoomDao(database: AppDatabase): RoomDao {
        return database.dao()
    }
}