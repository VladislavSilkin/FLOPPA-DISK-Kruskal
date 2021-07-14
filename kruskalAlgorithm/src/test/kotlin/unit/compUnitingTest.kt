package unit

import com.example.Edge
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

import com.example.KruskalWork

class JunitTestCompUniting {

    @Test
    fun `compUniting fun test1`() {
        val edge1 = Edge("a", "b", 2.0, 0)
        val components1 = Vector<String>()
        components1.addElement("ac")
        components1.addElement("bd")
        components1.addElement("ef")
        components1.addElement("gh")
        components1.addElement("ij")
        val obj1 = KruskalWork()
        val expected1 = Vector<String>()
        expected1.addElement("ef")
        expected1.addElement("gh")
        expected1.addElement("ij")
        expected1.addElement("acbd")
        assertEquals(expected1, obj1.compUniting(edge1, components1))
    }

    @Test
    fun `compUniting fun test2`() {
        val edge2 = Edge("g", "l", 2.0, 0)
        val components2 = Vector<String>()
        components2.addElement("az")
        components2.addElement("by")
        components2.addElement("cx")
        components2.addElement("dw")
        components2.addElement("ev")
        components2.addElement("fu")
        components2.addElement("gt")
        components2.addElement("hs")
        components2.addElement("ir")
        components2.addElement("kp")
        components2.addElement("lo")
        components2.addElement("mn")
        val obj2 = KruskalWork()
        val expected2 = Vector<String>()
        expected2.addElement("az")
        expected2.addElement("by")
        expected2.addElement("cx")
        expected2.addElement("dw")
        expected2.addElement("ev")
        expected2.addElement("fu")
        expected2.addElement("hs")
        expected2.addElement("ir")
        expected2.addElement("kp")
        expected2.addElement("mn")
        expected2.addElement("gtlo")
        assertEquals(expected2, obj2.compUniting(edge2, components2))
    }

    @Test
    fun `compUniting fun test3`() {
        val edge3 = Edge("a", "k", 2.0, 0)
        val components3 = Vector<String>()
        components3.addElement("qazxswedcvfrtgbnhyujm")
        components3.addElement("kiolp")
        val obj3 = KruskalWork()
        val expected3 = Vector<String>()
        expected3.addElement("qazxswedcvfrtgbnhyujmkiolp")
        assertEquals(expected3, obj3.compUniting(edge3, components3))
    }

    @Test
    fun `compUniting fun test4`() {
        val edge4 = Edge("t", "q", 2.0, 0)
        val components4 = Vector<String>()
        components4.addElement("plmkoijn")
        components4.addElement("bhuygvcftrdxzsewa")
        components4.addElement("q")
        val obj4 = KruskalWork()
        val expected4 = Vector<String>()
        expected4.addElement("plmkoijn")
        expected4.addElement("bhuygvcftrdxzsewaq")
        assertEquals(expected4, obj4.compUniting(edge4, components4))
    }

    @Test
    fun `compUniting fun test5`() {
        val edge5 = Edge("a", "b", 2.0, 0)
        val components5 = Vector<String>()
        components5.addElement("a")
        components5.addElement("b")
        components5.addElement("c")
        components5.addElement("d")
        components5.addElement("e")
        val obj5 = KruskalWork()
        val expected5 = Vector<String>()
        expected5.addElement("c")
        expected5.addElement("d")
        expected5.addElement("e")
        expected5.addElement("ab")
        assertEquals(expected5, obj5.compUniting(edge5, components5))
    }

}