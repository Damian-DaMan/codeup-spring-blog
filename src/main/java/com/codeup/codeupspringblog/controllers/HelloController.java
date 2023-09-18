package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Spring!";
    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

//    @GetMapping("/join")
//    public String showJoinForm(Model model) {
//        List<Item> shoppingCart = New
//        model.addAttribute("shoppingCart", shoppingCart);
//        return "join";
//    }

}
