package ru.zar1official.exchangerates.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.zar1official.exchangerates.data.local.models.FavouriteCurrency

@Dao
interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: FavouriteCurrency)

    @Delete
    suspend fun deleteFavourite(favourite: FavouriteCurrency)

    @Query("SELECT * from favourites")
    fun getFavourites(): Flow<List<FavouriteCurrency>>
}