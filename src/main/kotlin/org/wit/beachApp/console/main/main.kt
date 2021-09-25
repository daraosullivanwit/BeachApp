package org.wit.beachApp.console.main

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

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
//Placeholder menu option functions
fun addBeach(){
    println("You Chose Add Beach")
}

fun updateBeach(){
    println("You Chose Update Beach")
}

fun listAllBeaches(){
    println("You Chose List All Beaches")
}