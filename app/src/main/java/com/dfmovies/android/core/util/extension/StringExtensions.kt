package com.dfmovies.android.core.util.extension

import android.net.Uri
import android.os.Build
import android.text.Html
import androidx.annotation.RequiresApi
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun String?.isNotEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotEmpty != null)
    }
    return this.isNullOrEmpty().not()
}

fun String?.isNullOrEmpty(): Boolean {
    return this == null || this == ""
}

fun String?.withColorString(): String {
    return when {
        this.isNullOrEmpty() -> {
            "#000000"
        }
        this?.startsWith("#").orTrue().not() -> "#$this"
        else -> this!!
    }
}

fun String.toHtmlString(): String {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        @Suppress("DEPRECATION")
        return Html.fromHtml(this).toString()
    }
}

fun Any?.withBrackets(): String = "(" + this.toString() + ")"

fun Number?.formatWithDecimal(): String =
    (NumberFormat.getNumberInstance(Locale.GERMAN) as DecimalFormat).format(this)

fun Number.formatOneDecimal(): String = this.toDouble().toBigDecimal()
    .setScale(1, RoundingMode.UP).toDouble().toString()

fun Any?.appendWithPipe(any: Any?): String = this.toString() + " | " + any.toString()

fun Any?.appendWith(character: String?, any: Any?): String =
    this.toString() + character + any.toString()

fun String.removeSpaces() = replace(" ", "")

fun String?.removeAllSpaces() = this?.let { replace("\\s".toRegex(), "") }

@Suppress("MagicNumber")
fun String.creditCardBinCode() = removeSpaces().removeSuffix("*").substring(0, 6)

fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }

fun String.toUriOrNull() = runCatching { Uri.parse(this) }.getOrNull()

fun String.digitsOnly(): String {
    val regex = Regex("[^0-9]")
    return regex.replace(this, "")
}

fun String.alphaNumericOnly(): String {
    val regex = Regex("[^A-Za-z0-9 ]")
    return regex.replace(this, "")
}

fun String.replaceLeadingZeros(): String {
    val regex = Regex("^0+(?!$)")
    return regex.replace(this, "")
}

fun CharSequence.getSpans(prefix: Char): List<IntArray> {
    val spans = ArrayList<IntArray>()

    val pattern = Pattern.compile("$prefix\\w+")
    val matcher = pattern.matcher(this)

    // Check all occurrences
    while (matcher.find()) {
        val currentSpan = IntArray(2)
        currentSpan[0] = matcher.start()
        currentSpan[1] = matcher.end()
        spans.add(currentSpan)
    }
    return spans
}


fun String.toIntOrZero(): Int = this.toIntOrNull() ?: 0

fun String?.toLongOrZero(): Long = this?.toLongOrNull() ?: 0

val String.containsLatinLetter: Boolean
    get() = matches(Regex(".*[A-Za-z].*"))

val String.containsDigit: Boolean
    get() = matches(Regex(".*[0-9].*"))

val String.isAlphanumeric: Boolean
    get() = matches(Regex("[A-Za-z0-9]*"))

val String.hasLettersAndDigits: Boolean
    get() = containsLatinLetter && containsDigit

val String.isIntegerNumber: Boolean
    get() = toIntOrNull() != null

val String.toDecimalNumber: Boolean
    get() = toDoubleOrNull() != null

fun String.isValidEmail(): Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

/**
 * Tc No = d1 d2 d3 d4 d5 d6 d7 d8 d9 c1 c2

c1 = ( (d1 + d3 + d5 + d7 + d9) * 7 - (d2 + d4 + d6 + d8) ) mod10

c2 = ( d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8 + d9 + c1 ) mod10
 */
@Suppress("MagicNumber")
fun String.isValidTckn(): Boolean {
    if (this.length != 11) return false

    val digit1 = this[0].toString().toInt()
    val digit2 = this[1].toString().toInt()
    val digit3 = this[2].toString().toInt()
    val digit4 = this[3].toString().toInt()
    val digit5 = this[4].toString().toInt()
    val digit6 = this[5].toString().toInt()
    val digit7 = this[6].toString().toInt()
    val digit8 = this[7].toString().toInt()
    val digit9 = this[8].toString().toInt()
    val digit10 = this[9].toString().toInt()
    val digit11 = this[10].toString().toInt()

    val firstSum: Int = digit1 + digit3 + digit5 + digit7 + digit9

    val secondSum: Int = digit2 + digit4 + digit6 + digit8

    val isValidMode10 = (firstSum * 7 - secondSum) % 10 == digit10

    val isValidMode10in11 = (firstSum + secondSum + digit10) % 10 == digit11

    return this[0] != '0' && isValidMode10 && isValidMode10in11
}

infix fun <T : Any> List<T?>?.appendWith(seperator: String): String {
    val listsWithoutNulls = this?.filterNotNull()
    return listsWithoutNulls
        ?.foldIndexed(StringBuilder()) { index: Int, stringBuilder: StringBuilder, element: T ->
            stringBuilder.append(element.toString())
            if (index != listsWithoutNulls.size - 1) {
                stringBuilder.append(seperator)
            }
            return@foldIndexed stringBuilder
        }?.toString() ?: ""
}

@Suppress("MagicNumber", "MaxLineLength")
fun String.toFlagEmoji(): String {
    // 1. It first checks if the string consists of only 2 characters: ISO 3166-1 alpha-2 two-letter country codes (https://en.wikipedia.org/wiki/Regional_Indicator_Symbol).
    if (this.length != 2) {
        return this
    }

    val countryCodeCaps =
        this.toUpperCase() // upper case is important because we are calculating offset
    val firstLetter = Character.codePointAt(countryCodeCaps, 0) - 0x41 + 0x1F1E6
    val secondLetter = Character.codePointAt(countryCodeCaps, 1) - 0x41 + 0x1F1E6

    // 2. It then checks if both characters are alphabet
    if (!countryCodeCaps[0].isLetter() || !countryCodeCaps[1].isLetter()) {
        return this
    }

    return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
}

fun String?.phoneNumberWithoutLeadingPlus(): String {
    return this?.let {
        if (it.startsWith("+")) {
            it.substring(1, this.length)
        } else {
            it
        }
    } ?: this.orEmpty()
}

fun String.withCountryPath(): String {
    return "${this}countries/"
}

fun String.withCurrencyPath(): String {
    return "${this}currencies/"
}

fun String.withCurrencyDisabledPath(): String {
    return "${this}currencies_disabled/"
}

fun String.withTransactionsPath(): String {
    return "${this}transactions/"
}

fun String.withNotificationsPath(): String {
    return "${this}news_center/"
}

fun String.withGamingBrandPath(): String {
    return "${this}gaming/brand/"
}

fun String.toPng(): String {
    return "$this.png"
}

fun String.withPlusPrefix(): String {
    return "+$this"
}

fun String.withMinusPrefix(): String {
    return "-$this"
}

fun String.withPdfPrefix(): String {
    return "$this.pdf"
}

fun String.withHttps(): String {
    return if (this.startsWith("https://") || this.startsWith("http://")) this
    else "https://$this"
}

@Suppress("TooGenericExceptionCaught")
fun String?.getTimeUnitValue(): TimeUnit {
    return this?.let {
        try {
            TimeUnit.valueOf(it)
        } catch (e: Exception) {
            TimeUnit.SECONDS
        }
    } ?: TimeUnit.SECONDS
}

fun parseMoneyValue(
    value: String,
    groupingSeparator: String,
    currencySymbol: String
): String =
    value.replace(groupingSeparator, "").replace(currencySymbol, "")

fun parseMoneyValueWithLocale(
    locale: Locale,
    value: String,
    groupingSeparator: String,
    currencySymbol: String
): Number {
    val valueWithoutSeparator = parseMoneyValue(value, groupingSeparator, currencySymbol)
    return try {
        NumberFormat.getInstance(locale).parse(valueWithoutSeparator)!!
    } catch (exception: ParseException) {
        0
    }
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun getLocaleFromTag(localeTag: String): Locale {
    return try {
        Locale.Builder().setLanguageTag(localeTag).build()
    } catch (e: IllformedLocaleException) {
        Locale.getDefault()
    }
}
