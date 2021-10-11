package org.wit.beachApp.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class BeachMemStore : BeachStore {

    val beaches = ArrayList<BeachModel>()

    override fun findAll(): List<BeachModel> {
        return beaches
    }

    override fun findOne(id: Long) : BeachModel? {
        var foundBeach: BeachModel? = beaches.find { p -> p.id == id }
        return foundBeach
    }

    override fun create(beach: BeachModel) {
        beach.id = getId()
        beaches.add(beach)
        logAll()
    }

    override fun update(beach: BeachModel) {
        var foundBeach = findOne(beach.id!!)
        if (foundBeach != null) {
            foundBeach.name = beach.name
            foundBeach.description = beach.description
        }
    }

    override fun delete(beach: BeachModel) {
        beaches.remove(beach)
    }

    internal fun logAll() {
        beaches.forEach { logger.info("${it}") }
    }
}