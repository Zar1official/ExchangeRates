package ru.zar1official.exchangerates.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.zar1official.exchangerates.domain.repository.Repository
import javax.inject.Inject

class GetRatesWithBaseUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(base: String) = withContext(Dispatchers.IO) {
        kotlin.runCatching { repository.getLatestRates(base = base) }
    }
}