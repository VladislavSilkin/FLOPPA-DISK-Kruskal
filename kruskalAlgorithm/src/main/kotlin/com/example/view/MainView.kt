package com.example.view

import com.example.Edge
import com.example.KruskalController
import com.example.Vertex
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.stage.FileChooser
import javafx.stage.Stage
import tornadofx.*
import java.io.File
import java.io.FileInputStream
import java.lang.Exception
import kotlin.collections.ArrayList
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin
import kotlin.system.exitProcess

class MainView : View("Kruskal Algorithm") {
    private val extensions = arrayOf(FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"))
    private val kruskalController: KruskalController by inject()
    private var printGraphData = ArrayList<Edge>()
    private var loaded = false

    override val root = vbox {
        menubar {
            menu("File") {
                item("Open") {
                    action {
                        kruskalController.callClearGraph()
                        val pathGraph: List<File> =
                            chooseFile("Choose graph file", extensions, null, FileChooserMode.Single)
                        if(pathGraph.isNotEmpty()) {
                            try {
                                kruskalController.callCreateGraph(pathGraph)
                            } catch (e: Exception) {
                                println(e)
                                exitProcess(1)
                            }
                            printGraphData = kruskalController.callGetGraph()
                            printGraph()
                            loaded = true
                        }
                    }
                }
                item("Save"){
                    action{
                        val saveFile: File = FileChooser().showSaveDialog(Stage())
                        kruskalController.callSaveGraph(saveFile)
                    }
                }
                item("Exit") {
                    action { exitProcess(0) }
                }
            }
            menu("Help") {
                item("Help"){
                    action{
                        val helpText = File("./src/main/resources/help.txt").readText()
                        val helpLayout = StackPane()
                        helpLayout.text(helpText)
                        val scene = Scene(helpLayout, 1200.0, 200.0)
                        val stage = Stage()
                        stage.title = "Help"
                        stage.scene = scene
                        stage.show()
                    }
                }
                item("About"){
                    action{
                        val aboutText = File("./src/main/resources/about.txt").readText()
                        val aboutLayout = StackPane()
                        aboutLayout.text(aboutText)
                        val scene = Scene(aboutLayout, 800.0, 200.0)
                        val stage = Stage()
                        stage.title = "About"
                        stage.scene = scene
                        stage.show()
                    }
                }
            }
        }
        hbox {
            alignment = Pos.TOP_RIGHT
            val prevImageView = ImageView(Image(FileInputStream("./src/main/resources/left.png")))
            button("Previous Step", prevImageView) {
            }

            val playImageView = ImageView(Image(FileInputStream("./src/main/resources/play.png")))
            button("Start", playImageView) {
                    //isDisable = false
                    action {
                        if (loaded) {
                            kruskalController.callDoKruskal()
                           // printGraphData = kruskalController.callStepNext()
                            printGraph()
                        }
                    }
                    //isDisable = true
            }

            val nextImageView = ImageView(Image(FileInputStream("./src/main/resources/next.png")))
            button("Next Step", nextImageView) {
                action{
                    if(loaded){
                        tools.clear()
                        printGraphData = kruskalController.callStepNext()
                        printGraph()
                    }
                }
            }

            val fastImageView = ImageView(Image(FileInputStream("./src/main/resources/fast-forward.png")))
            button("Fast End", fastImageView) {
                action {
                    if (loaded) {
                        tools.clear()
                        kruskalController.callDoKruskal()
                        kruskalController.callUpdatePrintData()
                        printGraphData = kruskalController.callGetGraph()
                        printGraph()
                    }
                }
            }

            val clearImageView = ImageView(Image(FileInputStream("./src/main/resources/free.png")))
            button("Clear Field", clearImageView) {
                action {
                    tools.clear()
                    kruskalController.callClearGraph()
                    loaded = false
                }
            }
        }
        hbox {
            button {
                this.isVisible = false
            }
        }
    }
    private val tools = anchorpane {
    }

    private fun printGraph() {
        val vertexes = kruskalController.callGetVertexes()
        val coords: MutableMap<String, Vertex> = getCoords(vertexes)

        tools.clear()

        for (item in printGraphData) {
            val angle = atan((coords[item.v2]!!.y - coords[item.v1]!!.y) / (coords[item.v2]!!.x - coords[item.v1]!!.x))
            if (item.flag == 0)
                tools.add(createLine(coords[item.v1], coords[item.v2], angle, false))
            else
                tools.add(createLine(coords[item.v1], coords[item.v2], angle, true))
            addTextOnLines(coords, angle)
        }

        for (item in vertexes) {
            val tempText = when (item) {
                in "bhk" -> Text(coords[item]!!.x - 10, coords[item]!!.y + 15, coords[item]!!.name) // Вниз
                in "gmdqw" -> Text(coords[item]!!.x - 15, coords[item]!!.y + 12, coords[item]!!.name) // Влево
                in "jtr" -> Text(coords[item]!!.x - 5, coords[item]!!.y + 12, coords[item]!!.name) // Вправо
                in "ilf" -> Text(coords[item]!!.x - 5, coords[item]!!.y + 15, coords[item]!!.name) // Вниз и вправо
                else -> Text(coords[item]!!.x - 10, coords[item]!!.y + 12, coords[item]!!.name)
            }
            tempText.font = Font("Times New Roman", 36.0)

            tools.add(createNode(coords[item]))
            tools.add(tempText)
        }

    }

    private fun getCoords(vertexes: ArrayList<String>): MutableMap<String, Vertex> {
        val coords: MutableMap<String, Vertex> = mutableMapOf()
        val countOfVertex = vertexes.size
        val angle = (countOfVertex - 2) * 180 / countOfVertex
        val radius = 175.0
        val phi = 45.0 + (180.0 - angle) / 2
        var temp = 0

        for (item in vertexes) {
            coords[item] = Vertex(
                item,
                (600.0 + radius * cos(180.0 + 2 * Math.PI * temp / countOfVertex)),
                (275.0 + radius * sin(180.0 + 2 * Math.PI * temp / countOfVertex))
            )
            temp++
        }

        return coords
    }

    private fun createNode(a: Vertex?): Circle {
        val circle = Circle(a!!.x, a.y, 25.0)
        circle.fill = null
        circle.stroke = Color.BLACK
        return circle
    }

    private fun createLine(a: Vertex?, b: Vertex?, angle: Double, flag: Boolean): Line {
        if (a != null) {
            if (b != null) {
                val line: Line
                when {
                    returnQuater(a.x, a.y, b.x, b.y) == 1 -> line = Line(
                        a.x + cos(angle) * 25.0,
                        a.y + sin(angle) * 25.0,
                        b.x - cos(angle) * 25.0,
                        b.y - sin(angle) * 25.0
                    )
                    returnQuater(a.x, a.y, b.x, b.y) == 2 -> line = Line(
                        a.x - cos(angle) * 25.0,
                        a.y - sin(angle) * 25.0,
                        b.x + cos(angle) * 25.0,
                        b.y + sin(angle) * 25.0
                    )
                    returnQuater(a.x, a.y, b.x, b.y) == 3 -> line = Line(
                        a.x - cos(angle) * 25.0,
                        a.y - sin(angle) * 25.0,
                        b.x + cos(angle) * 25.0,
                        b.y + sin(angle) * 25.0
                    )
                    else -> line = Line(
                        a.x + cos(angle) * 25.0,
                        a.y + sin(angle) * 25.0,
                        b.x - cos(angle) * 25.0,
                        b.y - sin(angle) * 25.0
                    )
                }
                if(flag)
                    line.stroke = Color.RED
                return line
            }
        }
        return Line()
    }

    private fun returnQuater(x1: Double, y1: Double, x2: Double, y2: Double): Int{
        return if(x2 - x1 > 0 && y2 - y1 < 0)
            1
        else if(x2 - x1 < 0 && y2 - y1 < 0)
            2
        else if(x2 - x1 < 0 && y2 - y1 > 0)
            3
        else
            4
    }

    private fun addTextOnLines(coords: MutableMap<String, Vertex>, angle: Double) {
       for(item in printGraphData) {
           val x1 = coords[item.v1]!!.x
           val x2 = coords[item.v2]!!.x
           val y1 = coords[item.v1]!!.y
           val y2 = coords[item.v2]!!.y
           val tempText: Text = when(returnQuater(x1, y1, x2, y2)){
               1 -> Text((x2+x1)/2, (y2+y1)/2, item.weight.toString())
               2 -> Text((x2+x1)/2, (y2+y1)/2, item.weight.toString())
               3 -> Text((x2+x1)/2, (y2+y1)/2, item.weight.toString())
               else -> Text((x2+x1)/2, (y2+y1)/2, item.weight.toString())
           }
           tempText.font = Font("Times New Roman", 20.0)
           tools.add(tempText)
       }
    }
}
