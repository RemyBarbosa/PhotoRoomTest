package com.photoroomtest.entity.weather

data class Weather(
    val dateTime: Long,
    val kind: WeatherKind,
    val description: String
)