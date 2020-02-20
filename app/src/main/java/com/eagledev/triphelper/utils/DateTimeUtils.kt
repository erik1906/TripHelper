package com.eagledev.triphelper.utils

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import java.text.DecimalFormat

fun OffsetDateTime.toFormat(): String{
    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)
    return this.format(formatter)

}

fun Int.toCurrency(): String{
    val formatter = DecimalFormat("###,###,##0.00")
    return formatter.format(this.toDouble())
}