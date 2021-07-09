import com.example.Edge
import java.util.*
import kotlin.collections.HashMap

class Node(var name: String= "", var edges:Vector<Edge> = Vector<Edge>()){

    init {
        // println("Created Node with name $name\n")
    }

    var status: Boolean = false
    var degree = 0

    fun clearStatus(){
        this.status=false
    }

    fun printName(){
        println("PRINTED: ${this.name}")
    }

    fun printEdges(){
        for (elem in this.edges){
            elem.printEdge()
        }
    }

    fun addEdge(edge: Edge): Node {
        this.edges.addElement(edge)
        this.degree++
        return this
    }
    fun removeEdge(edge: Edge): Node{
        this.edges.remove(edge)
        this.degree--
        return this
    }
    fun removeNode(graph: HashMap<String, Node>): Node{                 //  Делает узел пустым
        reduceNode(this.edges.firstElement(), graph)
        this.removeEdge(this.edges.firstElement())
        return this
    }

    fun reduceNode(edge: Edge, graph: HashMap<String, Node>): Node{       //  Уменьшает на 1 степень, удаляя ребро перевернутое
        //  var value = graph[edge.v2]
        var neibEdge = Edge(edge.v2, edge.v1, edge.weight, 0)
        graph[edge.v2]?.removeEdge(neibEdge)
        return this
    }
}