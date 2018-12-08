package com.atgenee.core

import grails.converters.JSON

class IndexController {

    def index() {
        println request.getHeader("X-PJAX")
        println request.getHeader("X-PJAX-Container")
        def content
        switch (params.type) {
            case "1":
                content = "Java"
                break
            case "2":
                content = "JavaScript"
                break
            case "3":
                content = "Python"
                break
        }
        render view: '/index', model: [type: params.type,content: "当前选中的是：${content?:''}"]
    }

    def ajaxData() {
        def info = [state:0,data:""]
        def data = []
        data = ([[type:1, content:"HTML"],[type:2, content:"CSS"],[type:3, content:"JS"]])
        info.data = data
        render info as JSON
    }

    def ajax() {
        println request.getHeader("X-PJAX")
        def content
        switch (params.id) {
            case "1":
                content = "HTML"
                break
            case "2":
                content = "CSS"
                break
            case "3":
                content = "JS"
                break
        }
        render view: "/ajax", model: [type: params.id,content: "当前选中的是：${content?:''}"]
    }
}
