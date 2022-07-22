package ru.zar1official.exchangerates.domain

import javax.inject.Inject

class RemoveFavouriteUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(){}
}