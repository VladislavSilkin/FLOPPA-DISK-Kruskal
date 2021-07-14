package com.example

import Node
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class KruskalWork {

    var vectorOfVertex: ArrayList<String> = ArrayList<String>()
    var graph = HashMap<String, Node>()
    var resGraph: HashMap<String, Node> = cloneGraph(graph) as HashMap<String, Node>
    var printGraph = ArrayList<Edge>()
    var allEdges = Vector<Edge>()
    var resEdgeVector = Vector<Edge>()
    var temp = 0
    var components = Vector<String>()
    var steps = ArrayList<ArrayList<Edge>>()
    val resPrint = ArrayList<Edge>()
    val logs = File("./src/main/resources/logs.txt")
    var logText = String()


    fun createGraph(list: List<String>) : KruskalWork {
        for (item in list[0].split(" ")) {
            vectorOfVertex.add(item)
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
                    (list[i].split(" ")[2].toDouble()), 0
                )
            )
        }
        for (elem in graph){
            components.addElement(elem.key)
        }
        return this
    }

    fun modifyGraph(){
        printGraph.clear()
        for(item in resEdgeVector){
            printGraph.add(Edge(item.v1, item.v2, item.weight, 1))
        }
    }
    fun doKruskal() : Vector<Edge>{
        allEdges.sortBy { it -> it.weight }     /*** Сортируем ребра */

        for (edge in allEdges){
            val temp = ArrayList<Edge>()
            if (!cirle(edge.v1,edge.v2,components)){
                compUniting(edge,components)
                val edge1 = Edge(edge.v1,edge.v2,edge.weight, 0)
                resGraph[edge.v1]?.addEdge(edge1)
                val edge2 = Edge(edge.v2,edge.v1,edge.weight, 0)
                resGraph[edge.v2]?.addEdge(edge2)
                resEdgeVector.addElement(edge)
                for(item in resEdgeVector){
                    temp.add(Edge(item.v1, item.v2, item.weight, 1))
                    resPrint.add(Edge(item.v1, item.v2, item.weight, 1))
                }
                for(item in allEdges) {
                    var flag = true
                    for(item1 in temp) {
                        if (item.v1 == item1.v1 && item.v2 == item1.v2 && item.weight == item1.weight && item.flag != item1.flag) {
                            flag = false
                            break
                        }
                    }
                    if(flag)
                        temp.add(item)
                }
                steps.add(temp)
            }
        }
        steps.add(resPrint)
        printAllEdges(resEdgeVector)
        logText += "==================\n"
        printGraph(resGraph)
        var resLength =0.0
        for (elem in resEdgeVector){
            resLength += elem.weight
        }
        logText += resLength
        logs.writeText(logText)

        return resEdgeVector
    }

    fun whichRemove(v:String, components: Vector<String>): String {
        for (str in components){
            if (v in str){
                return str
            }
        }
        return "#"
    }

    fun cirle(v1: String,v2:String, components: Vector<String>):Boolean{
        val obj1 = whichRemove(v1,components)
        val obj2 = whichRemove(v2, components)
        if (obj1 == obj2){
            return true
        }
        return false
    }

    fun compUniting(edge: Edge,components: Vector<String>): Vector<String> {
        val a = whichRemove(edge.v1,components)
        val b = whichRemove(edge.v2,components)
        components.removeElement(whichRemove(edge.v1,components))
        components.removeElement(whichRemove(edge.v2,components))
        components.addElement(a+b)
        logText += "${components}\n"
        return components
    }

    fun printGraph(graph: HashMap<String, Node>){
        for (elem in graph){
            logText += "${elem.key} : \n"
            logText += elem.value.printEdges()
            logText += "Degree = ${elem.value.degree}\n"
        }
    }

    fun cloneGraph(graph: HashMap<String, Node>):HashMap<String, Node>{
        val newGraph = HashMap<String,Node>()

        for (elem in graph){
            val newNode = Node(elem.key, elem.value.edges)
            newNode.degree=newNode.edges.size
            newGraph.put(elem.key, newNode)
        }

        return newGraph
    }

    fun printAllEdges(edges: Vector<Edge>){
        logText += "All/Result Edges:\n"
        for (elem in edges){
            logText += elem.printEdge()
        }
    }

    fun clearGraph(){
        vectorOfVertex.clear()
        graph.clear()
        resGraph.clear()
        printGraph.clear()
        allEdges.clear()
        resEdgeVector.clear()
        components.clear()
        steps.clear()
        resPrint.clear()
        temp = 0
    }
}