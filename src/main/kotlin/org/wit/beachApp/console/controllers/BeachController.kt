package org.wit.beachApp.console.controllers

import mu.KotlinLogging
import org.wit.beachApp.console.models.BeachJSONStore
import org.wit.beachApp.console.models.BeachMemStore
import org.wit.beachApp.console.models.BeachModel
import org.wit.beachApp.console.views.BeachView

class BeachController {

    //val beaches = BeachMemStore()
    val beaches = BeachJSONStore()
    val beachView = BeachView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Beach Console App" }
        println("Beach Kotlin App Version 1.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Beach Console App" }
    }

    fun menu() :Int { return beachView.menu() }

    fun add(){
        var aBeach = BeachModel()

        if (beachView.addBeachData(aBeach))
            beaches.create(aBeach)
        else
            logger.info("Beach Not Added")
    }

    fun list() {
        beachView.listBeaches(beaches)
    }

    fun update() {

        beachView.listBeaches(beaches)
        var searchId = beachView.getId()
        val aBeach = search(searchId)

        if(aBeach != null) {
            if(beachView.updateBeachData(aBeach)) {
                beaches.update(aBeach)
                beachView.showBeach(aBeach)
                logger.info("Beach Updated : [ $aBeach ]")
            }
            else
                logger.info("Beach Not Updated")
        }
        else
            println("Beach Not Updated...")
    }

    fun search() {
        val aBeach = search(beachView.getId())!!
        beachView.showBeach(aBeach)
    }


    fun search(id: Long) : BeachModel? {
        var foundBeach = beaches.findOne(id)
        return foundBeach
    }

    fun delete() {
        beachView.listBeaches(beaches)
        var searchId = beachView.getId()
        val aBeach = search(searchId)

        if(aBeach != null) {
            beaches.delete(aBeach)
            println("Beach Deleted...")
            beachView.listBeaches(beaches)
        }
        else
            println("Beach Not Deleted...")
    }

    fun dummyData() {
        beaches.create(BeachModel(name = "New York New York", description = "So Good They Named It Twice"))
        beaches.create(BeachModel(name = "Ring of Kerry", description = "Some place in the Kingdom"))
        beaches.create(BeachModel(name = "Waterford City", description = "You get great Blaas Here!!"))
    }
}