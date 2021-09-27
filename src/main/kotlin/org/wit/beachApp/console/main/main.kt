package org.wit.beachApp.console.main

import mu.KotlinLogging
import org.wit.beachApp.console.models.BeachModel

private val logger = KotlinLogging.logger {}

var beach = BeachModel()

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
    println("Add Beach")
    println()
    print("Enter Name : ")
    beach.name = readLine()!!
    print("Enter a Description : ")
    beach.description = readLine()!!
    println("You entered [ " + beach.name + " ] for name " +
            "and [ " + beach.description + " ] for description")
}

fun updateBeach(){
    println("Update Beach")
    println()
    print("Enter a new name for [ " + beach.name + " ] : ")
    beach.name = readLine()!!
    print("Enter a new Description for [ " + beach.description + " ] : ")
    beach.description = readLine()!!
    println("You updated [ " + beach.name + " ] for name " +
            "and [ " + beach.description + " ] for description")
}

fun listAllBeaches(){
    println("You Chose List All Beaches")
}