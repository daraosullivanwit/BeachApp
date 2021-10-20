package org.wit.beachApp.console.controllers

import mu.KotlinLogging
import org.wit.beachApp.console.models.BeachJSONStore
import org.wit.beachApp.console.models.BeachModel
import org.wit.beachApp.console.views.AddBeachScreen
import org.wit.beachApp.console.views.ListBeachScreen
import org.wit.beachApp.console.views.MenuScreen
import tornadofx.*

class BeachUIController : Controller() {

    val beaches = BeachJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Beach TornadoFX UI App" }
    }
    fun add(_name : String, _description : String){

        var aBeach = BeachModel(name = _name, description = _description)
        beaches.create(aBeach)
        logger.info("Beach Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListBeachScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        beaches.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddBeachScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddBeachScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListBeachScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}