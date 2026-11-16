package com.amonteiro.a10_ynov_lyon_kmp.data.remote

import com.amonteiro.a10_ynov_lyon_kmp.BuildConfig
import com.amonteiro.a10_ynov_lyon_kmp.di.initKoin
import com.amonteiro.a10_ynov_lyon_kmp.domain.model.Weather
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable
import org.koin.mp.KoinPlatform

suspend fun main() {

    initKoin()
   val weatherApiDataSource =  KoinPlatform.getKoin().get<WeatherApiDataSource>()

    for (weather in weatherApiDataSource.loadWeathers("nice")) {
        println(weather.getResume())
    }
}

class WeatherApiDataSource(val client : HttpClient) {

    suspend fun loadWeathers(cityName:String) : List<Weather> {
        val response = client.get("https://api.openweathermap.org/data/2.5/find?q=$cityName&appid=${BuildConfig.WEATHER_API_KEY}&units=metric&lang=fr")

        if (!response.status.isSuccess()) {
            throw Exception("Erreur API: ${response.status} - ${response.bodyAsText()}")
        }

       return  response.body<WeatherResultDTO>().list.map {
            val icon = it.weather.firstOrNull()?.icon ?: ""
            Weather(
                id = it.id,
                name = it.name,
                temp = it.main.temp,
                speed = it.wind.speed,
                description = it.weather.firstOrNull()?.description ?: "-",
                icon = "https://openweathermap.org/img/wn/${icon}@4x.png",
            )
        }
    }

}


/* -------------------------------- */
// WEATHER
/* -------------------------------- */
@Serializable
data class WeatherResultDTO(val list: List<WeatherDTO>)

@Serializable
data class WeatherDTO(
    val id: Int, val name: String, var main: TempDTO,
    var weather: List<DescriptionDTO>,
    var wind: WindDTO
)

@Serializable
data class TempDTO(var temp: Double)

@Serializable
data class DescriptionDTO(var description: String, var icon: String)

@Serializable
data class WindDTO(var speed: Double)