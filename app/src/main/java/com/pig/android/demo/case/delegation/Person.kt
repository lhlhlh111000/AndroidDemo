package com.pig.android.demo.case.delegation

class Person(name: String, lastName: String) {

    var name: String by FormatDelegate()

    var lastName: String by FormatDelegate()

    var updateCount = 0
}