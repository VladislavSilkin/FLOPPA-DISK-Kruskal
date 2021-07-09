package com.example

class Edge(val v1: String, val  v2: String, val weight: Double, val flag: Int){

    fun printEdge(){
        println("${this.v1} <-> ${this.v2} = ${this.weight}")
    }
}