package com.sijan.nextdestination
import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sijan.nextdestination.data.Destination
import com.sijan.nextdestination.data.sample.attributes.SampleAttributes

class MainViewModel(context:Context) : ViewModel() {
    private val destinationsJson = context.resources.openRawResource(R.raw.destinations).bufferedReader().use { it.readText() }
    val destinations: List<Destination> by lazy {
        val type = object : TypeToken<List<Destination>>() {}.type
        Gson().fromJson(destinationsJson, type)
    }

    private val utilitiesJson = context.resources.openRawResource(R.raw.hotels).bufferedReader().use { it.readText() }
    val attributes: SampleAttributes by lazy {
        val type = object : TypeToken<SampleAttributes>() {}.type
        Gson().fromJson(utilitiesJson, type)
    }
}
