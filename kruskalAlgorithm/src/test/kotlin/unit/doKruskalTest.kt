package unit

import com.example.Edge
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.*

import com.example.KruskalWork

class HelloJunitTest {

    @Test
    fun `main fun test1`() {
        val obj1 = KruskalWork()
        val edge1 = Edge("a", "b", 1.0, 0)
        obj1.allEdges.addElement(edge1)
        val edge2 = Edge("b", "c", 1.0, 0)
        obj1.allEdges.addElement(edge2)
        val edge3 = Edge("c", "d", 1.0, 0)
        obj1.allEdges.addElement(edge3)
        val edge4 = Edge("a", "d", 2.0, 0)
        obj1.allEdges.addElement(edge4)
        obj1.components.addElement("a")
        obj1.components.addElement("b")
        obj1.components.addElement("c")
        obj1.components.addElement("d")
        val resVector = Vector<Edge>()
        resVector.addElement(edge1)
        resVector.addElement(edge2)
        resVector.addElement(edge3)
        assertEquals(resVector, obj1.doKruskal())
    }

    @Test
    fun `main fun test2`() {
        val obj = KruskalWork()
        val edge1 = Edge("a", "b", 1.0, 0)
        obj.allEdges.addElement(edge1)
        val edge2 = Edge("b", "c", 1.0, 0)
        obj.allEdges.addElement(edge2)
        val edge3 = Edge("c", "a", 2.0, 0)
        obj.allEdges.addElement(edge3)
        val edge4 = Edge("e", "d", 3.0, 0)
        obj.allEdges.addElement(edge4)
        val edge5 = Edge("d", "f", 4.0, 0)
        obj.allEdges.addElement(edge5)
        val edge6 = Edge("e", "f", 5.0, 0)
        obj.allEdges.addElement(edge6)
        val edge7 = Edge("g", "h", 6.0, 0)
        obj.allEdges.addElement(edge7)
        val edge8 = Edge("h", "i", 7.0, 0)
        obj.allEdges.addElement(edge8)
        val edge9 = Edge("h", "g", 8.0, 0)
        obj.allEdges.addElement(edge9)

        obj.components.addElement("a")
        obj.components.addElement("b")
        obj.components.addElement("c")
        obj.components.addElement("d")
        obj.components.addElement("e")
        obj.components.addElement("f")
        obj.components.addElement("g")
        obj.components.addElement("h")
        obj.components.addElement("i")
        val resVector = Vector<Edge>()
        resVector.addElement(edge1)
        resVector.addElement(edge2)
        resVector.addElement(edge4)
        resVector.addElement(edge5)
        resVector.addElement(edge7)
        resVector.addElement(edge8)
        assertEquals(resVector, obj.doKruskal())
    }

    @Test
    fun `main fun test3`() {
        val obj = KruskalWork()
        val edge1 = Edge("a", "b", 100.9, 0)
        obj.allEdges.addElement(edge1)
        val edge2 = Edge("b", "c", -24.1, 0)
        obj.allEdges.addElement(edge2)
        val edge3 = Edge("c", "d", 0.0, 0)
        obj.allEdges.addElement(edge3)
        val edge4 = Edge("a", "d", -14.5, 0)
        obj.allEdges.addElement(edge4)
        obj.components.addElement("a")
        obj.components.addElement("b")
        obj.components.addElement("c")
        obj.components.addElement("d")
        val resVector = Vector<Edge>()
        resVector.addElement(edge2)
        resVector.addElement(edge4)
        resVector.addElement(edge3)
        assertEquals(resVector, obj.doKruskal())
    }

    @Test
    fun `main fun test4`() {
        val obj = KruskalWork()
        obj.components.addElement("a")
        obj.components.addElement("b")
        obj.components.addElement("c")
        obj.components.addElement("d")
        obj.components.addElement("e")
        obj.components.addElement("f")
        obj.components.addElement("g")
        obj.components.addElement("h")
        obj.components.addElement("i")
        obj.components.addElement("j")
        obj.components.addElement("k")
        obj.components.addElement("l")
        val resVector = Vector<Edge>()
        assertEquals(resVector, obj.doKruskal())
    }

    @Test
    fun `main fun test5`() {
        val obj = KruskalWork()
        val edge1 = Edge("a", "b", 1.0, 0)
        obj.allEdges.addElement(edge1)
        val edge2 = Edge("a", "c", 1.0, 0)
        obj.allEdges.addElement(edge2)
        val edge3 = Edge("a", "d", 1.0, 0)
        obj.allEdges.addElement(edge3)
        val edge4 = Edge("a", "e", 1.0, 0)
        obj.allEdges.addElement(edge4)
        val edge5 = Edge("a", "f", 1.0, 0)
        obj.allEdges.addElement(edge5)

        val edge6 = Edge("b", "c", 1.0, 0)
        obj.allEdges.addElement(edge6)
        val edge7 = Edge("b", "d", 1.0, 0)
        obj.allEdges.addElement(edge7)
        val edge8 = Edge("b", "e", 1.0, 0)
        obj.allEdges.addElement(edge8)
        val edge9 = Edge("b", "f", 1.0, 0)
        obj.allEdges.addElement(edge9)

        val edge10 = Edge("c", "d", 1.0, 0)
        obj.allEdges.addElement(edge10)
        val edge11 = Edge("c", "e", 1.0, 0)
        obj.allEdges.addElement(edge11)
        val edge12 = Edge("c", "f", 1.0, 0)
        obj.allEdges.addElement(edge12)

        val edge13 = Edge("d", "e", 1.0, 0)
        obj.allEdges.addElement(edge13)
        val

                edge14 = Edge("d", "f", 1.0, 0)
        obj.allEdges.addElement(edge14)

        val edge15 = Edge("e", "f", 1.0, 0)
        obj.allEdges.addElement(edge15)

        obj.components.addElement("a")
        obj.components.addElement("b")
        obj.components.addElement("c")
        obj.components.addElement("d")
        obj.components.addElement("e")
        obj.components.addElement("f")
        val resVector = Vector<Edge>()
        resVector.addElement(edge1)
        resVector.addElement(edge2)
        resVector.addElement(edge3)
        resVector.addElement(edge4)
        resVector.addElement(edge5)
        assertEquals(resVector, obj.doKruskal())
    }

}