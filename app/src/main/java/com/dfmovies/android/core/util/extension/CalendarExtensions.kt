package com.dfmovies.android.core.util.extension

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd"
const val DEFAULT_DATE_PARSE_FORMAT = "dd/MM/yyyy"

fun String.getDateTime(): LocalDate? {
    return try {
        LocalDate.parse(this, DateTimeFormatter.ISO_DATE)
    } catch (e: Exception) {
        null
    }
}

fun String.getYear(): String {
    val date = this.getDateTime()
    return date?.year.toString()
}

fun String.getFormattedDateString(): String {
    val date = this.getDateTime()
    return date?.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_PARSE_FORMAT)).orEmpty()
}