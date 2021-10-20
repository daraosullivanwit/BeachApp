package org.wit.beachApp.console.views

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import org.wit.beachApp.console.controllers.BeachUIController
import org.wit.beachApp.console.models.BeachModel
import tornadofx.*

class ListBeachScreen : View("List Beaches") {

    val beachUIController: BeachUIController by inject()
    val tableContent = beachUIController.beaches.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", BeachModel::id)
            readonlyColumn("TITLE", BeachModel::name)
            readonlyColumn("DESCRIPTION", BeachModel::description)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    beachUIController.closeList()
                }
            }
        }
    }

}