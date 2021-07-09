import java.util.*
class Edge(val v1: String, val  v2: String, val weight: Double){

    fun printEdge(){
        println("${this.v1} <-> ${this.v2} = ${this.weight}")
    }
}