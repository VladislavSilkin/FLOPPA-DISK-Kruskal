package unit

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

import com.example.KruskalWork

class JunitTestCircle {

    @Test
    fun `circle fun test`() {
        val a1 = "a"
        val b1 = "b"
        val components1 = Vector<String>()
        components1.addElement("ac")
        components1.addElement("bd")
        components1.addElement("ef")
        components1.addElement("gh")
        components1.addElement("ij")
        val obj1 = KruskalWork()
        assertEquals(false, obj1.cirle(a1, b1, components1))
    }

    @Test
    fun `circle fun test2`() {
        val a2 = "m"
        val b2 = "q"
        val components2 = Vector<String>()
        components2.addElement("axb")
        components2.addElement("c")
        components2.addElement("mrq")
        components2.addElement("d")
        components2.addElement("ef")
        val obj2 = KruskalWork()
        assertEquals(true, obj2.cirle(a2, b2, components2))
    }

    @Test
    fun `circle fun test3`() {
        val a3 = "e"
        val b3 = "r"
        val components3 = Vector<String>()
        components3.addElement("a")
        components3.addElement("b")
        components3.addElement("c")
        components3.addElement("d")
        components3.addElement("ef")
        components3.addElement("ghi")
        components3.addElement("jklmn")
        components3.addElement("opqrst")
        val obj3 = KruskalWork()
        assertEquals(false, obj3.cirle(a3, b3, components3))
    }

    @Test
    fun `circle fun test4`() {

        val a4 = "g"
        val b4 = "x"
        val components4 = Vector<String>()
        components4.addElement("qazxswedcvfrtgbnhyujm")
        components4.addElement("kiolp")
        val obj4 = KruskalWork()
        assertEquals(true, obj4.cirle(a4, b4, components4))
    }

    @Test
    fun `circle fun test5`() {
        val a5 = ""
        val b5 = "yt"
        val components5 = Vector<String>()
        components5.addElement("az")
        components5.addElement("by")
        components5.addElement("cx")
        components5.addElement("dw")
        components5.addElement("ev")
        components5.addElement("fu")
        components5.addElement("gt")
        components5.addElement("hs")
        components5.addElement("ir")
        components5.addElement("jq")
        components5.addElement("kp")
        components5.addElement("lo")
        components5.addElement("mn")
        val obj5 = KruskalWork()
        assertEquals(false, obj5.cirle(a5, b5, components5))
    }
}