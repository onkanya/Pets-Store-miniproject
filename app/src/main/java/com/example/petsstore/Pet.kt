package com.example.petsstore

class Pet {
    var id: Int = 0
    var name: String = ""
    var age: Int = 0
    var type: String = ""
    var sex: String = ""
    var weight: Float = 0.0F

    constructor(name: String, age: Int, type: String, sex: String, weight: Float) {
        this.name = name
        this.age = age
        this.type = type
        this.sex = sex
        this.weight = weight
    }

}