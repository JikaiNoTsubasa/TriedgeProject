package fr.triedge.amadeus.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController extends AbstractController{

    @GetMapping("/")
    public ModelAndView root(){
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView model = new ModelAndView("home.html");

        return model;
    }
}
