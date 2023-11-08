package com.kotlin.yeonsu

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HtmlController {

    @GetMapping("/blog")
    @ResponseBody
    fun getBlog(): String {
        return "Hello World"
    }

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog"
        return "blog"
    }


}