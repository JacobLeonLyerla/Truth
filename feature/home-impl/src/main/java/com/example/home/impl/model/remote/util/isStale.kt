package com.example.home.impl.model.remote.util

import java.time.Duration
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun isStale(timestamp: String?): Boolean {
    // Define formatter based on the input format
    val formatter = DateTimeFormatter
        .ofPattern("yyyy-MM-dd HH:mm:ss")
        .withZone(ZoneOffset.UTC)

    // Parse string timestamp into Instant
    val timestampInstant = Instant.from(formatter.parse(timestamp))

    // Get current Instant
    val currentInstant = Instant.now()

    // Calculate duration between the two instants
    val duration = Duration.between(timestampInstant, currentInstant)

    // Check if duration is greater than or equal to 60 seconds
    return duration.seconds >= 60
}
