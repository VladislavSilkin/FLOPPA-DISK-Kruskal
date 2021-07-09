package com.example

import com.example.view.MainView
import tornadofx.Controller
import tornadofx.FileChooserMode
import tornadofx.View
import java.io.File

class KruskalController : Controller(){
    val workGraph = KruskalWork()
    val mainView: MainView by inject()
    fun callCreateGraph(path: List<File>) : KruskalWork{
        val fileGraph: File = File(path[0].toString())
        for(item in fileGraph.readLines()[0]){
            if(!item.isLetter() && item != ' ')
                throw Exception("Wrong vertexName")
        }
        return workGraph.createGraph(fileGraph.readLines())
    }
}