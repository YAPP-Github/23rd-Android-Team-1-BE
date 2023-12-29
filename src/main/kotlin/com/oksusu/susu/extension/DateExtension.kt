package com.oksusu.susu.extension

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

object Zone {
    val KST: ZoneId = ZoneId.of("Asia/Seoul")
    val UTC: ZoneId = ZoneId.of("UTC")
}

fun LocalDateTime.toInstant(): Instant {
    return this.toInstant(ZoneOffset.of("+09:00"))
}

fun LocalDateTime.toClockEpochMilli(): Long {
    return this.withMinute(0).withSecond(0).withNano(0).toInstant().toEpochMilli()
}
