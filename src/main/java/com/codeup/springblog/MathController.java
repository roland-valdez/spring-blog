package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
public class MathController {
//    @GetMapping("/add/{num1}/and/{num2}")
    @RequestMapping(path = "/add/{num1}/and/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String add(@PathVariable int num1, @PathVariable int num2){
        return num1 + " plus " + num2 + " equals " + (num1 + num2);
    }

    @RequestMapping(path = "/subtract/{num1}/from/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String subtract(@PathVariable int num1, @PathVariable int num2){
        return num2 + " minus " + num1 + " equals " + (num2 - num1);
    }
    @RequestMapping(path = "/multiply/{num1}/and/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String multyply(@PathVariable int num1, @PathVariable int num2){
        return num1 + " times " + num2 + " equals " + (num1 * num2);
    }
    @RequestMapping(path = "/divide/{num1}/by/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public String divide(@PathVariable int num1, @PathVariable int num2){
        return num1 + " divided by " + num2 + " equals " + (num1 / num2);
    }
}
