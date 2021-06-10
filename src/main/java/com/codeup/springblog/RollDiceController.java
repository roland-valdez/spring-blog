package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String dice(Model model) {
//        boolean load = false;
        model.addAttribute("load", false);
        return "roll-dice";
    }
    @GetMapping("/roll-dice/{roll}")
    public String diceRoll(@PathVariable int roll, Model model) {
        int dice = (int) Math.floor(Math.random() * (6 - 1 + 1) + 1);
        String outcome;
        if (roll == dice){
          outcome = "won";
      }
      else {
          outcome = "lost";
      }

        model.addAttribute("load", true);
        model.addAttribute("roll", roll);
        model.addAttribute("dice", dice);
        model.addAttribute("outcome", outcome);
        return "roll-dice";
    }
}
