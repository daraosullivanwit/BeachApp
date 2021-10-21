package org.wit.beachApp.console.views

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import org.wit.beachApp.console.controllers.BeachUIController
import org.wit.beachApp.console.models.BeachModel
import tornadofx.*

class ListBeachScreen : View("List Beaches") {

    val beachUIController: BeachUIController by inject()
    val tableContent = beachUIController.beaches.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(1400.0, 445.0)
        tableview(data) {
            style{
                fontFamily = "Times New Roman"
            }
            readonlyColumn("ID", BeachModel::id)
            readonlyColumn("TITLE", BeachModel::name)
            readonlyColumn("DESCRIPTION", BeachModel::description)
        }
        button("Close") {
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
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    beachUIController.closeList()
                }
            }
        }
    }

}