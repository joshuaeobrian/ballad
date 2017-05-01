package com.tiy.ballad.web.controller;

import com.tiy.ballad.model.Ballad;
import com.tiy.ballad.model.User;
import com.tiy.ballad.service.BalladService;
import com.tiy.ballad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by josh on 4/27/17.
 */

@Controller
@SessionAttributes("id")
public class BalladController {
    @Autowired
    private UserService userService;
    @Autowired
    private BalladService balladService;

    @ModelAttribute("id")
    public Integer getID(){
        return 0;
    }

    @RequestMapping("/")
    public String  index(){

        return "testing/home";
    }

    @RequestMapping("/login")
    public String loginForm(){
        return "testing/login";
    }
    @PostMapping("/validate")
    public String validate(ModelMap modelMap,String username, String password){
        try {
            User user = userService.login(username, password);
            modelMap.put("id",user.getId());
            return "redirect:/profile";
        }catch (Exception e){
            return "redirect:/login";
        }

    }

    @RequestMapping("/signup")
    public String signUpForm(){
        return "testing/sign_up";
    }
    @PostMapping("/addUser")
    public String signUpUser(HttpSession session, User user){
        try {
            Integer id =  userService.saveNewUser(user);
            session.setAttribute("id", id);
            return "redirect:/profile";
        }catch (Exception e){
            return "redirect:/signup";
        }


    }

    @RequestMapping("/profile")
    public String profile(@ModelAttribute("id") Integer id, Model model){
        User user = userService.findUserById(id);
        model.addAttribute("public", balladService.showMyBallads(user));


        return "testing/profile";
    }

    @RequestMapping("/profilecfg")
    public String profileConfig(){
        return "testing/profilecfg";
    }

    @RequestMapping("/ballad")
    public String ballad(Model model){
        model.addAttribute("ballad",new Ballad());
        return "testing/ballad";
    }

    @RequestMapping("/ballad/{id}")
    public String displayBalladWithId(@PathVariable Integer id, Model model,HttpSession session){
        session.setAttribute("balladId",id);
        Ballad ballad = balladService.findBalladById(id);
//        session.setAttribute("id",ballad.getOwner().getId());

        model.addAttribute("ballad", ballad);
        return "testing/ballad";
    }


}
