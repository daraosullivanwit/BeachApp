package org.wit.beachApp.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.beachApp.console.controllers.BeachUIController
import tornadofx.*
import kotlin.reflect.KClass

class AddBeachScreen : View("Add Beach") {
    val model = ViewModel()
    val _name = model.bind { SimpleStringProperty() }
    val _description = model.bind { SimpleStringProperty() }
    val beachUIController: BeachUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Name") {
                textfield(_name).required()
            }
            field("Description") {
                textarea(_description).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        beachUIController.add(_name.toString(),_description.toString())

                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        beachUIController.closeAdd()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _name.value = ""
        _description.value = ""
        model.clearDecorators()
    }
}