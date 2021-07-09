package com.example.view

import com.example.KruskalController
import com.example.KruskalWork
import com.example.Vertex
import javafx.geometry.Pos
import javafx.scene.Group
import javafx.scene.image.Image
import javafx.scene.image.ImageView
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

    override val root = vbox {
        menubar{
            menu("File"){
                item("Open"){
                    action{
                        val pathGraph: List<File> = chooseFile("Choose graph file", extensions, null, FileChooserMode.Single)
                        try {
                            kruskalWork = kruskalController.callCreateGraph(pathGraph)
                        }
                        catch(e: Exception){
                            println(e)
                            System.exit(1)
                        }
                        getGraph()
                    }
                }
                item("Save")
                item("Exit"){
                    action{System.exit(0)}
                }
            }
            menu("Help"){
                item("Help")
                item("About")
            }
        }
    }
    val tools = hbox{
        alignment = Pos.TOP_RIGHT

        val prevImageView = ImageView(Image(FileInputStream("/home/neyther/IdeaProjects/kruskalAlgorithm/src/main/resources/left.png")))
        button("Previous Step", prevImageView){}

        val playPath = FileInputStream("/home/neyther/IdeaProjects/kruskalAlgorithm/src/main/resources/play.png")
        val playImage = Image(playPath)
        val playImageView = ImageView(playImage)
        button("Start", playImageView){}

        val nextPath = FileInputStream("/home/neyther/IdeaProjects/kruskalAlgorithm/src/main/resources/next.png")
        val nextImage = Image(nextPath)
        val nextImageView = ImageView(nextImage)
        button("Next Step", nextImageView){}

        val fastImageView = ImageView(Image(FileInputStream("/home/neyther/IdeaProjects/kruskalAlgorithm/src/main/resources/fast-forward.png")))
        button("Fast End", fastImageView){}

        val clearImageView = ImageView(Image(FileInputStream("/home/neyther/IdeaProjects/kruskalAlgorithm/src/main/resources/free.png")))
        button("Clear Field", clearImageView){

        }
    }

    fun getGraph(){
        val graphGroup = Group()
       // val kruskalWork = KruskalWork().graph
        val vertexes = kruskalWork.first
        var tempCoords = Pair<Double, Double>(0.0, 0.0)
        val countVertex = kruskalWork.first.size
        var tempNumVertex = 1
        var arrayOfCoords = ArrayList<Pair<Double, Double>>()
        var coords: MutableMap<String, Vertex> = mutableMapOf()

        for(item in kruskalWork.first){
            coords[item.toString()] = Vertex(item.toString(), getCoords(tempNumVertex, countVertex).first, getCoords(tempNumVertex, countVertex).second)
            tempNumVertex++
        }
        println("T")
        val workspaces = anchorpane{
            for(item in vertexes)
                add(createNode(coords[item]))
            for(item in kruskalWork.graph){
                add(createLine(coords[item.first], coords[item.second]))
            }
        }
    }

    fun getCoords(temp: Int, count: Int): Pair<Double, Double> {
        if(temp <= (count/2+1))
            return Pair(100.0*temp, 0.0)
        else
            return Pair(100.0*temp - (100.0*(count/2+1)), 100.0)
    }

    fun createNode(a: Vertex?): Circle {
        if (a != null) {
            return Circle(a.x, a.y, 25.0)
        }
        return Circle()
    }

    fun createLine(a: Vertex?, b: Vertex?): Line{
        if (a != null) {
            if (b != null) {
                return Line(a.x, a.y, b.x, b.y)
            }
        }
        return Line()
    }
}
