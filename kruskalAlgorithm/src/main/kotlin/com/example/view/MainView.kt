package com.example.view

import com.example.KruskalController
import javafx.geometry.Pos
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File
import java.io.FileInputStream
import java.lang.Exception

class MainView : View("Kruskal Algorithm") {
    private val extensions = arrayOf(FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"))
    val kruskalController: KruskalController by inject()
    override val root = vbox {
        menubar{
            menu("File"){
                item("Open"){
                    action{
                        val pathGraph: List<File> = chooseFile("Choose graph file", extensions, null, FileChooserMode.Single)
                        try {
                            kruskalController.callCreateGraph(pathGraph)
                        }
                        catch(e: Exception){
                            println(e)
                            System.exit(1)
                        }
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
        button("Clear Field", clearImageView){}
    }

    val workspaces = hbox{

    }
}
