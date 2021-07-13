import java.lang.reflect.RecordComponent
import java.util.*
import kotlin.collections.HashMap

val scan = java.util.Scanner(System.`in`)

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
    var a = whichRemove(edge.v1,components)
    var b = whichRemove(edge.v2,components)
    components.removeElement(whichRemove(edge.v1,components))
    components.removeElement(whichRemove(edge.v2,components))
    components.addElement(a+b)
    println(components)
    return components
}

fun cloneGraph(graph: HashMap<String, Node>):HashMap<String, Node>{
    var newGraph = HashMap<String,Node>()

    for (elem in graph){
        var newNode = Node(elem.key, elem.value.edges)
        newNode.degree=newNode.edges.size
        newGraph.put(elem.key, newNode)
    }

    return newGraph
}
//
//fun Cycle(graph: HashMap<String, Node>):Boolean{
//
//}
//
//fun opisat(graph: HashMap<String, Node>):Vector<String>{
//    var mnozhestvo = Vector<String>()
//    for (elem in graph){
//        mnozhestvo.addElement(elem.key)                        //init
//    }
//    for (elem in mnozhestvo){
//
//    }
//
//    return
//}
fun checkCycle(graph: HashMap<String, Node>, edge: Edge):Boolean{
    var tmpGraph = cloneGraph(graph)
    var edge1 = Edge(edge.v1,edge.v2,edge.weight)
    var edge2 = Edge(edge.v2,edge.v1,edge.weight)

    tmpGraph[edge.v1]?.addEdge(edge1)
    tmpGraph[edge.v2]?.addEdge(edge2)

    return isCycle(tmpGraph)


}

fun isCycle(tmpGraph: HashMap<String, Node>): Boolean {

    while (isExsistOneDegree(tmpGraph)){
        for (elem in tmpGraph){
            if(elem.value.degree == 1){
                elem.value.removeNode(tmpGraph)
            }
        }
    }
    var cycle =true
    for (elem in tmpGraph){
        cycle = elem.value.degree>0
        if (cycle==true){
            return true
        }
    }
    return cycle
}

fun isExsistOneDegree(tmpGraph: HashMap<String, Node>):Boolean{
    val isExsist = false
    for (elem in tmpGraph){
        if (elem.value.degree == 1){
            return true
        }
    }

    return isExsist

}
fun printAllEdges(edges: Vector<Edge>){
    println("All/Result Edges:")
    for (elem in edges){
        elem.printEdge()
    }
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
    var resGraph: HashMap<String, Node> = graph.clone() as HashMap<String, Node> /** А ТУТ ХРАНИТСЯ ОСТОВНОЕ ДЕРЕВО */

    for (name in vectorOfVertex){

        val node = Node(name)
        val nodeRes =Node(name)
        resGraph[name] = nodeRes
        graph[name] = node

    }

    var components = Vector<String>()
    for (elem in graph){
        components.addElement(elem.key)
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

    allEdges.sortBy { it -> it.weight }     /*** Сортируем ребра */
    var resEdgeVector = Vector<Edge>()

    for (edge in allEdges){

        if (!cirle(edge.v1,edge.v2,components)){
            compUniting(edge,components)
            var edge1 = Edge(edge.v1,edge.v2,edge.weight)
            resGraph[edge.v1]?.addEdge(edge1)
            var edge2 = Edge(edge.v2,edge.v1,edge.weight)
            resGraph[edge.v2]?.addEdge(edge2)
           resEdgeVector.addElement(edge)
        }
//        compUniting(edge,components)
//        var tmpGraph=cloneGraph(resGraph)
//        if (!checkCycle(tmpGraph,edge)){
//            var edge1 = Edge(edge.v1,edge.v2,edge.weight)
//            resGraph[edge.v1]?.addEdge(edge1)
//            var edge2 = Edge(edge.v2,edge.v1,edge.weight)
//            resGraph[edge.v2]?.addEdge(edge2)
//            resEdgeVector.addElement(edge)
//        }
    }   //abdcf
    printAllEdges(resEdgeVector)
    println("==================")
    printGraph(resGraph)
    var resLength =0.0
    for (elem in resEdgeVector){
        resLength += elem.weight
    }
    println(resLength)
}


