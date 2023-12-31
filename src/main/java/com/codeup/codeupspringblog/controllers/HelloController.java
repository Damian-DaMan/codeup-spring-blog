package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/join")
    public String showJoinForm(Model model) {
        List<Item> shoppingCart = new ArrayList<>();
        shoppingCart.add(new Item("hammer"));
        shoppingCart.add(new Item("nail"));
        model.addAttribute("shoppingCart", shoppingCart);

        return "join";
    }

    @PostMapping("/join")
    public String addItem(Model model, @RequestParam(name = "item-name") String itemName) {
        List<Item> shoppingCart = new ArrayList<>();
        shoppingCart.add(new Item("hammer"));
        shoppingCart.add(new Item("nail"));

        shoppingCart.add(new Item(itemName));
        model.addAttribute("shoppingCart", shoppingCart);
        return "join";
    }

}
