package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiceRollController {

    @GetMapping("/roll-dice")
    public String rollDice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String yourGuess(@PathVariable int guess, Model model){
        int randomRoll = (int) Math.floor(Math.random() *(6) + 1);


        model.addAttribute("guess", guess);
        model.addAttribute("roll", randomRoll);
        return "dice-results";
    }


}
