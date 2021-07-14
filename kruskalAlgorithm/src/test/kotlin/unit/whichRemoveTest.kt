package unit

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

import com.example.KruskalWork

class JuitTestWhichRemove {

    @Test
    fun `wichRemove fun test1`() {
        val vec = Vector<String>(10)
        vec.addElement("abc")
        vec.addElement("def")
        vec.addElement("ghi")
        val obj = KruskalWork()
        assertEquals("abc", obj.whichRemove("a", vec))
    }

    @Test
    fun `wichRemove fun test2`() {
        val vec1 = Vector<String>(10)
        vec1.addElement("cabdpouy")
        vec1.addElement("eqfzxrt")
        vec1.addElement("ghijkl")
        val obj1 = KruskalWork()
        assertEquals("eqfzxrt", obj1.whichRemove("z", vec1))
    }

    @Test
    fun `wichRemove fun test3`() {
        val vec2 = Vector<String>(10)
        vec2.addElement("zxcvbnm")
        vec2.addElement("lkjhgfdsa")
        vec2.addElement("qwertyuio")
        val obj2 = KruskalWork()
        assertEquals("#", obj2.whichRemove("p", vec2))
    }

    @Test
    fun `wichRemove fun test4`() {
        val vec3 = Vector<String>(10)
        vec3.addElement("qwetyuiop")
        vec3.addElement("srabnlkjhgfd")
        vec3.addElement("zxcvnm")
        val obj3 = KruskalWork()
        assertEquals("srabnlkjhgfd", obj3.whichRemove("ra", vec3))
    }

    @Test
    fun `wichRemove fun test5`() {
        val vec4 = Vector<String>(10)
        vec4.addElement("plmkoijn")
        vec4.addElement("bhuygvcftrdxzsewa")
        vec4.addElement("q")
        val obj4 = KruskalWork()
        assertEquals("bhuygvcftrdxzsewa", obj4.whichRemove("uyg", vec4))
    }
}