package org.wit.beachApp.console.views

import org.wit.beachApp.console.main.beachView
import org.wit.beachApp.console.main.beaches
import org.wit.beachApp.console.models.BeachMemStore
import org.wit.beachApp.console.models.BeachModel

class BeachView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Beach")
        println(" 2. Update Beach")
        println(" 3. List All Beaches")
        println(" 4. Search Beaches")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listBeaches(beaches : BeachMemStore) {
        println("List All Beaches")
        println()
        beaches.logAll()
        println()
    }

    fun showBeach(beach : BeachModel) {
        if(beach != null)
            println("Beach Details [ $beach ]")
        else
            println("Beach Not Found...")
    }

    fun addBeachData(beach : BeachModel) : Boolean {

        println()
        print("Enter a Name : ")
        beach.name = readLine()!!
        print("Enter a Description : ")
        beach.description = readLine()!!

        return beach.name.isNotEmpty() && beach.description.isNotEmpty()
    }

    fun updateBeachData(beach : BeachModel) : Boolean {

        var tempTitle: String?
        var tempDescription: String?

        if (beach != null) {
            print("Enter a new Title for [ " + beach.name + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + beach.description + " ] : ")
            tempDescription = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                beach.name = tempTitle
                beach.description = tempDescription
                return true
            }
        }
        return false
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
}