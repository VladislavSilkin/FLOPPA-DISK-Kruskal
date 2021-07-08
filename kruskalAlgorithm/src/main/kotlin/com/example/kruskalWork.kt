package com.example

import tornadofx.*

class KruskalWork {
    val graph: MutableMap<Char?, MutableMap<Char?, Double>?> = mutableMapOf()

    fun createGraph(list: List<String>) {
        for (item in list[0]) {
            if (item != ' ')
                graph[item] = null
        }
        for (item in list) {
            graph[item[0]] = mutableMapOf(item[2] to item[4].toDouble())
        }
    }
}