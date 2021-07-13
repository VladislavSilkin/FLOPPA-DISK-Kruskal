package com.example

import tornadofx.Controller
import java.io.File

class KruskalController : Controller() {
    private val workGraph = KruskalWork()
    var isNextStep = false
    private var isStart = false
    var isLast = false
    fun callCreateGraph(path: List<File>): KruskalWork {
        val fileGraph = File(path[0].toString())

        for (item in fileGraph.readLines()[0]) {
            if (!item.isLetter() && item != ' ')
                throw Exception("Wrong vertexName")
        }

        return workGraph.createGraph(fileGraph.readLines())
    }

    fun callSaveGraph(saveFile: File) {
        var writeString = String()
        for (item in workGraph.vectorOfVertex) {
            if (item == workGraph.vectorOfVertex[workGraph.vectorOfVertex.size - 1])
                writeString += "$item\n"
            else
                writeString += "$item "
        }

        for (item in workGraph.printGraph) {
            writeString += "${item.v1} ${item.v2} ${item.weight.toString()}\n"
        }

        saveFile.writeText(writeString)
    }

    fun callGetGraph(): ArrayList<Edge> {
        isStart = true
        return workGraph.printGraph
    }

    fun callGetVertexes(): ArrayList<String> {
        return workGraph.vectorOfVertex
    }

    fun callDoKruskal() {
        workGraph.doKruskal()
    }

    fun callClearGraph() {
        workGraph.clearGraph()
    }

    fun callUpdatePrintData() {
        workGraph.modifyGraph()
    }

    fun getStringWithoutSpaces(changeString: String): String {
        var outputString = String()
        var counter = 0
        for (item in changeString.split(" ")) {
            if (item != "")
                if(counter != changeString.split(" ").size-1)
                    outputString += "$item "
                else
                    outputString += item
            counter++
        }
        return outputString
    }

    fun callStart() : ArrayList<Edge> {
        isNextStep = true
        return workGraph.steps[workGraph.temp]
    }

    fun callStepNext(): ArrayList<Edge> {
            if (workGraph.temp < workGraph.steps.size - 1) {
                if (isNextStep)
                    workGraph.temp++
                else
                    workGraph.temp += 2
                return workGraph.steps[workGraph.temp]
            } else {
                isLast = true
                return workGraph.steps[workGraph.temp]
            }
    }

    fun callStepPrev(): ArrayList<Edge> {
            if (workGraph.temp > 0) {
                if (!isNextStep)
                    workGraph.temp--
                else
                    workGraph.temp -= 2
                return workGraph.steps[workGraph.temp]
            } else
                return workGraph.steps[workGraph.temp]
        }
}