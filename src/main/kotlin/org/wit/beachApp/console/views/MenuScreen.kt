package org.wit.beachApp.console.views

import javafx.application.Platform
import javafx.geometry.Orientation
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import org.wit.beachApp.console.controllers.BeachUIController
import tornadofx.*

class MenuScreen : View("Beach Main Menu") {

    val beachUIController: BeachUIController by inject()

    override val root = form {
        setPrefSize(1400.0, 700.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Beach") {
                style {
                    fontWeight = FontWeight.LIGHT
                    borderColor += box(
                        top = Color.DARKRED,
                        right = Color.DARKRED,
                        left = Color.DARKRED,
                        bottom = Color.DARKRED
                    )
                    fontFamily = "Times New Roman"
                    backgroundColor += Color.CORAL
                }
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
                style {
                    fontWeight = FontWeight.LIGHT
                    borderColor += box(
                        top = Color.DARKRED,
                        right = Color.DARKRED,
                        left = Color.DARKRED,
                        bottom = Color.DARKRED
                    )
                    fontFamily = "Times New Roman"
                    backgroundColor += Color.CORAL
                }
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
                style {
                    fontWeight = FontWeight.LIGHT
                    borderColor += box(
                        top = Color.DARKRED,
                        right = Color.DARKRED,
                        left = Color.DARKRED,
                        bottom = Color.DARKRED
                    )
                    fontFamily = "Times New Roman"
                    backgroundColor += Color.CORAL
                }
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