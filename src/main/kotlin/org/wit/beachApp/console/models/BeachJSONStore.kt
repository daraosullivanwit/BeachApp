package org.wit.beachApp.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.beachApp.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "beaches.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<BeachModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class BeachJSONStore : BeachStore {

    var beaches = mutableListOf<BeachModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<BeachModel> {
        return beaches
    }

    override fun findOne(id: Long) : BeachModel? {
        var foundBeach: BeachModel? = beaches.find { p -> p.id == id }
        return foundBeach
    }

    override fun create(beach: BeachModel) {
        beach.id = generateRandomId()
        beaches.add(beach)
        serialize()
    }

    override fun update(beach: BeachModel) {
        var foundBeach = findOne(beach.id!!)
        if (foundBeach != null) {
            foundBeach.name = beach.name
            foundBeach.description = beach.description
        }
        serialize()
    }

    internal fun logAll() {
        beaches.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(beaches, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        beaches = Gson().fromJson(jsonString, listType)
    }
}