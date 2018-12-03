package com.atgenee.core

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
}
