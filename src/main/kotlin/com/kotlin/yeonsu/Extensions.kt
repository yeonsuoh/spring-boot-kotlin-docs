package com.kotlin.yeonsu

import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.*

// ------ 날짜 포멧터 확장함수 ------

fun LocalDateTime.format(): String =
    this.format(englishDateFormatter)

private val daysLookup = (1..31).associate {
    it.toLong() to
        getOrdinal(it)
}

private val englishDateFormatter = DateTimeFormatterBuilder()
    .appendPattern("yyyy-MM-dd")
    .appendLiteral(" ")
    .appendText(ChronoField.DAY_OF_MONTH, daysLookup)
    .appendLiteral(" ")
    .appendPattern("yyyy")
    .toFormatter(Locale.ENGLISH)

private fun getOrdinal(n: Int) = when {
    n in 11..13 -> "${n}th"
    n % 10 == 1 -> "${n}st"
    n % 10 == 2 -> "${n}nd"
    n % 10 == 3 -> "${n}rd"
    else -> "${n}th"
}

// ------ 슬러그 변환함수 ------

// 문자열을 슬러그 형식으로 변환하는 확장함수
// 슬러그는 주로 URL에 사용되며 일반적으로 공백을 하이픈'-'으로 대체하고 특수문자를 제거함
fun String.toSlug() = lowercase(Locale.getDefault())
    .replace("\n", " ")
    .replace("[^a-z\\d\\s]".toRegex(), " ")
    .split(" ")
    .joinToString("-")
    .replace("-+".toRegex(), "-")
