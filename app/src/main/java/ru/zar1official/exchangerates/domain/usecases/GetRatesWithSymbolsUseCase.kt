package ru.zar1official.exchangerates.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.zar1official.exchangerates.domain.models.SymbolDomain
import ru.zar1official.exchangerates.domain.repository.Repository
import javax.inject.Inject

class GetRatesWithSymbolsUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(base: String, symbols: List<SymbolDomain>) =
        withContext(Dispatchers.IO) {
            kotlin.runCatching {
                if (symbols.isEmpty()) {
                    listOf()
                } else {
                    repository.getLatestRates(
                        base = base,
                        symbols = symbols
                    )
                }
            }
        }
}