package com.example

import Node
import tornadofx.property
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class KruskalWork {

    var vectorOfVertex: ArrayList<String> = ArrayList<String>()
    var graph = HashMap<String, Node>()
    var resGraph: HashMap<String, Node> = graph.clone() as HashMap<String, Node>
    var printGraph = ArrayList<Edge>()
    var allEdges = Vector<Edge>()
    var resEdgeVector = Vector<Edge>()



    fun createGraph(list: List<String>) : KruskalWork {
        for (item in list[0].split(" ")) {
            vectorOfVertex.add(item.toString())
        }
        for (name in vectorOfVertex) {
            val node = Node(name)
            val nodeRes = Node(name)
            resGraph[name] = nodeRes
            graph[name] = node
        }

        var from: String
        var to: String
        var weight: Double

        for (i in 1 until list.size) {
            val parts = list[i].split(" ")
            from = parts[0]
            to = parts[1]
            weight = parts[2].toDouble()
            val edge1 = Edge(from, to, weight, 0)

            /*** CОЗДАЕМ РЕБРА */
            val edge2 = Edge(to, from, weight, 0)
            allEdges.addElement(edge1)

            graph[from]?.addEdge(edge1)
            /*** Добавляем ребра в граф */
            graph[to]?.addEdge(edge2)
            printGraph.add(
                Edge(
                    list[i].split(" ")[0].toString(),
                    list[i].split(" ")[1].toString(),
                    (list[i].split(" ")[2].toDouble() - 48), 0
                )
            )
        }
        return this
    }

    fun modifyGraph(){
        printGraph.clear()
        for(item in resEdgeVector){
            printGraph.add(Edge(item.v1, item.v2, item.weight, 1))
        }
    }
    fun doKruskal(){
        allEdges.sortBy { it -> it.weight }

        var countVertex = vectorOfVertex.count()
        var countOfAddedNodes = 0
        for (edge in allEdges){
            if (!checkCycle(resGraph,edge)){
                resEdgeVector.addElement(edge)
                var edge1 = Edge(edge.v1,edge.v2,edge.weight, 0)
                var edge2 = Edge(edge.v2, edge.v1, edge.weight, 0)
                var froms = edge.v1
                var tos = edge.v2
                resGraph[froms]?.addEdge(edge1)
                resGraph[tos]?.addEdge(edge2)
                countOfAddedNodes++
            }
            if (countOfAddedNodes == countVertex-1){
                break
            }
        }

        var resLength =0.0
        for (elem in resEdgeVector){
            resLength += elem.weight
        }
        printGraph(resGraph)
        return
    }

    fun printGraph(graph: HashMap<String, Node>/*, a:Int*/){
//    println("Printed graph $a")
        for (elem in graph){
            print("${elem.key} : \n")
            elem.value.printEdges()
            println("Degree = ${elem.value.degree}")
            print("\n")
        }
    }

    fun <String, Node> clone(original: HashMap<String, Node>): MutableMap<String, Node> {
        val copy: MutableMap<String, Node> = HashMap()
        copy.putAll(original)
        return copy
    }

    fun printMutableGraph(graph: MutableMap<String, Node>){
        for (elem in graph){
            elem.value.printEdges()
        }
    }

    fun printAllEdges(edges: Vector<Edge>){
        println("All/Result Edges:")
        for (elem in edges){
            elem.printEdge()
        }
    }

    fun isExsistOneDegree(graph: HashMap<String, Node>):Boolean{
        var isExsist = false
        for (elem in graph){
            if (elem.value.degree == 1){
                return true
            }
        }

        return isExsist

    }

    fun isCycle(graph: HashMap<String, Node>) : Boolean{
        //  var status = false // не цикл
/*//
//    for (elem in graph){        //делаем все вершины не просмотренными
//      elem.value.clearStatus()
//    }
//
//    for (elem in graph){
//        dfs(elem.value, status)
//        if (status == true){
//            return true
//        }
//    }*/

        while (isExsistOneDegree(graph)){
            for (elem in graph){
                if (elem.value.degree == 1){
                    elem.value.removeNode(graph)
                }
            }
        }
        var cycle = true
        for (elem in graph){
            cycle = elem.value.degree>0
            //  printGraph(graph)
        }
        // println("HEY GRAPH - CYCLE? $cycle")
        return cycle
    }

    fun checkCycle(graph: HashMap<String, Node>, edge: Edge):Boolean{
        val from = edge.v1
        val to = edge.v2

        var status = false
        // var tmpGraph = clone(graph) as HashMap<String, Node>

        // printGraph(tmpGraph)


        var edge1 = Edge(edge.v1, edge.v2, edge.weight, 0)
        var edge2 = Edge(edge.v2, edge.v1, edge.weight, 0)
        graph[from]?.addEdge(edge1)
        graph[to]?.addEdge(edge2)

        status = isCycle(graph)
        //printGraph(tmpGraph,9)
        //remove edges
        graph[from]?.removeEdge(edge1)
        graph[to]?.removeEdge(edge2)

        return status

    }
}