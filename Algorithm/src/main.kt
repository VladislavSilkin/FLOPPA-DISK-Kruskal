import java.util.*
import kotlin.Comparator
import kotlin.collections.HashMap
import kotlin.math.E

val scan = java.util.Scanner(System.`in`)

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


    var edge1 = Edge(edge.v1, edge.v2, edge.weight)
    var edge2 = Edge(edge.v2, edge.v1, edge.weight)
    graph[from]?.addEdge(edge1)
    graph[to]?.addEdge(edge2)

    status = isCycle(graph)
    //printGraph(tmpGraph,9)
    //remove edges
    graph[from]?.removeEdge(edge1)
    graph[to]?.removeEdge(edge2)

    return status

}



fun main(){




    var vectorOfVertex = Vector<String>()                                /** ВВОД ПЕРВОЙ СТРОКИ С ВЕРШИНАМИ  */
    var arrayVertex = scan.nextLine().split(" ")
    for (elem in arrayVertex){
        if(!(elem.count()>3) && !(elem in vectorOfVertex) /*&& !(elem.contains(" "))*/){
            vectorOfVertex.addElement(elem)
        }
    }
    println(vectorOfVertex)
                                            /** СОЗДАНИЕ ВСЕХ ВЕРШИН */


    var graph =  HashMap<String, Node>() /**  ТУТ ХРАНИТСЯ ГРАФ */
    var resGraph: HashMap<String, Node> = graph.clone() as HashMap<String, Node>  /** А ТУТ ХРАНИТСЯ ОСТОВНОЕ ДЕРЕВО */

    for (name in vectorOfVertex){

        //var edge = Vector<Edge>()
        val node = Node(name)
        val nodeRes =Node(name)
        //println("${node.name}:: ${node.edges}")
        resGraph[name] = nodeRes
        graph[name] = node
        //node.printName()

    }



    var allEdges = Vector<Edge>()           /** ДОБАВЛЕНИЕ РЕБЕР К ВЕРШИНАМ */

    var newStr: String
    var from: String
    var to: String
    var weight: Double
    while (scan.hasNext()){
        newStr = scan.nextLine()
        val parts = newStr.split(" ")
        from = parts[0]
        to = parts[1]
        weight = parts[2].toDouble()
        val edge1 = Edge(from, to, weight)   /*** CОЗДАЕМ РЕБРА */
        val edge2 = Edge(to, from, weight)
        allEdges.addElement(edge1)

        graph[from]?.addEdge(edge1)          /*** Добавляем ребра в граф */
        graph[to]?.addEdge(edge2)

    }
  //  println("check cycle ${isCycle(graph)}")
    printGraph(graph)
                                                                                /***На этом моменте все хорошо=====*/

    allEdges.sortBy { it -> it.weight }     /*** Сортируем ребра */
   // printAllEdges(allEdges)

                                                                                /***На этом моменте все хорошо=====*/

   // printGraph(resGraph,2)
   // printGraph(graph,3)

    var countVertex = vectorOfVertex.count()
    //println("$countVertex Nodes in graph")


    //======
//    print("isCycle = ${isCycle(graph)}")
    //=======
                                                                                /***До этого момента все хорошо=====*/

    var resEdgeVector = Vector<Edge>()

    var countOfAddedNodes = 0
    for (edge in allEdges){                         /*** ЗАПУСКАЕМ ПЕРЕБОР РЕБЕР */
      //  println("\n\nПроверяется след. граф с ребром\n")
        //edge.printEdge()
       // printGraph(resGraph)
       // println("\n${checkCycle(resGraph,edge)} \n")
        //println("\n\n=================\n")
        //printGraph(resGraph)


        if (!checkCycle(resGraph,edge)){
            resEdgeVector.addElement(edge)
            var edge1 = Edge(edge.v1,edge.v2,edge.weight)
            var edge2 = Edge(edge.v2, edge.v1, edge.weight)
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
    printAllEdges(resEdgeVector)
    println("==================")
    printGraph(resGraph)
    var resLength =0.0
    for (elem in resEdgeVector){
        resLength += elem.weight
    }
    println(resLength)
}