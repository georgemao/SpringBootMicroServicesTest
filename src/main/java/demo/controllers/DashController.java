package demo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by George.Mao on 11/7/2014.
 */
@Controller // Spring MVC Controller
public class DashController {

    @Value("${my.custom.prop}")
    private String myProperty;

    @RequestMapping("/test.html")
    public String test(Model model){

        System.out.println("Loaded: " + myProperty);
        model.addAttribute("myProp", myProperty);

        return "test";
    }


    @RequestMapping("/greeting")
    public String greeting(Model model){

        model.addAttribute("name", "mememe");

        return "greeting";
    }
}
