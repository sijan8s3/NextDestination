package com.sijan.nextdestination.data

data class Destination(
    val activities: List<String>,
    val best_time_to_visit: String,
    val continent: String,
    val country: String,
    val currency: String,
    val description: String,
    val id: Int,
    val image: String,
    val language: String,
    val local_dishes: List<String>,
    val name: String,
    val population: String,
    val top_attractions: List<String>
)