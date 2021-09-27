package org.wit.beachApp.console.main

import mu.KotlinLogging
import org.wit.beachApp.console.models.BeachModel

private val logger = KotlinLogging.logger {}

val beaches = ArrayList<BeachModel>()

fun main(args: Array<String>){
    //Logging Support
    logger.info { "Launching Beach Console App" }
    println("Beach Console App")

    var input: Int
    //put menu in loop so it continues to show
    do {
        input = menu()
        when(input) {
            1 -> addBeach()
            2 -> updateBeach()
            3 -> listAllBeaches()
            4 -> searchBeach()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Beach Console App" }
}

fun menu() : Int {

    var option : Int
    var input: String? = null

    println("Main Menu")
    println(" 1. Add Beach")
    println(" 2. Update Beach")
    println(" 3. List All Beaches")
    println(" 4. Search Beaches")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    //validate input
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addBeach(){
    var aBeach = BeachModel()
    println("Add Beach")
    println()
    print("Enter a Name : ")
    aBeach.name = readLine()!!
    print("Enter a Description : ")
    aBeach.description = readLine()!!

    //Basic Validation
    if (aBeach.name.isNotEmpty() && aBeach.description.isNotEmpty()) {
        aBeach.id = beaches.size.toLong()
        //Passing a copy of the beach object to the add function to ensure a separate copy of the object is stored in the list
        beaches.add(aBeach.copy())
        logger.info("Beach Added : [ $aBeach ]")
    }
    else
        logger.info("Beach Not Added")
}

fun updateBeach(){
    println("Update Beach")
    println()
    listAllBeaches()
    var searchId = getId()
    val aBeach = search(searchId)

    if(aBeach != null) {
        print("Enter a new Title for [ " + aBeach.name + " ] : ")
        aBeach.name = readLine()!!
        print("Enter a new Description for [ " + aBeach.description + " ] : ")
        aBeach.description = readLine()!!
        //Basic Validation
        if (aBeach.name.isNotEmpty() && aBeach.description.isNotEmpty()) {
            println(
                "You updated [ " + aBeach.name + " ] for name " +
                        "and [ " + aBeach.description + " ] for description"
            )
            logger.info("Beach Updated")
        }
        else
            logger.info("Beach not Updated")
    }
}

fun listAllBeaches(){
    println("List All Beaches")
    println()
    beaches.forEach { logger.info("${it}") }
    println()
}

fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : BeachModel? {
    var foundBeach: BeachModel? = beaches.find { p -> p.id == id }
    return foundBeach
}

fun searchBeach(){
    var searchId = getId()
    val aBeach = search(searchId)

    if(aBeach != null)
        println("Beach Details [ $aBeach ]")
    else
        println("Beach Not Found...")
}

fun dummyData() {
    beaches.add(BeachModel(1, "New York New York", "So Good They Named It Twice"))
    beaches.add(BeachModel(2, "Ring of Kerry", "Some place in the Kingdom"))
    beaches.add(BeachModel(3, "Waterford City", "You get great Blaas Here!!"))
}