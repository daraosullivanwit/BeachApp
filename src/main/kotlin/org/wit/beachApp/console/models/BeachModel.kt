package org.wit.beachApp.console.models

data class BeachModel(var id: Long = 0, var name: String = "", var location: String = "",
                          var description: String = "", var WalkingPath: Boolean = true, var distanceFromHome: Long = 0, var swimWarnings: Array<String> = arrayOf(), var tideLevel: String = "", var surf: Array<String> = arrayOf())