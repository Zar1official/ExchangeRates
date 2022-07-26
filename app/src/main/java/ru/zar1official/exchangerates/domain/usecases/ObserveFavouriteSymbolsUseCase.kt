package ru.zar1official.exchangerates.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.zar1official.exchangerates.domain.models.SymbolDomain
import ru.zar1official.exchangerates.domain.repository.Repository
import javax.inject.Inject

class ObserveFavouriteSymbolsUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(): Flow<List<SymbolDomain>> {
        return repository.observeFavouriteSymbols()
    }
}