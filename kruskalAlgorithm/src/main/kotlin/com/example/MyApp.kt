package com.example

import com.example.view.MainView
import javafx.stage.Stage
import tornadofx.App

class MyApp: App(MainView::class, Styles::class){
    override fun start(stage: Stage){
        with(stage){
            width = 1200.0
            height = 675.0
        }
        super.start(stage)
    }
}