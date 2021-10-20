package org.wit.beachApp.console.main

import org.wit.beachApp.console.views.MenuScreen
import tornadofx.*

class MainApp : App(MenuScreen::class){
    fun main(args: Array<String>){
        launch<MainApp>(args)
    }
}