package com.pig.android.demo.case.delegation

class ListWithTrash<T>(private val innerList: MutableList<T> = ArrayList<T>())
    : MutableCollection<T> by innerList {

    var deletedItem : T? = null

    override fun remove(element: T): Boolean {
        if(innerList.remove(element)) {
            deletedItem = element
            return true
        }

        return false
    }

    fun recover(): T? {
        return deletedItem
    }
}