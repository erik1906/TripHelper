package com.eagledev.triphelper.utils

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

fun OffsetDateTime.toFormat(): String{
    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)
    return this.format(formatter)

}