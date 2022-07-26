package ru.zar1official.exchangerates.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.zar1official.exchangerates.domain.models.SymbolDomain
import ru.zar1official.exchangerates.domain.repository.Repository
import javax.inject.Inject

class AddFavouriteUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(favourite: SymbolDomain) = withContext(Dispatchers.IO) {
        repository.addFavourite(favourite = favourite)
    }
}