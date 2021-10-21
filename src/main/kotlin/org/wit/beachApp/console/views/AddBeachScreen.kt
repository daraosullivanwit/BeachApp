package org.wit.beachApp.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import org.wit.beachApp.console.controllers.BeachUIController
import tornadofx.*
import kotlin.reflect.KClass

class AddBeachScreen : View("Add Beach") {
    val model = ViewModel()
    val _name = model.bind { SimpleStringProperty() }
    val _description = model.bind { SimpleStringProperty() }
    val beachUIController: BeachUIController by inject()

    override val root = form {
        setPrefSize(1400.0, 700.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Name") {
                style{
                    fontFamily = "Times New Roman"
                }
                textfield(_name).required()
            }
            field("Description") {
                style{
                    fontFamily = "Times New Roman"
                }
                textarea(_description).required()
            }
            button("Add") {
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