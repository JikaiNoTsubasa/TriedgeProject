package fr.triedge.amadeus.api;

import fr.triedge.amadeus.model.User;
import fr.triedge.amadeus.services.DB;
import fr.triedge.amadeus.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@RestController
public class LoginController extends AbstractController{

    static Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(path = "/login", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView login(
            @RequestParam(value = "username",required = false)String username,
            @RequestParam(value = "password",required = false)String password){
        ModelAndView model = new ModelAndView("login.html");

        if (Utils.isValid(username) && Utils.isValid(password)){
            try {
                User user = DB.getInstance().login(username, password);
                if (user == null){
                    model.addObject("error", "Username or password is incorrect");
                }else{
                    getSession().setAttribute("user",user);
                    createLoginCookie("Ama",username);
                    return new ModelAndView("redirect:/home");
                }
            } catch (SQLException e) {
                model.addObject("Something wrong happened: "+e.getMessage());
                log.error("Failed to login user "+username,e);
            }
        }

        return model;
    }

    @GetMapping("/disconnect")
    public ModelAndView disconnect(){
        getSession().setAttribute("user",null);
        deleteLoginCookie("Ama");
        return new ModelAndView("redirect:/home");
    }

}
