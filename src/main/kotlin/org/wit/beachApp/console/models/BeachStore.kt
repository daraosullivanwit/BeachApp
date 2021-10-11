package org.wit.beachApp.console.models

interface BeachStore {
    fun findAll(): List<BeachModel>
    fun findOne(id: Long): BeachModel?
    fun create(beach: BeachModel)
    fun update(beach: BeachModel)
    fun delete(beach: BeachModel)
}