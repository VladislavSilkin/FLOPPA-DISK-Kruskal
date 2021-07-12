package com.example

import tornadofx.Controller
import java.io.File

class KruskalController : Controller(){
    private val workGraph = KruskalWork()
    fun callCreateGraph(path: List<File>) : KruskalWork{
        val fileGraph = File(path[0].toString())
        val rightLine = ArrayList<String>()

        for(item in fileGraph.readLines()[0]){
            if(!item.isLetter() && item != ' ')
                throw Exception("Wrong vertexName")
        }

       // for(item in fileGraph.readLines())
        //    rightLine.add(getStringWithoutSpaces(item))
        return workGraph.createGraph(fileGraph.readLines())
    }

    fun callSaveGraph(saveFile: File){
        var writeString = String()
        for(item in workGraph.vectorOfVertex){
            if(item == workGraph.vectorOfVertex[workGraph.vectorOfVertex.size-1])
                writeString += "$item\n"
            else
                writeString += "$item "
        }

        for(item in workGraph.printGraph){
            writeString += "${item.v1} ${item.v2} ${item.weight.toString()}\n"
        }

        saveFile.writeText(writeString)
    }

    fun callGetGraph() : ArrayList<Edge>{
        return workGraph.printGraph
    }

    fun callGetVertexes() : ArrayList<String>{
        return workGraph.vectorOfVertex
    }

    fun callDoKruskal(){
        workGraph.doKruskal()
    }

    fun callClearGraph(){
        workGraph.clearGraph()
    }

    fun callUpdatePrintData(){
        workGraph.modifyGraph()
    }

    fun getStringWithoutSpaces(changeString: String) : String{
        var outputString = String()
        for(item in changeString) {
            outputString += if (item != changeString[changeString.length - 1])
                "$item "
            else
                "$item"
        }
        return outputString
    }
}