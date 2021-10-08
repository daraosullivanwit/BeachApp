package org.wit.beachApp.console.main

import mu.KotlinLogging
import org.wit.beachApp.console.models.BeachModel
import org.wit.beachApp.console.models.BeachMemStore
import org.wit.beachApp.console.views.BeachView
import org.wit.beachApp.console.controllers.BeachController


private val logger = KotlinLogging.logger {}

val beaches = BeachMemStore()
val beachView = BeachView()
val controller = BeachController()

fun main(args: Array<String>){
    //Logging Support
    logger.info { "Launching Beach Console App" }
    println("Beach Console App")

    var input: Int
    //put menu in loop so it continues to show
    do {
        input = beachView.menu()
        when(input) {
            1 -> addBeach()
            2 -> updateBeach()
            3 -> beachView.listBeaches(beaches)
            4 -> searchBeach()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Beach Console App" }
}

fun addBeach(){
    var aBeach = BeachModel()

    if (beachView.addBeachData(aBeach))
        beaches.create(aBeach)
    else
        logger.info("Beach Not Added")
}

fun updateBeach(){
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

fun searchBeach(){
    val aBeach = search(beachView.getId())!!
    beachView.showBeach(aBeach)
}

fun search(id: Long) : BeachModel? {
    var foundBeach = beaches.findOne(id)
    return foundBeach
}

fun dummyData() {
    beaches.create(BeachModel(1, "New York New York", "So Good They Named It Twice"))
    beaches.create(BeachModel(2, "Ring of Kerry", "Some place in the Kingdom"))
    beaches.create(BeachModel(3, "Waterford City", "You get great Blaas Here!!"))
}