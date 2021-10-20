package org.wit.beachApp.console.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.beachApp.console.controllers.BeachUIController
import tornadofx.*

class MenuScreen : View("Beach Main Menu") {

    val beachUIController: BeachUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Beach") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        beachUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Beaches") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        beachUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}