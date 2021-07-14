import com.example.Edge
import java.util.*
import kotlin.collections.HashMap

class Node(var name: String= "", var edges:Vector<Edge> = Vector<Edge>()){

    var degree = 0

    fun printEdges() : String{
        var returnText = String()
        for (elem in this.edges){
            returnText += elem.printEdge()
        }
        return returnText
    }

    fun addEdge(edge: Edge): Node {
        this.edges.addElement(edge)
        this.degree++
        return this
    }
}