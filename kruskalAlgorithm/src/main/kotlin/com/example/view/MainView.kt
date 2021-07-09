package com.example.view

import com.example.KruskalController
import com.example.KruskalWork
import com.example.Vertex
import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File
import java.io.FileInputStream
import java.lang.Exception

class MainView : View("Kruskal Algorithm") {
    private val extensions = arrayOf(FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"))
    val kruskalController: KruskalController by inject()
    var kruskalWork = KruskalWork()
    var workspaces = AnchorPane()

    override val root = vbox {
        menubar {
            menu("File") {
                item("Open") {
                    action {
                        val pathGraph: List<File> =
                            chooseFile("Choose graph file", extensions, null, FileChooserMode.Single)
                        try {
                            kruskalWork = kruskalController.callCreateGraph(pathGraph)
                        } catch (e: Exception) {
                            println(e)
                            System.exit(1)
                        }
                        getGraph()
                    }
                }
                item("Save")
                item("Exit") {
                    action { System.exit(0) }
                }
            }
            menu("Help") {
                item("Help")
                item("About")
            }
        }
        hbox {
            alignment = Pos.TOP_RIGHT
            val prevImageView = ImageView(Image(FileInputStream("./src/main/resources/left.png")))
            button("Previous Step", prevImageView) {}

            val playPath = FileInputStream("./src/main/resources/play.png")
            val playImage = Image(playPath)
            val playImageView = ImageView(playImage)
            button("Start", playImageView) {
                action {
                    tools.clear()
                    kruskalWork.doKruskal()
                    kruskalWork.modifyGraph()
                    getGraph()
                }
            }

            val nextPath = FileInputStream("./src/main/resources/next.png")
            val nextImage = Image(nextPath)
            val nextImageView = ImageView(nextImage)
            button("Next Step", nextImageView) {}

            val fastImageView = ImageView(Image(FileInputStream("./src/main/resources/fast-forward.png")))
            button("Fast End", fastImageView) {}

            val clearImageView = ImageView(Image(FileInputStream("./src/main/resources/free.png")))
            button("Clear Field", clearImageView) {

            }
        }
        hbox{
            button()
        }
    }
    val tools = anchorpane{
       /*
            */

    }
   // val tools = hbox{

    //}

    fun getGraph() {
        val graphGroup = Group()
        // val kruskalWork = KruskalWork().graph
        val vertexes = kruskalWork.vectorOfVertex
        var tempCoords = Pair<Double, Double>(0.0, 0.0)
        val countVertex = kruskalWork.vectorOfVertex.size
        var tempNumVertex = 1
        var arrayOfCoords = ArrayList<Pair<Double, Double>>()
        var coords: MutableMap<String, Vertex> = mutableMapOf()

        for (item in kruskalWork.vectorOfVertex) {
            coords[item.toString()] = Vertex(
                item.toString(),
                getCoords(tempNumVertex, countVertex).first,
                getCoords(tempNumVertex, countVertex).second
            )
            tempNumVertex++
        }
        println("T")
        for (item in kruskalWork.printGraph) {
            if (item.flag == 0)
                tools.add(createLine(coords[item.v1], coords[item.v2], false))
            else
                tools.add(createLine(coords[item.v1], coords[item.v2], true))
        }

        for (item in vertexes)
            tools.add(createNode(coords[item]))

    }

    fun getCoords(temp: Int, count: Int): Pair<Double, Double> {
        if(temp <= (count/2+1))
            return Pair(100.0*temp-75.0, 0.0)
        else
            return Pair(100.0*temp - (100.0*(count/2+1)-75.0), 100.0)
    }

    fun createNode(a: Vertex?): Circle {
        if (a != null) {
            val circle = Circle(a.x, a.y, 25.0)
            return circle
        }
        return Circle()
    }

    fun createLine(a: Vertex?, b: Vertex?, flag: Boolean): Line{
        if (a != null) {
            if (b != null) {
                return if(flag) {
                    val line = Line(a.x, a.y, b.x, b.y)
                    line.stroke = Color.RED
                    line
                } else
                    Line(a.x, a.y, b.x, b.y)
            }
        }
        return Line()
    }

}
