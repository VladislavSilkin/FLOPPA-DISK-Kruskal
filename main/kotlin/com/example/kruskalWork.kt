package com.example

import tornadofx.*

class KruskalWork {

    var first: ArrayList<String> = ArrayList<String>()
    var graph: ArrayList<Edge> = ArrayList<Edge>()
    get(){
        return field
    }

    fun createGraph(list: List<String>) : KruskalWork{
        for (item in list[0]) {
            if (item != ' ')
                first.add(item.toString())
        }
        for (i in 1 until list.size) {
            graph.add(Edge(list[i][0].toString(), list[i][2].toString(), (list[i][4].toDouble()-48), 0))
        }
        return this
    }
}