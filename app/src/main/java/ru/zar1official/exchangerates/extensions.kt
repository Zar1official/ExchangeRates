package ru.zar1official.exchangerates

fun <T> List<T>.update(index: Int, item: T): List<T> = toMutableList().apply { this[index] = item }
fun <T> List<T>.update(oldItem: T, newItem: T): List<T> =
    toMutableList().apply { this[this.indexOf(oldItem)] = newItem }